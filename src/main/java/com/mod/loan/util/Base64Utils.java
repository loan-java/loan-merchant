package com.mod.loan.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;
/**
 * 
 * @author wugy
 *  2016年7月19日下午1:55:30
 *
 */
public class Base64Utils {

	
	private Base64Utils() {
		throw new Error("can't instance this tool class");
	}

	public static void main(String[] args) {
		String imageToStr = Base64Utils.getImageToStr("D:/tomcat.png");
		System.out.println(imageToStr);
		Base64Utils.GenerateStrToImage(imageToStr, "D:/tomcat1.jpg");
	}

	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	public static String getImageToStr(String path) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return encode(data);
	}

	public static boolean GenerateStrToImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] bytes = decodeBase64(imgStr);
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 编码
	 */
	public static String encode(byte[] val) {
		return DatatypeConverter.printBase64Binary(val);
	}


	/**
	 * 解码
	 */
	public static byte[] decodeBase64(String value) {
		return DatatypeConverter.parseBase64Binary(value);
	}

}