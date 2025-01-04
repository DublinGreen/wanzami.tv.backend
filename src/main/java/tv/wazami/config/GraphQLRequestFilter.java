package tv.wazami.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.hibernate.mapping.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tv.wazami.model.JwtToken;
import tv.wazami.repository.JwtRepository;
import tv.wazami.service.JwtService;

@Component
public class GraphQLRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtRepository jwtRepository;

	@Autowired
	private final JdbcTemplate jdbcTemplate;

//	private static final Logger log = (Logger) LoggerFactory.getLogger(GraphQLRequestFilter.class);

	public GraphQLRequestFilter(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getRequestURI().equals("/graphql")) {
			String authHeader = request.getHeader("Authorization");
			System.out.println("Intercepted GraphQL request with Authorization header: " + authHeader);

			if (authHeader == null || !isValidToken(authHeader)) {
				throw new RuntimeException("Unauthorized");
			}
		}

		filterChain.doFilter(request, response);
	}

	private boolean isValidToken(String token) {
		String newValue = token.substring(7, token.length()).trim(); // "Remove Bearer "
		System.out.println("Intercepted: " + newValue);
		System.out.println(newValue);

//		Optional<JwtToken> jwtObj = jwtRepository.findById((long) 4);
//    	Optional<JwtToken> obj2 = jwtRepository.findByJwtNative(newValue);
//    	Optional<JwtToken> obj3 = jwtRepository.findAllActiveStatus();
//    	Optional<JwtToken> obj4 = jwtRepository.findByStatusAndJwt(1,newValue);
//    	System.out.println("HERE --------------" + obj2);
//    	System.out.println("HERE --------------" + obj3);
//    	System.out.println("HERE --------------" + obj4);

//	    log.info("Querying for jwt records where jwt = 'token':");

		JwtService jwtService = new JwtService(jdbcTemplate);
		java.util.List<Map<String, Object>> records = jwtService.fetchAndPrintRecords(newValue);
		
	    
		if (records.get(0).get("jwt").equals(newValue)) {
			return true;
		}
		return false;
	}

}
