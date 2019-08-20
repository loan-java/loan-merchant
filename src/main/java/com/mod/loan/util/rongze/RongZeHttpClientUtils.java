package com.mod.loan.util.rongze;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @包名 com.sinaif.common.utils
 * @类名: RongZeHttpClientUtils
 * @描述: http client请求工具类 @作者： 李运期 @创建时间： 2016年9月21日 上午10:41:19
 * 
 */
public class RongZeHttpClientUtils {

	/**
	 * 默认的编码,解决中文乱码
	 */
	public static String defaultEncode = "UTF-8";

	/**
	 * 请求网络数据
	 * 
	 * @param urlAddress
	 * @param datas
	 * @return
	 */
	public static String sendPost(String urlAddress, byte[] datas) throws Exception {
		URL url = null;
		HttpURLConnection con = null;
		StringBuffer result = new StringBuffer();
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "keep-alive");// 维持长连接
			con.setRequestProperty("Content-Length", String.valueOf(datas.length));// 维持长连接
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");// 维持长连接
			con.getOutputStream().write(datas, 0, datas.length);
			con.getOutputStream().flush();

			// 根据ResponseCode判断连接是否成功
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				System.out.println("sendPost Error===" + responseCode);
			} else {
				System.out.println("sendPost success!" + responseCode);
			}

			result.append(getContent(con.getInputStream(), "UTF-8"));
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (con != null) {
					con.disconnect();
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return result.toString();
	}

	/**
	 * 获取文件内容
	 * 
	 * @param in
	 * @param encode
	 * @return
	 */
	public static String getContent(InputStream in, String encode) {
		String mesage = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			int len = 0;
			byte[] data = new byte[1024];
			while ((len = in.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
			mesage = new String(outputStream.toByteArray(), encode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mesage;
	}

}
