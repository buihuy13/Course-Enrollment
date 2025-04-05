package CNTTK18.JobBE.Services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import CNTTK18.JobBE.Exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    //Take Secret key from application.properties
    @Value("${jwt.secretkey}")
    private String secretkey;

    //Generate token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3*60*1000)) //3ph
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
    public String extractUserName(String token) throws Exception {
        // extract the username from jwt token
        String username = extractClaim(token, Claims::getSubject);
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Token không chứa thông tin username");
        }
        return username;
    }

    //Lấy 1 claim từ token
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    //Lấy tất cả claims từ token (Kiểm tra luôn cả chữ ký)
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    //Kiểm tra token có hợp lệ không (hết hạn hay không)
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    //Kiểm tra token có hết hạn không
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Lấy thời gian hết hạn của token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateRefreshToken(String username)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tokenType", "refresh");
    
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000)) // 3 ngày
                .signWith(getKey())
                .compact();
    }
    
    public String refreshAccessToken(String refreshToken) throws Exception {
         // Kiểm tra token có hợp lệ không
         Claims claims = extractAllClaims(refreshToken);
            
         // Kiểm tra loại token
         if (!"refresh".equals(claims.get("tokenType"))) {
             throw new InvalidTokenException("Refresh Token không hợp lệ");
         }
         
         // Lấy thông tin người dùng từ token
         String username = claims.getSubject();
         
         // Tạo access token mới
         return generateToken(username);
    }
}
