package tv.wanzami.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tv.wanzami.model.JwtToken;
import tv.wanzami.repository.JwtRepository;

@Component
public class GraphQLRequestFilter extends OncePerRequestFilter {
	
	private int charToRemoveFromToken = 7;

	@Autowired
	private JwtRepository jwtRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "https://main.d1m8vpcvr1g12z.amplifyapp.com");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
	    // Allow preflight CORS requests through
	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        return;
	    }
	    		
		if (request.getRequestURI().equals("/graphql")) {
			String authHeader = request.getHeader("Authorization");

			if (authHeader == null || !isValidToken(authHeader)) {
				System.out.println(request.getHeaders(authHeader));
				System.out.println(authHeader);
				throw new RuntimeException("Unauthorized");
			}
		}

		filterChain.doFilter(request, response);
	}

	private boolean isValidToken(String token) {
		String cleanToken = token.substring(this.charToRemoveFromToken, token.length()).trim(); // "Remove Bearer "
    	Optional<JwtToken> jwtObj = jwtRepository.findByStatusAndJwt(1,cleanToken);
    	JwtToken jwtTokenObj = jwtObj.get();
    	
		if (jwtTokenObj.getJwt().equals(cleanToken)) {
			return true;
		}
		return false;
	}
	
}
