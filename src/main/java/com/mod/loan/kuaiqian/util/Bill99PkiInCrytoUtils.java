package com.mod.loan.kuaiqian.util;

import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.mod.loan.kuaiqian.schema.ma.mbrinfo.MaSealPkiDataType;
import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiang.zhang
 * @version 1.0
 * @date	2012-05-01
 */
public class Bill99PkiInCrytoUtils {

	/**
	 * 请求PKI加密
	 * @param mpf
	 * @param originalData
	 * @return
	 * @throws CryptoException
	 */
	public static MaSealPkiDataType seadInCryto(Mpf mpf , String originalData) throws CryptoException {
		MaSealPkiDataType seal = new MaSealPkiDataType();
		
		ICryptoService service = CryptoServiceFactory.createCryptoService();
		SealedData sealedData = service.seal(mpf,originalData.getBytes());
		
		seal.setDigitalEnvelope(new String(Base64.encodeBase64((sealedData.getDigitalEnvelope()==null?"".getBytes():sealedData.getDigitalEnvelope()))));
		seal.setEncryptedData(new String(Base64.encodeBase64(sealedData.getEncryptedData()==null?"".getBytes():sealedData.getEncryptedData())));
		seal.setOriginalData(new String(Base64.encodeBase64(sealedData.getOriginalData()==null?"".getBytes():sealedData.getOriginalData())));
		seal.setSignedData(new String(Base64.encodeBase64(sealedData.getSignedData()==null?"".getBytes():sealedData.getSignedData())));
		
		//测试日志
		Bill99PkiInCrytoUtils.logRequest(originalData, seal, sealedData);
		
		return seal;
	}
	
	/**
	 * 打印请求日志
	 * @param originalData
	 * @param sealPkiType
	 * @param sealedData
	 */
	public static void logRequest(String originalData, MaSealPkiDataType sealPkiType, SealedData sealedData){
		System.out.println("logRequest-----------------------------------");
		System.out.println("originalData:"+originalData);
		System.out.println("MaSealPkiDataType:------------");
		System.out.println(".DigitalEnvelope: "+sealPkiType.getDigitalEnvelope());
		System.out.println(".EncryptedData: "+sealPkiType.getEncryptedData());
		System.out.println(".OriginalData: "+sealPkiType.getOriginalData());
		System.out.println(".SignedData: "+sealPkiType.getSignedData());
		System.out.println("SealedData:------------");
		System.out.println(".DigitalEnvelope: "+ Bill99PkiInCrytoUtils.bytes2Str(sealedData.getDigitalEnvelope()));
		System.out.println(".EncryptedData: "+ Bill99PkiInCrytoUtils.bytes2Str(sealedData.getEncryptedData()));
		System.out.println(".OriginalData: "+ Bill99PkiInCrytoUtils.bytes2Str(sealedData.getOriginalData()));
		System.out.println(".SignedData: "+ Bill99PkiInCrytoUtils.bytes2Str(sealedData.getSignedData()));
		System.out.println("logRequest-----------------------------------");
	}
	
	/**
	 * 返回PKI验签
	 * @param mpf
	 * @param originalData
	 * @return
	 * @throws CryptoException
	 */
	public static boolean unseadInCryto(Mpf mpf , String originalData, MaSealPkiDataType seal) throws CryptoException {
		
		byte[] digitalEnvelope = Base64.decodeBase64(seal.getDigitalEnvelope().getBytes());
		byte[] encryptedData = Base64.decodeBase64(seal.getEncryptedData().getBytes());
		byte[] signedData = Base64.decodeBase64(seal.getSignedData().getBytes());
		 
		SealedData sealedData = new SealedData();
        sealedData.setDigitalEnvelope(digitalEnvelope);
        sealedData.setEncryptedData(encryptedData);
        sealedData.setSignedData(signedData);
		//获得解密对象
		ICryptoService service = CryptoServiceFactory.createCryptoService();
		UnsealedData unsealedData = service.unseal(mpf,sealedData);
		
		//测试日志
		Bill99PkiInCrytoUtils.logResponse(originalData, seal, unsealedData);
		
		 if(null != unsealedData){
            boolean VerifySignResult = unsealedData.getVerifySignResult();
        	if(VerifySignResult){
        		//签名正确时	比较原数据与解密后数据
        		if(new String(unsealedData.getDecryptedData()).equals(originalData)){
        			return true;
        		}
        	}
         }
		
		return false;
	}

	
	/**
	 * 打印返回日志
	 * @param originalData
	 * @param sealPkiType
	 * @param unsealedData
	 */
	public static void logResponse(String originalData, MaSealPkiDataType sealPkiType, UnsealedData unsealedData){
		System.out.println("logResponse-----------------------------------");
		System.out.println("originalData:"+originalData);
		System.out.println("MaSealPkiDataType:------------");
		System.out.println(".DigitalEnvelope: "+sealPkiType.getDigitalEnvelope());
		System.out.println(".EncryptedData: "+sealPkiType.getEncryptedData());
		System.out.println(".OriginalData: "+ (sealPkiType.getOriginalData()==null?"":sealPkiType.getOriginalData()));
		System.out.println(".SignedData: "+sealPkiType.getSignedData());
		System.out.println("UnsealedData:------------");
		System.out.println(".verifySignResult: "+ unsealedData.getVerifySignResult());
		System.out.println(".decryptedData: "+ Bill99PkiInCrytoUtils.bytes2Str(unsealedData.getDecryptedData()));
		System.out.println(".decryptedData: "+new String(unsealedData.getDecryptedData()));
		System.out.println("logResponse-----------------------------------");
	}
	
	
	/**
	 * 统一格式打印byte[]
	 * @param b
	 * @return
	 */
	public static String bytes2Str(byte[] b){
		if(null == b){return "";}
		StringBuffer sb = new StringBuffer("[");
		for(int i=0;i<b.length;i++){
			if( (b.length-1) == i){
				sb.append(b[i]);
			}else{
				sb.append(b[i]+",");
			}
		}
		return sb.append("]").toString();
	}
	
	/**
	 * 转换时间格式为  yyyyMMddkkmmss
	 * @param dateTime
	 * @return
	 */
	public static String toDateStr(Date date) {
		String patten2 = "yyyyMMddkkmmss";
		SimpleDateFormat df2 =new SimpleDateFormat(patten2);
		return df2.format(date).toString();
	}
	
	/**
	 * 空字符转为""
	 * @param str
	 * @return
	 */
	public static String null2String(String str) {
		return ((str == null) ? "" : str);
	}
	
	/**
	 * 空对象转为""
	 * @param obj
	 * @return
	 */
	public static String null2String(Object obj) {
		return ((obj == null) ? "" : obj.toString());
	}
}
