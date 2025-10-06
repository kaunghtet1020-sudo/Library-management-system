package com.LibraryManagment.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.LibraryManagment.Config.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component

public class JwtUtil {
   @Autowired
   private JWTProperties jwtProperties;

   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   private Claims extractAllClaims(String token) {
      return Jwts
            .parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

   }

   private Boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   public String generatedToken(UserDetails userDetails) {
      Map<String, Object> claims = new HashMap<>();
      return createToken(claims, userDetails.getUsername());
   }

   public String createToken(Map<String, Object> claims, String subject) {
      System.out.println("We are generation token");
      String token = Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
            .signWith(getSignKey())
            .compact();
      return token;

   }

   private SecretKey getSignKey() {
      byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
      return Keys.hmacShaKeyFor(keyBytes);
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

   }

   public String getUserNameFromToken(HttpServletRequest request) {
      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      if (authHeader == null || !authHeader.startsWith("Bearer")) {
         return null;

      }
      jwt = authHeader.substring(7);
      return extractUsername(jwt);

   }
}
