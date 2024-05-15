package com.maboroshi.cave.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


@Component
public class JWTUtils {
    private  static  String signKey = "maboroshi";
    private  static  Long expire = 3*3600*1000L;

    public static String genJWT(Map<String,Object> claims) {

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+ expire))
                .compact();
        return jwt;
    }
    public static Claims ParseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
