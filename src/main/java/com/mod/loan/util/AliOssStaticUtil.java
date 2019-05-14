package com.mod.loan.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aliyun.oss.model.OSSObject;
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


    /**
     * 获取oss上的指定文件
     *
     * @param objectName
     * @param bucketName
     * @return
     */
    public static String ossGetFile(String objectName, String bucketName) {
        StringBuffer stringBuffer = new StringBuffer();
        // 创建OSSClient实例。
        OSSClient ossClient = null;
        try {
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = Constant.endpoint_out;
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = Constant.accesskey_id;
            String accessKeySecret = Constant.access_key_secret;
            // 创建OSSClient实例。
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 判断文件是否存在。doesObjectExist还有一个参数isOnlyInOSS，如果为true则忽略302重定向或镜像；如果为false，则考虑302重定向或镜像。
            boolean found = ossClient.doesObjectExist(bucketName, objectName);
            logger.error("判断文件objectName=【" + objectName + "】在bucketName=【" + bucketName + "】是否存在【" + found + "】");
            if (!found) {
                throw new RuntimeException("当前查询数据不存在");
            }
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            // 读取文件内容。
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                stringBuffer.append(line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            reader.close();
        }catch (Exception e) {
            logger.error("获取oss上的指定文件失败：", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
        return stringBuffer.toString();
    }


}
