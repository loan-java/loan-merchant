package com.mod.loan.kuaiqian.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;


/**
 * 
 * @ClassName: PKIUtil
 * @Description: PKI工具类
 * @author xiaodong.zhi
 * @date 2015-12-2
 *
 */
public class PKIUtil {
	
	private static Logger logger = Logger.getLogger(PKIUtil.class);
	
	private static final String ENCODING = "utf-8";
	
    
    public static String byte2UTF8String(byte[] bytes) {
		try {
			return new String(bytes, ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error("byte2UTF8String exception", e);
			return "";
		}
	}
    
    public static String byte2UTF8StringWithBase64(byte[] bytes) {
    	try {
    		return new String(Base64.encodeBase64(bytes), ENCODING);
    	} catch (UnsupportedEncodingException e) {
    		logger.error("byte2UTF8String exception", e);
    		return "";
    	}
    }
    
    public static byte[] utf8String2ByteWithBase64(String string) {
    	try {
    		return Base64.decodeBase64(string.getBytes(ENCODING));
    	} catch (UnsupportedEncodingException e) {
    		logger.error("utf8String2Byte exception", e);
    		return null;
    	}
    }
	
}
