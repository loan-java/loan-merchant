package com.mod.loan.util;

import java.io.InputStream;

import com.mod.loan.config.Constant;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;

public class AliOssStaticUtil {

	private static Logger logger = LoggerFactory.getLogger(AliOssStaticUtil.class);

	/**
	 * oss向static-ym上传文件，按照 yyyy/MMdd 格式存储
	 * 
	 * @param env
	 * @param inputStream
	 * @param fileName
	 * @return 路径
	 */
	public static String ossUploadFile(String env, InputStream inputStream, String fileName) {
		OSSClient ossClient = new OSSClient(endPointUrl(env), Constant.accesskey_id, Constant.access_key_secret);
		DateTime currentDate = TimeUtils.getDateTime();
		String savePath = currentDate.getYear() + "/" + currentDate.toString("MMdd") + "/" + fileName;
		try {
			ossClient.putObject(Constant.bucket_name, savePath, inputStream);
		} catch (Exception e) {
			logger.error("oss文件上传失败。");
			throw new RuntimeException(e);
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
		return savePath;
	}

	/**
	 * 根据环境切换上传地址
	 * 
	 * @param env
	 * @return
	 */
	public static String endPointUrl(String env) {
		if ("dev".equals(env)) {
			return Constant.endpoint_out;
		}
		return Constant.endpoint_in;
	}

}
