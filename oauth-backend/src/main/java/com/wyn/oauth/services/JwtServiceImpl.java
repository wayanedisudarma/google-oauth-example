package com.wyn.oauth.services;

import com.wyn.oauth.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-expiration}")
	private long accessExpiration;

	@Value("${jwt.refresh-expiration}")
	private long refreshExpiration;

	@Override
	public String generateAccessToken(UserEntity user) {
		return Jwts.builder()
			.setSubject(user.getId())
			.claim("type", "access")
			.claim("email", user.getEmail())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
			.signWith(getKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public String generateRefreshToken(UserEntity user) {
		return Jwts.builder()
			.setSubject(user.getId())
			.claim("type", "refresh")
			.claim("email", user.getEmail())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
			.signWith(getKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public Claims parse(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	public String extractUserId(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private Key getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

}
