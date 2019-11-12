package com.mod.loan.pay.kuaiqian.util;

import com.mod.loan.service.pay.baofoo.BaofooBalanceQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;


/**
 * 
 * @ClassName: PKIUtil
 * @Description: PKI工具类
 * @author xiaodong.zhi
 * @date 2015-12-2
 *
 */
@Slf4j
public class PKIUtil {
	

	private static final String ENCODING = "utf-8";

	public static final Logger log = LoggerFactory.getLogger(PKIUtil.class);

    public static String byte2UTF8String(byte[] bytes) {
		try {
			return new String(bytes, ENCODING);
		} catch (UnsupportedEncodingException e) {
			log.error("byte2UTF8String exception", e);
			return "";
		}
	}
    
    public static String byte2UTF8StringWithBase64(byte[] bytes) {
    	try {
    		return new String(Base64.encodeBase64(bytes), ENCODING);
    	} catch (UnsupportedEncodingException e) {
			log.error("byte2UTF8String exception", e);
    		return "";
    	}
    }
    
    public static byte[] utf8String2ByteWithBase64(String string) {
    	try {
    		return Base64.decodeBase64(string.getBytes(ENCODING));
    	} catch (UnsupportedEncodingException e) {
			log.error("utf8String2Byte exception", e);
    		return null;
    	}
    }
	
}
