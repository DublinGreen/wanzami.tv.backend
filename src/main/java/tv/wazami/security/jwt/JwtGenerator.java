package tv.wazami.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tv.wazami.model.User;

import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {


    public String generate(User jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getEmail());
        claims.put("password", String.valueOf(jwtUser.getPassword()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "Graphql")
                .compact();
    }
}
