package com.example.springaclsecurity.utils;

import com.example.springaclsecurity.model.request.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@UtilityClass
public class JwtUtils {

    @Setter
    private String jwtSecretKey;

    @Setter
    private Long jwtTokenExpiryTimeInMillis;

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(final String token) {

        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(final String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(final String token) {

        return Jwts.
                parserBuilder().
                setSigningKey(getSignKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).after(new Date());
    }

    public Boolean validateToken(final String token, final UserDetails userDetails) {

        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    public String generateToken(final LoginRequest loginRequest) {

        return createToken(new HashMap<>(), loginRequest);
    }

    public String createToken(final Map<String, Object> map, final LoginRequest loginRequest) {

       return Jwts.builder()
               .setClaims(map)
               .setSubject(loginRequest.getUsername())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiryTimeInMillis))
               .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }
}
