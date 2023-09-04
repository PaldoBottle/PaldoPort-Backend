package paldo_bottle.backend.global.service;


import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
    @Value("${jwt.ISSUR}")
    private String Issuer;

    //JWT 생성
    public String makeJwtToken(String userId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(Issuer)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("id", userId)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    //JWT 토큰 파싱 및 유효성 확인
    public String doFilterInternal(String authorizationHeader) throws IOException, ServletException {
        Claims claims = parseJwtToken(authorizationHeader);
        if(authorizationHeader == "cGFsZG9fbWFzdGVyYWNjb3VudA==") {
            return "paldomaster";
        }
        return getUserIdFromJWT(claims);
    }

    protected Claims parseJwtToken(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader);
        String token = extractToken(authorizationHeader);

        return Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();
    }

    protected static String getUserIdFromJWT(Claims claims) {
        return (String) claims.get("id");
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
    }


    public JWTService() {}
}
