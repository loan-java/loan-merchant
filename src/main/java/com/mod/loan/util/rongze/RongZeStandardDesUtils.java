package com.mod.loan.util.rongze;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

/**
 * 
 * @Description : RongZeStandardDesUtils
 * @Copyright   : Sinaif Software Co.,Ltd.Rights Reserved
 * @Company     : 海南新浪爱问普惠科技有限公司
 * @author      : Darben
 * @version     : 1.0
 * @Date        : 2018年11月5日 下午16:00:07
 *
 */
public class RongZeStandardDesUtils {

    private static final Integer DES_KEY_LEN = 16;
    private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public RongZeStandardDesUtils() {
    }

    /**
     * 生成DES秘钥，长度为16位
     * @return
     * @throws Exception
     */
    public static String generateDesKey() throws Exception {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < DES_KEY_LEN; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * DES 算法 ECB 模式 PKCS5 填充方式加密
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String source, String key) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(desKey);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(1, secureKey,random);

        byte[] buf = cipher.doFinal(source.getBytes());

        return new String(Base64.encodeBase64(buf));
    }

    /**
     * DES 算法 ECB 模式 PKCS5 填充方式解密
     * @param cryptograph
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph, String key) throws Exception {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(2, secureKey,random);
            byte[] buf = cipher.doFinal(Base64.decodeBase64(cryptograph.getBytes()));
            return new String(buf);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 签名方法
     * @param content
     * @param privateKey
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String sign(String content, String privateKey) throws Exception {
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
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 验签方法
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
}
