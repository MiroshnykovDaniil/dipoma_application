package com.diploma.application.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    //todo It's not really cool to keep secret right here with this shape, need to think of better solution like property file
    private String SECRET_KEY = "secret";

    // Here we extract the username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Here we extract the account expiration date from existing token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // this method takes token and with claimsResolver it figures our what the claims are
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // here we check if token is expired or not
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // method for generating tokens on UserDetails by createToken method
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // Here we validate is username in token the same with username in userDetails
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
