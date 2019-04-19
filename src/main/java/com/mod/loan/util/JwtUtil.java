package com.mod.loan.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.mod.loan.config.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
	public static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	public static Key signingKey = null;
	static {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.jwt_sercetKey);
		signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	}

	/**
	 * 生成token
	 * 
	 * @param data
	 * @return
	 */
	public static String generToken(Map<String, String> data) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("alg", "HS256").setHeaderParam("typ", "JWT").setIssuedAt(now)
				.setIssuedAt(new Date()).signWith(signatureAlgorithm, signingKey);
		for (Entry<String, String> claim : data.entrySet()) {
			builder.claim(claim.getKey(), claim.getValue());
		}
		// 生成JWT
		return builder.compact();
	}

	/**
	 * 解密token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Claims ParseJwt(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
			return claims;
		} catch (Exception e) {
			log.error("token解析异常");
			// e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Map<String, String> map = new HashMap<>();
		map.put("uid", "123");
		String token = JwtUtil.generToken(map);
		System.out.println(token);
		Claims claims = ParseJwt(token);
		System.out.println(JSON.toJSONString(claims));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
