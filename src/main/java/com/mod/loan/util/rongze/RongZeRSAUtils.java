package com.mod.loan.util.rongze;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description : RongZeRSAUtils
 * @Copyright   : Sinaif Software Co.,Ltd.Rights Reserved
 * @Company     : 海南新浪爱问普惠科技有限公司
 * @author      : Darben
 * @version     : 1.0
 * @Date        : 2018年11月5日 下午16:00:07
 *
 */
public class RongZeRSAUtils {
	private static int KEYSIZE = 1024;

	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static Map<String, String> generateKeyPair() throws Exception {
		SecureRandom sr = new SecureRandom();
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(KEYSIZE, sr);
		KeyPair kp = kpg.generateKeyPair();
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.encodeBase64(publicKeyBytes), "UTF-8");
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.encodeBase64(privateKeyBytes), "UTF-8");
		Map<String, String> map = new HashMap();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.encodeBase64(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}

	public static String encrypt_privateKey(String source, String privateKey) throws Exception {
		Key key = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(1, key);
		byte[] data = source.getBytes();
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 117) {
			byte[] cache;
			if (inputLen - offSet > 117) {
				cache = cipher.doFinal(data, offSet, 117);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		return new String(Base64.encodeBase64(out.toByteArray()), "UTF-8");
	}

	/**
	 * RSA/ECB/PKCS1Padding 进行加密（秘钥长度1024）
	 * 
	 * @param source
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String source, String publicKey) throws Exception {
		Key key = getPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(1, key);
		byte[] data = source.getBytes("UTF-8");
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 117) {
			byte[] cache;
			if (inputLen - offSet > 117) {
				cache = cipher.doFinal(data, offSet, 117);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		return new String(Base64.encodeBase64(out.toByteArray()), "UTF-8");
	}

	/**
	 * RSA 私钥进行解密
	 * @param cryptograph
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String cryptograph, String privateKey) throws Exception {
		Key key = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(2, key);
		byte[] encryptedData = Base64.decodeBase64(cryptograph.getBytes());
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 128) {
			byte[] cache;
			if (inputLen - offSet > 128) {
				cache = cipher.doFinal(encryptedData, offSet, 128);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] decryptedData = out.toByteArray();
		return new String(decryptedData, "UTF-8");
	}

	public static String decrypt_publicKey(String cryptograph, String publicKey) throws Exception {
		Key key = getPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(2, key);
		byte[] encryptedData = Base64.decodeBase64(cryptograph.getBytes());
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 128) {
			byte[] cache;
			if (inputLen - offSet > 128) {
				cache = cipher.doFinal(encryptedData, offSet, 128);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] decryptedData = out.toByteArray();
		return new String(decryptedData);
	}

	/**
	 * 生成签名
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static String sign(String content, String privateKey) throws InvalidKeySpecException,
			NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, SignatureException {
		String charset = "UTF-8";

		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(priKey);
			signature.update(content.getBytes(charset));
			byte[] signed = signature.sign();
			return new String(Base64.encodeBase64(signed));
		} catch (Exception var8) {
			throw var8;
		}
	}

	/**
	 * 验签处理
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public static boolean checkSign(String content, String sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes());
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));
			boolean bverify = signature.verify(Base64.decodeBase64(sign.getBytes()));
			return bverify;
		} catch (Exception var8) {
			var8.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVEkgWubrj8wCvttn2f20iL065zcW3NiO7HUxBWoZA7Os/54j8cDAethJDM7xOV4ASF88XWvyJMnWECSjlibbzEX9m+n2R9Vuem6K9xmFtHhSh+ser7QDOTyRw0jinJheDLjkst5EqzbmAfrHzbS90xt1pOqQDT7lmEokXS+r/dwIDAQAB";
		
		String privatekey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJUSSBa5uuPzAK+22fZ/bSIvTrnNxbc2I7sdTEFahkDs6z/niPxwMB62EkMzvE5XgBIXzxda/IkydYQJKOWJtvMRf2b6fZH1W56bor3GYW0eFKH6x6vtAM5PJHDSOKcmF4MuOSy3kSrNuYB+sfNtL3TG3Wk6pANPuWYSiRdL6v93AgMBAAECgYASw/xjL/pA28RIWmJRz3SzivUEGs065QvWcDe7OmQQJELxBnqOHKlSFGV0JD0eN5GdtXWSYRfNRZbZijWlv5JSELJCu+FAgZVtsoIB5x+375N34qE3EFbjUAlMBdyq1yac/R32PVe8ZtaeTEA3O1BaI07EBJKSZOnurGs577tGsQJBAPBDJ2XqvGR9AJpdVCwgw/3DcXzctQOWK3Zh72F5bbAhVX5MrcMH5Dtvebvsnweqhy7CW0JYT4skqOIw96xptl0CQQCe1f17QNcXa0TTauJmQe5YgB+7goaocMp5ijb+aPA8I1qwg9HT5KMGH+n/8lb0pNghumKUPFffQcbcAclEXMfjAkBAE0jAj3eqvQOaJ2W5Vwut85+ikyHbUQNKURgMQNwV6u3n/v8gUkx15WbrzVEkNEBVIhRAf1jHXQGFQVXsL00pAkEAkTP/LpXwDDC8Eqo7nk/XnIBEgh6pC4NRFhsMezdjpxaK6aMYRyVVA1xZhun8JFouW2cQh4NCsY5oROg9HonTEQJBAIO5RS4aeKDaIE/2MznafFJAMF4Syxw4DC0u2k/VbiXcwIAsukw/rjuVzAxM+4zq581tqcaScgmalpfk/jj0ibI=";
		
		try {
			String content="你好2018";
			
			String e = encrypt(content, publickey);
			System.out.println(e);
			byte[] encodedText = Base64.encodeBase64(e.getBytes());
			System.out.println(new String(encodedText,"UTF-8"));
			System.out.println(new String(Base64.decodeBase64(encodedText)));
			
			/*String r = decrypt(e, privatekey);
			System.out.println("====="+r);*/
			
			/*String sign = sign(content, privatekey);
			System.out.println(sign);
			System.out.println(checkSign(content, sign, publickey));*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
