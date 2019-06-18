package com.mod.loan.util.rongze;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.config.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * @ author liujianjian
 * @ date 2019/5/15 9:03
 */
@Slf4j
public class RongZeRequestUtil {

    public static String doPost(String url, String method, String bizData) throws Exception {
        log.info("接口融泽请求开始, 方法是:{}", method);
        return doPost(url, method, bizData, "");
    }

    public static String doPost(String url, String method, String bizData, String returnUrl) throws Exception {
        return doPost(url, buildRequestParams(method, bizData, returnUrl));
    }

    private static String doPost(String url, String reqParamsStr) throws Exception {
        //大王贷款回调服务API接口URL
//        String kingplatformUrl = "http://king-platform-callback.king-test.svc.cluster.local:9090/platform/callback";
//        String reqParamsStr = "{\"app_id\":\"appid1000000\",\"biz_data\":\"pGBul5nSR6ufWBMs/VRBPmduZhorhdfG8JEvIeikTgm4pvPdo/+qeB28lHw/G+NT2ZqXRMfn51LhsMutNWCzrSC06C35jFiOkQb3b4in1U3DS0jOxeN3AGbuF/7EzwA6ThlQTm1HkMw=\",\"biz_enc\":\"1\",\"des_key\":\"MDuA8iX/SIIxJRJRn8IAJ2+OZDZbAyZCGhhMU/oG5bOK51HypaLTIo9LKyKCRwbtqh0mJOP2ZS19o3lZNFCCexxOu4lFwnRWfOes+crDF8ppb4Z90W826GiNhtN3zk8UlXI0d3epCMIXLcHg3niXJu9O+ikAo9ycgM0u2EnXyPs=\",\"format\":\"json\",\"method\":\"api.bank.result\",\"return_url\":\"\",\"sign\":\"nhab1zczyG5t2PEhK0ztmttTm+5wcHFjekreSSRBOs8c19WVkl4ajO1nCXojPqxI34AlhdTQOkb/YMnOxHX3qX9n4jZ3W2qEdCnz2QcDBhvKrAqUbCD1Wty+iPltN+u+D41h0FmPvKuculPLGKDdINMkIPQjERVs9T49L5semF4=\",\"sign_type\":\"RSA\",\"timestamp\":\"1543396830799\",\"version\":\"1.0\"}";

        String result = HttpClientUtils.sendPost(url, reqParamsStr.getBytes());
        log.info("接口融泽请求结束");
        return result;
    }

    /**
     * 构建请求参数
     */
    public static String buildRequestParams(String method, String bizData, String returnUrl) throws Exception {
        //说明：秘钥格式PKCS8，秘钥长度1024，签名方式RSA，字符集UTF-8

        //机构RSA私钥
//        String privatekey = Constant.orgPrivateKey;
//        String privatekey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEsWzW9BCMya+fTZ9ikE5PLglVG3eMhIG6ajF4NsmfjL/jBgWylbmPxvWnuzO1nnI+UVDDmJrxHuB39NIu2H1bJcjZ5o8WF7tEpnMYQ8GFQANCyOSiEQuV7A79vexGKDfyT7D+VjJLVlMhsS9Zv2bdHLi9n1UsNdgeimBO/NvQzAgMBAAECgYBD0wGzFI64LRDBpvItdaaTbHG1ZzQaz6bxRHJLZiIsm6tlWDEZwmg5ANK/0HFGenKk7TuctE2ar+eoHxTMsmBvGzctn8KSU+c5MqLiwMko4tkzoSJkaRAIO2f9Uv2rqLMHaOehzxHVgB39wIBv1HL5I1BmRjETHMR4cxmmb9w56QJBAPv2z246LDHe6ndCujcubkxYcNusGIWttSocwS3uCCGDLnrqEQ72zCmUevuSrYZFfnAT0LbA1fveP06J6Oa5pbUCQQCjwUEW3X08P2E8O2uCUWclianmQbZELaP68evNgumyOD8E3YJxDZtcTDcjrWoanE+UW31rm2ShihIEJrujMWNHAkBLIptSnGhHatjyPWS4RdFAVPM6noQlgNpQN4jnwF6OV8cJgjkaBEB3eb5+vIugSaLdmxsXFEP7OpgYPInGG8AtAkAni90/O2AqM5g05pixER2a2CMaw1XUIz2NtezfZbUwYBr//sqoqMOTR6itSgzsvkENsAaa/R0RUfnF3ODFqYCzAkEA7hH2HCE47Jy6q8tNGptbKy3Rj18KhcrLyQ4aq5KgX2wX2hNY0A5ZWJ+csYkWNgmPfbeOHj6gDwawg+ce0lZMng==";
        //机构RSA公钥
//        String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChLFs1vQQjMmvn02fYpBOTy4JVRt3jISBumoxeDbJn4y/4wYFspW5j8b1p7sztZ5yPlFQw5ia8R7gd/TSLth9WyXI2eaPFhe7RKZzGEPBhUADQsjkohELlewO/b3sRig38k+w/lYyS1ZTIbEvWb9m3Ry4vZ9VLDXYHopgTvzb0MwIDAQAB";

        String despwd = StandardDesUtils.generateDesKey();

        RequestRongZeBean vo = new RequestRongZeBean();
        vo.setMethod(method);
        vo.setSign_type("RSA");
        vo.setBiz_data(BizDataUtil.encryptBizData(bizData, despwd));
        vo.setBiz_enc("1");
        vo.setDes_key(genDescKey(despwd));
        vo.setApp_id(Constant.rongZeRequestAppId);
        vo.setVersion("1.0");
        vo.setFormat("json");
        vo.setTimestamp(System.currentTimeMillis() + "");
        vo.setReturn_url(returnUrl);

        String reqContent = JSONObject.toJSONString(vo);
//        Map<String, String> paramMap = JSONObject.parseObject(reqContent, Map.class);
//
//        StringBuffer sbfStr = new StringBuffer();
//        List<String> list = new ArrayList<String>(paramMap.keySet());
//        Collections.sort(list); //参数名ASCII码从小到大排序（字典序）
//        for (String key : list) {
//            if (StringUtils.isNotBlank(paramMap.get(key))) {
//                sbfStr.append(key + "=" + paramMap.get(key) + "&");
//            }
//        }
//        String pendVertContent = sbfStr.toString().substring(0, sbfStr.length() - 1);
//        System.out.println("待生成签名的字符串：" + pendVertContent);
//        String sign = RSAUtils.sign(pendVertContent, privatekey);
//        System.out.println("签名sign:" + sign);

        //设置签名
        vo.setSign(SignUtil.genSign(reqContent));
        return JSONObject.toJSONString(vo);
    }

    public static String buildRequestParams(String method, String bizData) throws Exception {
        return buildRequestParams(method, bizData, "");
    }

    //生成 RSA 加密后的密钥
    public static String genDescKey(String despwd) throws Exception {
        return RSAUtils.encrypt(despwd, Constant.rongZePublicKey);
    }

    public static class RequestRongZeBean {
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
