package tv.wanzami.query;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import tv.wanzami.config.AwsCredentialsConfig;

@Component
public class S3Query implements GraphQLQueryResolver {

    @Value("${aws.bucket}")
    private String bucketName;

    private final AwsCredentialsConfig awsCredentialsConfig;

    public S3Query(AwsCredentialsConfig awsCredentialsConfig) {
        this.awsCredentialsConfig = awsCredentialsConfig;
    }
    
    private S3Presigner getPresigner() {
        return S3Presigner.builder()
                .region(Region.EU_NORTH_1) // Change to your AWS region
//                .credentialsProvider(ProfileCredentialsProvider.create())
//                .credentialsProvider(DefaultCredentialsProvider.create())
//                .credentialsProvider(StaticCredentialsProvider.create(
//                        AwsBasicCredentials.create(awsCredentialsConfig.getAccessKeyId(), awsCredentialsConfig.getSecretAccessKey())
//                    ))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsCredentialsConfig.getAccessKeyId(), awsCredentialsConfig.getSecretAccessKey())
                    ))// READ ONLY ACCESS
                .build();
    }

    public String generatePresignedPutUrl(String fileName) {  // Removed @RequestParam (not needed in GraphQL)
        try (S3Presigner presigner = getPresigner()) { // Ensuring resource cleanup

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType("application/octet-stream") // Adjust content type as needed
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(30)) // URL valid for 30 minutes
                    .putObjectRequest(putObjectRequest)
                    .build();

            URL presignedUrl = presigner.presignPutObject(presignRequest).url();
            return presignedUrl.toString();
        }
    }

    public String generatePresignedGetUrl(String fileName) {
        try (S3Presigner presigner = getPresigner()) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            GetObjectPresignRequest getPresignRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(getObjectRequest)
                    .signatureDuration(Duration.ofMinutes(90)) // Set expiry
                    .build();

            URL presignedUrl = presigner.presignGetObject(getPresignRequest).url();
            return presignedUrl.toString();
        }
    }

    public List<String> generatePresignedUrlsForFolder(String folderPrefix) {
    	List<String> urls = new ArrayList<>();

        try (S3Presigner presigner = getPresigner()) {
            S3Client s3Client =  (S3Client) S3Presigner.builder()
                    .region(Region.EU_NORTH_1) // Change to your AWS region
                  .credentialsProvider(ProfileCredentialsProvider.create())
                  .credentialsProvider(DefaultCredentialsProvider.create())
                  .credentialsProvider(StaticCredentialsProvider.create(
                          AwsBasicCredentials.create(awsCredentialsConfig.getAccessKeyId(), awsCredentialsConfig.getSecretAccessKey())
                      ))
//                  .credentialsProvider(StaticCredentialsProvider.create(
//                          AwsBasicCredentials.create(awsCredentialsConfig.getAccessKeyId(), awsCredentialsConfig.getSecretAccessKey())
//                      ))// READ ONLY ACCESS
                  .build();

            ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .prefix(folderPrefix.endsWith("/") ? folderPrefix : folderPrefix + "/")
                    .build();

            ListObjectsV2Response listResponse = s3Client.listObjectsV2(listRequest);

            for (S3Object s3Object : listResponse.contents()) {
                String key = s3Object.key();

                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();

                GetObjectPresignRequest getPresignRequest = GetObjectPresignRequest.builder()
                        .getObjectRequest(getObjectRequest)
                        .signatureDuration(Duration.ofMinutes(90))
                        .build();

                URL presignedUrl = presigner.presignGetObject(getPresignRequest).url();
                urls.add(presignedUrl.toString());
            }

            s3Client.close();
        }

        return urls;
    }
}
