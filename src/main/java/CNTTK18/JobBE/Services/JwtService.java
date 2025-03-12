package CNTTK18.JobBE.Services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    //Take Secret key from application.properties
    @Value("${jwt.secretkey}")
    private String secretkey;

    //Generate token
    public String generateToken(String username, int userId) {
        Map<String, Object> claims = new HashMap<>();
        String id = Integer.toString(userId);
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .id(id)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();

    }

    //Tạo 1 SecretKey
    private SecretKey getKey() {
        byte[] keyBytes = secretkey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Lấy username từ token
    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    //Lấy 1 claim từ token
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    //Lấy tất cả claims từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    //Kiểm tra token có hợp lệ không
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Kiểm tra token có hết hạn không
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Lấy thời gian hết hạn của token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
