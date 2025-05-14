package tv.wanzami.query;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import graphql.kickstart.tools.GraphQLQueryResolver;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class S3Query implements GraphQLQueryResolver {

	private final String bucketName = "wanzami.tv.bucket";

    private S3Presigner getPresigner() {
        return S3Presigner.builder()
                .region(Region.EU_NORTH_1) // Change to your AWS region
//                .credentialsProvider(ProfileCredentialsProvider.create())
//                .credentialsProvider(DefaultCredentialsProvider.create())
//                .credentialsProvider(StaticCredentialsProvider.create(
//                        AwsBasicCredentials.create("AKIA45Y2R2BSO5W52OM5", "EWxCDW4p6pkcFbAUBhWYXE+ycQfJrxFM++dBo333")
//                    ))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("AKIA45Y2R2BSITSOTVVO", "gIpWg6S3En23KTIBeWeOQO8Dtt3AieLwZLfZFfmi")
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
                          AwsBasicCredentials.create("AKIA45Y2R2BSITSOTVVO", "gIpWg6S3En23KTIBeWeOQO8Dtt3AieLwZLfZFfmi")
                      ))
//                  .credentialsProvider(StaticCredentialsProvider.create(
//                          AwsBasicCredentials.create("AKIA45Y2R2BSITSOTVVO", "gIpWg6S3En23KTIBeWeOQO8Dtt3AieLwZLfZFfmi")
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
