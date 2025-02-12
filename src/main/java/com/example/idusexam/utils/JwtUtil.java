package com.example.idusexam.utils;

import com.example.idusexam.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    private static String SECRET;
    @Value("${jwt.expired}")
    private int exp;
    private static int EXP;

    @PostConstruct
    public void init() {
        SECRET = secret;
        EXP = exp;
    }

    public static User getUser(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return User.builder()
                    .idx(claims.get("userIdx", Long.class))
                    .username(claims.get("username", String.class))
                    .nickname(claims.get("nickname", String.class))
                    .email(claims.get("userEmail", String.class))
                    .phone(claims.get("userPhone", Long.class))
                    .gender(claims.get("userGender", String.class))
                    .build();

        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return null;
        }
    }

    public static String generateToken(Long userIdx, String userEmail, Long userPhone,
                                       String userName, String userNickName, String userGender) {
        Claims claims = Jwts.claims();
        claims.put("userNickName", userNickName);
        claims.put("userEmail", userEmail);
        claims.put("userIdx", userIdx);
        claims.put("userPhone", userPhone);
        claims.put("userName", userName);
        claims.put("userGender", userGender);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }

    public static boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return false;
        }
        return true;
    }
}
