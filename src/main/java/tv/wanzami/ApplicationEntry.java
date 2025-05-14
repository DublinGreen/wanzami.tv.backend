package tv.wanzami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application Entry class
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "tv.wanzami")
@SpringBootApplication
public class ApplicationEntry {
	
	/**
	 * application main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEntry.class, args);
	}

	@Bean
	public graphql.schema.GraphQLScalarType extendedScalarLong() {
	    return graphql.scalars.ExtendedScalars.GraphQLLong;
	}
	
}
