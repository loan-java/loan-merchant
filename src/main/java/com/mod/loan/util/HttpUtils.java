package com.mod.loan.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mod.loan.config.Constant;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * http请求类
 * 
 * @author wugy 2016年7月11日下午5:00:35
 *
 */
public final class HttpUtils {

	public final static String BAIDU_IP_API = "http://api.map.baidu.com/location/ip?ak=%s&sn=%s&ip=%s";
	public final static String BAIDU_AK = "ak";
	public final static String BAIDU_SN = "sn";

	private HttpUtils() {
		throw new Error("can't instance this tool class");
	}

	/**
	 * 
	 * 功能描述：获取真实的IP地址
	 * 
	 * @param request
	 */
	public static String getIpAddr(HttpServletRequest request, String split) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (!StringUtils.isEmpty(ip) && ip.contains(",")) {
			String[] ips = ip.split(",");
			ip = ips[ips.length - 1];
		}
		// 转换IP 格式
		if (!StringUtils.isEmpty(ip)) {
			ip = ip.replace(".", split);
		}
		return ip;
	}

	public static String doPost(String reqUrl, Map<String, String> parameters) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = sendPost(reqUrl, parameters);
			String responseContent = getContent(urlConn);
			return responseContent.trim();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
				urlConn = null;
			}
		}
	}

	public static String doPost(String reqUrl, String data) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = sendPost(reqUrl, data);
			String responseContent = getContent(urlConn);
			return responseContent.trim();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
				urlConn = null;
			}
		}
	}

	public static String doUploadFile(String reqUrl, Map<String, String> parameters, String fileParamName,
			String filename, String contentType, byte[] data) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = sendFormdata(reqUrl, parameters, fileParamName, filename, contentType, data);
			String responseContent = new String(getBytes(urlConn));
			return responseContent.trim();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
	}

	private static HttpURLConnection sendFormdata(String reqUrl, Map<String, String> parameters, String fileParamName,
			String filename, String contentType, byte[] data) {
		HttpURLConnection urlConn = null;
		try {
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(5000);// （单位：毫秒）jdk
			urlConn.setReadTimeout(5000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			urlConn.setDoOutput(true);

			urlConn.setRequestProperty("connection", "keep-alive");

			String boundary = "-----------------------------114975832116442893661388290519"; // 分隔符
			urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			boundary = "--" + boundary;
			StringBuffer params = new StringBuffer();
			if (parameters != null) {
				for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
					String name = iter.next();
					String value = parameters.get(name);
					params.append(boundary + "\r\n");
					params.append("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n");
					// params.append(URLEncoder.encode(value, "UTF-8"));
					params.append(value);
					params.append("\r\n");
				}
			}

			StringBuilder sb = new StringBuilder();
			// sb.append("\r\n");
			sb.append(boundary);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data; name=\"" + fileParamName + "\"; filename=\"" + filename
					+ "\"\r\n");
			sb.append("Content-Type: " + contentType + "\r\n\r\n");
			byte[] fileDiv = sb.toString().getBytes();
			byte[] endData = ("\r\n" + boundary + "--\r\n").getBytes();
			byte[] ps = params.toString().getBytes();

			OutputStream os = urlConn.getOutputStream();
			os.write(ps);
			os.write(fileDiv);
			os.write(data);
			os.write(endData);

			os.flush();
			os.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return urlConn;
	}

	private static String getContent(HttpURLConnection urlConn) {
		try {
			String responseContent = null;
			InputStream in = urlConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
			return responseContent;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static byte[] getBytes(HttpURLConnection urlConn) {
		try {
			InputStream in = urlConn.getInputStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0; (i = in.read(buf)) > 0;)
				os.write(buf, 0, i);
			in.close();
			return os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static HttpURLConnection sendPost(String reqUrl, Map<String, String> parameters) {
		String params = generatorParamString(parameters);
		// System.out.println(params);
		return sendPost(reqUrl, params);
	}

	private static HttpURLConnection sendPost(String reqUrl, String params) {
		HttpURLConnection urlConn = null;
		try {
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(5000);// （单位：毫秒）jdk
			urlConn.setReadTimeout(20000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			urlConn.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlConn.getOutputStream(), "utf-8");
			out.append(params, 0, params.length());
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return urlConn;
	}

	/**
	 * 将parameters中数据转换成用"&"链接的http请求参数形式
	 * 
	 * @param parameters
	 * @return
	 */
	public static String generatorParamString(Map<String, String> parameters) {
		StringBuffer params = new StringBuffer();
		if (parameters != null) {
			for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String value = parameters.get(name);
				params.append(name + "=");
				try {
					params.append(URLEncoder.encode(value, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e.getMessage(), e);
				} catch (Exception e) {
					String message = String.format("'%s'='%s'", name, value);
					throw new RuntimeException(message, e);
				}
				if (iter.hasNext())
					params.append("&");
			}
		}
		return params.toString();
	}

	/**
	 * 
	 * @param link
	 * @param charset
	 * @return
	 */
	public static String doGet(String link, String charset) {
		try {
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0; (i = in.read(buf)) > 0;) {
				out.write(buf, 0, i);
			}
			out.flush();
			String s = new String(out.toByteArray(), charset);
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * UTF-8编码
	 * 
	 * @param link
	 * @return
	 */
	public static String doGet(String link) {
		return doGet(link, "UTF-8");
	}

	public static int getIntResponse(String link) {
		String str = doGet(link);
		return Integer.parseInt(str.trim());
	}

	public static String executeGet(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		String msg = "";
		try {
			httpClient.executeMethod(method);
			msg = method.getQueryString();
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}

	public static String executeMethod(HttpMethodBase method) {
		HttpClient httpClient = new HttpClient();
		String msg = "";
		try {
			httpClient.executeMethod(method);
			msg = method.getResponseBodyAsString();
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}

	/**
	 * 爬取页面
	 * 
	 * @param url
	 * @return
	 */
	public static String getContent(String url, String charset) {
		HttpClientBuilder build = HttpClients.custom();
		// 默认重试三次，可以使用有参构造函数自定义重试次数
		HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler();
		CloseableHttpClient client = build.setRetryHandler(retryHandler).build();
		String content = null;
		HttpGet get = new HttpGet(url);

		try {
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, charset);
		} catch (Exception e) {
			System.err.println("下载页面失败......");
			e.printStackTrace();
		}
		return content.trim();
	}

	/**
	 * 爬取页面
	 */
	public static String getHtml(String url, String charset) {
		HttpClient httpClient = new HttpClient();
		InputStream input = null;
		String s = null;
		try {
			// 得到 post 方法
			GetMethod getMethod = new GetMethod(url);
			// 执行，返回状态码
			int statusCode = httpClient.executeMethod(getMethod);
			// 针对状态码进行处理
			// 简单起见，只处理返回值为 200 的状态码
			if (statusCode == HttpStatus.SC_OK) {
				input = getMethod.getResponseBodyAsStream();
				// 获得文件输出流
				BufferedInputStream in = new BufferedInputStream(input);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				for (int i = 0; (i = in.read(buf)) > 0;) {
					out.write(buf, 0, i);
				}
				out.flush();
				in.close();
				s = new String(out.toByteArray(), charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 发送rest风格请求
	 * 
	 * @param URL
	 *            请求地址
	 * @param data
	 *            包体内容
	 * @param METHOD
	 *            请求方式
	 * @return
	 */
	public static String restRequest(final String URL, final String data, final String METHOD) {
		String result = null;
		HttpURLConnection conn = null;
		try {
			URL target = new URL(URL);
			conn = (HttpURLConnection) target.openConnection();
			conn.setRequestMethod(METHOD);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);// 是否输入参数
			byte[] bypes = data.getBytes();
			conn.getOutputStream().write(bypes);// 输入参数
			if (200 != conn.getResponseCode()) {
				throw new RuntimeException("failed, error code is " + conn.getResponseCode());
			}
			byte[] temp = new byte[conn.getInputStream().available()];
			if (conn.getInputStream().read(temp) != -1) {
				result = new String(temp, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return result;
	}

	public static String getIPAddressBaiDu(String ip) {
		String result = "";
		if (ip == null || ip.isEmpty()) {
			return result;
		}
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(BAIDU_AK, Constant.baidu_ak_value);
		parameters.put(BAIDU_SN, Constant.baidu_sn_value);
		parameters.put("ip", ip.trim());
		String body = HttpUtils.doPost(BAIDU_IP_API, parameters);
		JSONObject json = JSONObject.parseObject(body);
		return json.getInteger("status") == 0 ? json.getJSONObject("content").getString("address") : result;
	}

}
