package com.metamagic.ms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenService {

	@Value("${token.key}")
	private String tokenKey = null;

	@Value("${token.validity}")
	private Long tokenValidity = null;

	public String generateToken(String loginId) {
		JSONObject json = new JSONObject();
		try {
			json.put("userId", loginId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return generateTokenFromJson(json, tokenValidity);
	}

	public JSONObject getTokenData(String tokenId) throws JSONException, ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		String tokenData = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(tokenId).getBody().getSubject();
		return new JSONObject(tokenData);
	}

	private String generateTokenFromJson(JSONObject tokenJson, Long tokenExpiry) {
		return Jwts.builder().setSubject(tokenJson.toString())
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpiry))
				.signWith(SignatureAlgorithm.HS512, tokenKey).compact();
	}

	/**
	 * method to create JWT token using json object as a payload and expiry in
	 * miliseconds
	 * 
	 * @param tokenJson
	 *            a payload for JWT token
	 * @param tokenExpiry
	 *            token expiry in milliseconds
	 * 
	 * @return JWT token
	 */
	public String generateToken(JSONObject tokenJson, Long tokenExpiry) {
		return generateTokenFromJson(tokenJson, tokenExpiry);
	}
}