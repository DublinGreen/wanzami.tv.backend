package tv.wazami.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tv.wazami.model.JwtToken;
import tv.wazami.repository.JwtRepository;

@Component
public class GraphQLRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtRepository jwtRepository;
	
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
    	String newValue = token.substring(7, token.length()); // "Remove Bearer "
        System.out.println("Intercepted: " + newValue);

//    	Optional<JwtToken> jwtObj  = jwtRepository.findById((long) 604);
    	Optional<JwtToken> jwtObj  = jwtRepository.findByToken(newValue);
    	
		if (jwtObj.isPresent()) {
			return true;
    	}
        return false;
    }
}
