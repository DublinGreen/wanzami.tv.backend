package tv.wazami.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import tv.wazami.enums.Role;
import tv.wazami.model.User;

import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "Graphql";

    public User validate(String token) {

        User jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new User();
            
            
            jwtUser.setUsername(body.getSubject());
            jwtUser.setPassword((String) body.get("userId"));
            jwtUser.setRole((Role) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
