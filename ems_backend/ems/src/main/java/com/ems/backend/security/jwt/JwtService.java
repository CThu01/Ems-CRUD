package com.ems.backend.security.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRETKEY = "160C058169E069AC3E94D69AB10205612AD7A244FC9770640A605438A4FAA44D8EBA1126A40135472CFCA68D55B60D151AA33CC341623F7623D4F8314B7F351F";
	
	private static final Long VALIDITY = TimeUnit.MINUTES.toMillis(30);
	
	public String generateToken(UserDetails userDetails) {
		return Jwts
				.builder()
				.subject(userDetails.getUsername())
				.issuedAt(Date.from(Instant.now()))
				.expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
				.signWith(generateKey())
				.compact();
	}

	private SecretKey generateKey() {
		byte[] decodedValue =  Base64.getDecoder().decode(SECRETKEY);
		System.out.println("decodedValue : " + decodedValue);
		return Keys.hmacShaKeyFor(decodedValue);
	}
	
}





