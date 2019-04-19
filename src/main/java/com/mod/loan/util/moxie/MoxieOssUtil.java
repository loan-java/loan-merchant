package com.mod.loan.util.moxie;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import com.mod.loan.config.Constant;

public class MoxieOssUtil {

	private static final Logger logger = LoggerFactory.getLogger(MoxieOssUtil.class);

	/**
	 * 获取通讯录信息
	 *
	 * @param env
	 *            环境 dev or online
	 * @param taskId
	 *            通讯录taskId
	 * @return
	 */
	public static String addressList(String env, String taskId) {
		String data = null;
		try {
			String savePath = Constant.moxie_address_list + "/" + taskId;
			OSSObject ossObject = getOssObject(env, Constant.moxie_bucket_name, savePath);
			data = IOUtils.readStreamAsString(ossObject.getObjectContent(), "utf-8");
		} catch (Exception e) {
			logger.error("获取通讯录信息异常,taskId={}", taskId);
		}
		return StringUtils.isBlank(data) ? "" : data;
	}

	/**
	 * 获取运营商聚信立报告数据
	 * 
	 * @param env
	 *            环境 dev or online
	 * @param taskId
	 *            魔蝎taskid
	 * @return
	 */
	public static String mobileJxlReport(String env, String taskId) {
		String data = null;
		try {
			String savePath = Constant.moxie_mobile_jxl + "/" + taskId;
			OSSObject ossObject = getOssObject(env, Constant.moxie_bucket_name, savePath);
			data = uncompress(ossObject.getObjectContent());
		} catch (IOException e) {
			logger.error("获取运营商聚信立报告数据异常,taskId={}", taskId);
		}
		return StringUtils.isBlank(data) ? "" : data;
	}

	/**
	 * 获取运营商原始数据
	 * 
	 * @param env
	 *            环境 dev or online
	 * @param taskId
	 *            魔蝎taskid
	 * @return
	 */
	public static String mobileMxdata(String env, String taskId) {
		String data = null;
		try {
			String savePath = Constant.moxie_mobile_mxdata + "/" + taskId;
			OSSObject ossObject = getOssObject(env, Constant.moxie_bucket_name, savePath);
			data = uncompress(ossObject.getObjectContent());
		} catch (IOException e) {
			logger.error("获取运营商原始数据异常,taskId={}", taskId);
		}
		return StringUtils.isBlank(data) ? "" : data;
	}

	/**
	 * 获取支付宝原始数据
	 * 
	 * @param env
	 *            环境 dev or online
	 * @param taskId
	 *            魔蝎taskid
	 * @return
	 */
	public static String zfbData(String env, String taskId) {
		String data = null;
		try {
			String savePath = Constant.moxie_zfb_data + "/" + taskId;
			OSSObject ossObject = getOssObject(env, Constant.moxie_bucket_name, savePath);
			data = IOUtils.readStreamAsString(ossObject.getObjectContent(), "utf-8");
		} catch (Exception e) {
			logger.error("获取支付宝原始数据异常,taskId={}", taskId);
		}
		return StringUtils.isBlank(data) ? "" : data;
	}

	/**
	 * 获取魔杖报告
	 *
	 * @param env
	 *            环境 dev or online
	 * @param taskId
	 *            运营商taskId
	 * @return
	 */
	public static String magicWandReport(String env, String taskId) {
		String data = null;
		try {
			String savePath = Constant.moxie_magic_wand + "/" + taskId;
			OSSObject ossObject = getOssObject(env, Constant.moxie_bucket_name, savePath);
			data = IOUtils.readStreamAsString(ossObject.getObjectContent(), "utf-8");
		} catch (Exception e) {
			logger.error("获取魔杖报告异常,taskId={}", taskId);
		}
		return StringUtils.isBlank(data) ? "" : data;
	}

	private static String endPointUrl(String env) {
		if ("dev".equals(env)) {
			return Constant.endpoint_out;
		}
		return Constant.endpoint_in;
	}

	private static String uncompress(InputStream gzippedResponse) throws IOException {
		InputStream decompressedResponse = new GZIPInputStream(gzippedResponse);
		Reader reader = new InputStreamReader(decompressedResponse, "UTF-8");
		StringWriter writer = new StringWriter();
		char[] buffer = new char[10240];
		for (int length = 0; (length = reader.read(buffer)) > 0;) {
			writer.write(buffer, 0, length);
		}
		writer.close();
		reader.close();
		decompressedResponse.close();
		gzippedResponse.close();
		return writer.toString();
	}

	/**
	 * 根据路径获取oss文件对象
	 * 
	 * @param env
	 * @param bucketName
	 * @param savePath
	 * @return
	 */
	private static OSSObject getOssObject(String env, String bucketName, String savePath) {
		OSSClient ossClient = null;
		ossClient = new OSSClient(endPointUrl(env), Constant.accesskey_id, Constant.access_key_secret);
		OSSObject oSSObject = ossClient.getObject(bucketName, savePath);
		return oSSObject;
	}

}
