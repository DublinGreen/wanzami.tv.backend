package tv.wazami.service;

import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SuppressWarnings("deprecation")
@Component
public class FileUploadResolver {

    public String uploadFile(DataFetchingEnvironment environment) throws IOException {
        GraphQLServletContext context = environment.getContext();
        MultipartFile file = (MultipartFile) context.getFileParts().get(0);

        // Process the file (save it, analyze it, etc.)
        String originalFilename = file.getOriginalFilename();
        byte[] content = file.getBytes();

        // For demo purposes, return the file name
        return "Uploaded file: " + originalFilename;
    }
}
