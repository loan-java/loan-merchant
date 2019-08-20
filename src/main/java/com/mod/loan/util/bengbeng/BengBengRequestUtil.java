package com.mod.loan.util.bengbeng;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.config.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * @ author liujianjian
 * @ date 2019/5/15 9:03
 */
@Slf4j
public class BengBengRequestUtil {

    public static String doPost(String url, String method, String bizData) throws Exception {
        return doPost(url, method, bizData, "");
    }

    public static String doPost(String url, String method, String bizData, String returnUrl) throws Exception {
        return doPost(url, buildRequestParams(method, bizData, returnUrl));
    }

    private static String doPost(String url, String reqParamsStr) throws Exception {
        String result = BengBengHttpClientUtils.sendPost(url, reqParamsStr.getBytes());
        return result;
    }

    /**
     * 构建请求参数
     */
    public static String buildRequestParams(String method, String bizData, String returnUrl) throws Exception {
        String despwd = BengBengStandardDesUtils.generateDesKey();
        RequestBengBengBean vo = new RequestBengBengBean();
        vo.setMethod(method);
        vo.setSign_type("RSA");
        vo.setBiz_data(BengBengBizDataUtil.encryptBizData(bizData, despwd));
        vo.setBiz_enc("1");
        vo.setDes_key(genDescKey(despwd));
        vo.setApp_id(Constant.bengBengRequestAppId);
        vo.setVersion("1.0");
        vo.setFormat("json");
        vo.setTimestamp(System.currentTimeMillis() + "");
        vo.setReturn_url(returnUrl);

        String reqContent = JSONObject.toJSONString(vo);
        //设置签名
        vo.setSign(BengBengSignUtil.genSign(reqContent));
        return JSONObject.toJSONString(vo);
    }

    public static String buildRequestParams(String method, String bizData) throws Exception {
        return buildRequestParams(method, bizData, "");
    }

    //生成 RSA 加密后的密钥
    public static String genDescKey(String despwd) throws Exception {
        return BengBengRSAUtils.encrypt(despwd, Constant.bengBengPublicKey);
    }

    public static class RequestBengBengBean {
        /**
         * 要请求的 API 方法名称
         */
        private String method;
        /**
         * API 请求的签名
         */
        private String sign;
        /**
         * 签名方式，默认RSA
         */
        private String sign_type;
        /**
         * 请求的业务数据，json 格式。注意是 json 格式的字符串
         */
        private String biz_data;
        /**
         * biz_data 加密方式
         * （0 不加密，1 加密:采用 DES 加密算法）
         */
        private String biz_enc;
        /**
         * RSA 加密后的密钥（biz_enc 为 1 时为必传）
         */
        private String des_key;
        /**
         * 分配给应用的唯一标识
         */
        private String app_id;
        /**
         * API 协议版本，默认值：1.0
         */
        private String version;
        /**
         * 响应格式，仅支持 json
         */
        private String format;
        /**
         * 时间戳，精确到毫秒（比如1539073086805 ）
         */
        private String timestamp;
        /**
         * 回调 url
         */
        private String return_url;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public String getBiz_data() {
            return biz_data;
        }

        public void setBiz_data(String biz_data) {
            this.biz_data = biz_data;
        }

        public String getBiz_enc() {
            return biz_enc;
        }

        public void setBiz_enc(String biz_enc) {
            this.biz_enc = biz_enc;
        }

        public String getDes_key() {
            return des_key;
        }

        public void setDes_key(String des_key) {
            this.des_key = des_key;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getReturn_url() {
            return return_url;
        }

        public void setReturn_url(String return_url) {
            this.return_url = return_url;
        }
    }
}
