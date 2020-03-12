package com.community.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    /**
     * 秘钥
     */
    @Value("jwt.secret")
    private String secret;

    /**
     * 过期时间
     */
    private Long expiration = 900L;

    /**
     * 生成token
     *
     * @return
     */
    public String createToken(Long userId, String username, String role) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        map.put("role", role);
        return Jwts.builder()
                .setClaims(map)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        return generateToken(token).getSubject();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims generateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    public Boolean isTokenExpired(String token) {
        Date expiration;
        try {
            expiration = this.getExpirationDateFromToken(token);
        } catch (ExpiredJwtException var4) {
            expiration = null;
//            log.error("expiredJwtException->" + var4.getMessage());
        } catch (Exception var5) {
//            log.error("isTokenExpired", var5);
            expiration = null;
        }

        return expiration == null ? true : expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) throws Exception {
        Claims claims = this.getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration;
    }


    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
