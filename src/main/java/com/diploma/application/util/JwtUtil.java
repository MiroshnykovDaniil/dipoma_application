package com.diploma.application.util;
import com.diploma.application.config.AppProperties;
import com.diploma.application.model.User;
import com.diploma.application.security.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public JwtUtil(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    private AppProperties appProperties;


    public Boolean validateToken(String token) {
        try {
            System.out.println("Validation");
            System.out.println(token);
            System.out.println(appProperties.getAuth());
            System.out.println(appProperties.getAuth().getTokenSecret());

            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token);
            System.out.println("End of validation");
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty. "+ex,ex);
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        System.out.println("getUserIdFromToken");
        System.out.println(Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody());
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.getSubject());
        return claims.getSubject();
    }

    public String createToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println("createToken");
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        System.out.println("createToken: builder");
        return Jwts.builder()
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String createToken(User user) {
        logger.trace("CREATE TOKEN !!!!");
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }


}
