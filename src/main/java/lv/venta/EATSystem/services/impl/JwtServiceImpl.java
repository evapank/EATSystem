package lv.venta.EATSystem.services.impl;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lv.venta.EATSystem.models.MyUser;

public class JwtServiceImpl {
	
		@Value("${JWT_SECRET_KEY}")
		private String SECRET_KEY;
	
		public String extractUsername(String token){
			return extractClaim(token, Claims::getSubject);
		}
	
	
		public boolean isValid(String token, UserDetails userDetails){
			String username = extractUsername(token);
			return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		}
	
	
		private boolean isTokenExpired(String token){
			return extractClaim(token, Claims::getExpiration).before(new Date());
		}
	
	
		public <T> T extractClaim(String token, Function<Claims, T> resolver){
			Claims claims = extractAllClaims(token);
			return resolver.apply(claims);
		}
	
	
		private Claims extractAllClaims(String token){
			return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJwt(token)
			.getBody();
	
		}
	
		public String generateToken(MyUser user){
			String token = Jwts.builder()
			.setSubject(user.getUsername())
			.claim("authority", user.getAuthority().getTitle())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * (24*60)))
			.signWith(getSigningKey())
			.compact();
			return token;
		}
	
	
		public SecretKey getSigningKey(){
			byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
			return Keys.hmacShaKeyFor(keyBytes);
		}
	}

