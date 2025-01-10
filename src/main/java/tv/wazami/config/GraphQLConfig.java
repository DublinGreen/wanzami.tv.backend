package tv.wazami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.schema.GraphQLScalarType;
import tv.wazami.service.FileUploadResolver;
import tv.wazami.service.UploadCoercing;

@Configuration
public class GraphQLConfig {

//    @Bean
//    public RuntimeWiringConfigurer runtimeWiringConfigurer(FileUploadResolver fileUploadResolver) {
//        return wiring -> wiring
//            .type("Mutation", builder -> builder.dataFetcher("uploadFile", fileUploadResolver::uploadFile));
//    }
    
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiring -> wiring
            .scalar(GraphQLScalarType.newScalar()
                .name("Upload")
                .description("A file upload scalar type")
                .coercing(new UploadCoercing())
                .build());
    }
}
