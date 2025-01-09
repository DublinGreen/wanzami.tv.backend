package tv.wazami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application Entry class
 */
@ComponentScan(basePackages = "tv.wazami")
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
