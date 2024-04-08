package com.mugenminds.mugenminds.security;

import com.mugenminds.mugenminds.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // Corrected generateToken method
    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        Date currentDate = new Date();
        Date expiry = new Date(currentDate.getTime()+jwtExpirationDate);

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst() // Since we're assuming only one role per user
                .orElseThrow(() -> new IllegalStateException("User has no roles"));

        String token = Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(expiry)
                .signWith(key())
                .compact();

        return token;
    }

    // Corrected getUsername method
    public String getUsername(String token){

        return Jwts.parser()
                .setSigningKey(key())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }


    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    // Corrected validateToken method
    public boolean validateToken(String token){

        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .parseClaimsJws(token);

            return true;
        }catch (MalformedJwtException malformedJwtException){
            throw new InvalidJwtAuthenticationException("Invalid JWT token");
        }catch (ExpiredJwtException expiredJwtException){
            throw new InvalidJwtAuthenticationException("Expired JWT token");
        }catch (UnsupportedJwtException unsupportedJwtException){
            throw new InvalidJwtAuthenticationException("Unsupported JWT token");
        }catch (IllegalArgumentException illegalArgumentException){
            throw new InvalidJwtAuthenticationException("String is null or empty");
        }catch (Exception e){
            e.printStackTrace();
            throw new InternalError("Something is wrong");
        }
    }
}
