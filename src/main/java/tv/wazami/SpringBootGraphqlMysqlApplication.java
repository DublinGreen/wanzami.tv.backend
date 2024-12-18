package tv.wazami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootGraphqlMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphqlMysqlApplication.class, args);
	}

	@Bean
	public graphql.schema.GraphQLScalarType extendedScalarLong() {
	    return graphql.scalars.ExtendedScalars.GraphQLLong;
	}
	
}
