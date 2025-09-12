package com.example.SpringSecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtUtil {

    private String secrectKey = "nTcHbTBzfqSkbedG/pIHMVNYvi3M/4PCW69iVdXWaHA=";

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey(){
        byte[] keybytes = Decoders.BASE64.decode(secrectKey);
        return Keys.hmacShaKeyFor(keybytes);
    }
}
