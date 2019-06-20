package com.mod.loan.util.chanpay;

import com.mod.loan.config.Constant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.time.DateFormatUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.*;


public class ChanpayGateway {


    /**
     * 畅捷支付平台公钥
     */
    private static String MERCHANT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPq3oXX5aFeBQGf3Ag/86zNu0VICXmkof85r+DDL46w3vHcTnkEWVbp9DaDurcF7DMctzJngO0u9OG1cb4mn+Pn/uNC1fp7S4JH4xtwST6jFgHtXcTG9uewWFYWKw/8b3zf4fXyRuI/2ekeLSstftqnMQdenVP7XCxMuEnnmM1RwIDAQAB";//生产环境公钥

    /**
     * 生产环境 测试商户号私钥
     */
    private static String MERCHANT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANB5cQ5pf+QHF9Z2+DjrAXstdxQHJDHyrni1PHijKVn5VHy/+ONiEUwSd5nx1d/W+mtYKxyc6HiN+5lgWSB5DFimyYCiOInh3tGQtN+pN/AtE0dhMh4J9NXad0XEetLPRgmZ795O/sZZTnA3yo54NBquT19ijYfrvi0JVf3BY9glAgMBAAECgYBFdSCox5eXlpFnn+2lsQ6mRoiVAKgbiBp/FwsVum7NjleK1L8MqyDOMpzsinlSgaKfXxnGB7UgbVW1TTeErS/iQ06zx3r4CNMDeIG1lYwiUUuguIDMedIJxzSNXfk65Bhps37lm129AE/VnIecpKxzelaUuzyGEoFWYGevwc/lQQJBAPO0mGUxOR/0eDzqsf7ehE+Iq9tEr+aztPVacrLsEBAwqOjUEYABvEasJiBVj4tECnbgGxXeZAwyQAJ5YmgseLUCQQDa/dgviW/4UMrY+cQnzXVSZewISKg/bv+nW1rsbnk+NNwdVBxR09j7ifxg9DnQNk1Edardpu3z7ipHDTC+z7exAkAM5llOue1JKLqYlt+3GvYr85MNNzSMZKTGe/QoTmCHStwV/uuyN+VMZF5cRcskVwSqyDAG10+6aYqD1wMDep8lAkBQBoVS0cmOF5AY/CTXWrht1PsNB+gbzic0dCjkz3YU6mIpgYwbxuu69/C3SWg7EyznQIyhFRhNlJH0hvhyMhvxAkEAuf7DNrgmOJjRPcmAXfkbaZUf+F4iK+szpggOZ9XvKAhJ+JGd+3894Y/05uYYRhECmSlPv55CBAPwd8VUsSb/1w==";

    /**
     * 编码类型
     */
    public static String charset = "UTF-8";

    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取钱包的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值 如：buildRequest("",
     * "",sParaTemp)
     *
     * @param sParaTemp 请求参数数组
     * @return 钱包处理结果
     * @throws Exception
     */
    public static String buildRequest(Map<String, String> sParaTemp, String signType, String key, String inputCharset,
                                      String gatewayUrl) throws Exception {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp, signType, key, inputCharset);
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        // 设置编码集
        request.setCharset(inputCharset);
        request.setMethod(HttpRequest.METHOD_POST);
        request.setParameters(generatNameValuePair(createLinkRequestParas(sPara), inputCharset));
        request.setUrl(gatewayUrl);
        System.out.println(gatewayUrl + "" + httpProtocolHandler.toString(generatNameValuePair(createLinkRequestParas(sPara), inputCharset)));
        if (sParaTemp.get("Service").equalsIgnoreCase("nmg_quick_onekeypay") || sParaTemp.get("Service").equalsIgnoreCase("nmg_nquick_onekeypay")) {
            return null;
        }

        // 返回结果处理
        HttpResponse response = httpProtocolHandler.execute(request, null, null);
        if (response == null) {
            return null;
        }
        String strResult = response.getStringResult();

        return strResult;
    }

    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取钱包的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值 如：buildRequest("",
     * "",sParaTemp)
     *
     * @param sParaTemp 请求参数数组
     * @return 钱包处理结果
     * @throws Exception
     */
    public static Object buildRequests(Map<String, String> sParaTemp,
                                       String signType, String key, String inputCharset, String gatewayUrl)
            throws Exception {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp, signType, key,
                inputCharset);
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
                .getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        // 设置编码集
        request.setCharset(inputCharset);
        request.setMethod(HttpRequest.METHOD_POST);
        request.setParameters(generatNameValuePair(
                createLinkRequestParas(sPara), inputCharset));
        request.setUrl(gatewayUrl);
        HttpResponse response = httpProtocolHandler
                .execute(request, null, null);
        if (response == null) {
            return null;
        }

        byte[] byteResult = response.getByteResult();
        if (byteResult.length > 1024) {
            return byteResult;
        } else {
            return response.getStringResult();
        }

    }

    @SuppressWarnings("unused")
    private static Map convertMap(Map<?, ?> parameters, String InputCharset)
            throws ArrayIndexOutOfBoundsException, UnsupportedEncodingException, IllegalArgumentException {
        Map<String, String> formattedParameters = new HashMap<String, String>(parameters.size());
        for (Map.Entry<?, ?> entry : parameters.entrySet()) {
            if (entry.getValue() == null || Array.getLength(entry.getValue()) == 0) {
                formattedParameters.put((String) entry.getKey(), null);
            } else {
                String value = new String(((String) Array.get(entry.getValue(), 0)).getBytes(InputCharset), charset);

            }
        }
        return formattedParameters;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     *
     * @param properties MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties, String charset)
            throws Exception {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            // nameValuePair[i++] = new NameValuePair(entry.getKey(),
            // URLEncoder.encode(entry.getValue(),charset));
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }
        return nameValuePair;
    }

    /**
     * 生成要请求参数数组
     *
     * @param sParaTemp    请求前的参数数组
     * @param signType     RSA
     * @param key          商户自己生成的商户私钥
     * @param inputCharset UTF-8
     * @return 要请求的参数数组
     * @throws Exception
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String signType, String key,
                                                       String inputCharset) throws Exception {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = "";
        if ("MD5".equalsIgnoreCase(signType)) {
            mysign = buildRequestByMD5(sPara, key, inputCharset);
        } else if ("RSA".equalsIgnoreCase(signType)) {
            mysign = buildRequestByRSA(sPara, key, inputCharset);
        }
        // 签名结果与签名方式加入请求提交参数组中
//        System.out.println("Sign:" + mysign);
        sPara.put("Sign", mysign);
        sPara.put("SignType", signType);

        return sPara;
    }

    /**
     * 生成MD5签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByMD5(Map<String, String> sPara, String key, String inputCharset)
            throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = MD5.sign(prestr, key, inputCharset);
        return mysign;
    }

    /**
     * 生成RSA签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByRSA(Map<String, String> sPara, String privateKey, String inputCharset)
            throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = RSA.sign(prestr, privateKey, inputCharset);
        return mysign;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static Map<String, String> createLinkRequestParas(Map<String, String> params) {
        Map<String, String> encodeParamsValueMap = new HashMap<String, String>();
        List<String> keys = new ArrayList<String>(params.keySet());
        String charset = params.get("InputCharset");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value;
            try {
                value = URLEncoder.encode(params.get(key), charset);
                encodeParamsValueMap.put(key, value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return encodeParamsValueMap;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @param encode 是否需要urlEncode
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean encode) {

        params = paraFilter(params);

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        String charset = params.get("InputCharset");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (encode) {
                try {
                    value = URLEncoder.encode(value, charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("Sign") || key.equalsIgnoreCase("SignType") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    public String gatewayPost(Map<String, String> origMap) throws Exception {
        return this.gatewayPost(origMap, charset, Constant.chanpayOrgPrivateKey);
    }

    /**
     * 向测试服务器发送post请求
     *
     * @param origMap              参数map
     * @param MERCHANT_PRIVATE_KEY 私钥
     * @throws Exception
     */
    public String gatewayPost(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) throws Exception {
        String result = "";
        String urlStr = Constant.chanpayApiGateway + "?";
//            String urlStr = "https://pay.chanpay.com/mag-unify/gateway/receiveOrder.do?";// 测试环境地址，上生产后需要替换该地址
//            Map<String, String> sPara = buildRequestPara(origMap, "RSA", MERCHANT_PRIVATE_KEY, charset);
        result = buildRequest(origMap, "RSA", ChanpayGateway.MERCHANT_PRIVATE_KEY, charset,
                urlStr);
        return result;
    }

    /**
     * 向测试服务器发送post请求
     *
     * @param origMap              参数map
     * @param charset              编码字符集
     * @param MERCHANT_PRIVATE_KEY 私钥
     */
    public Object gatewayPosts(Map<String, String> origMap, String charset,
                               String MERCHANT_PRIVATE_KEY) {
        try {
            String urlStr = "https://pay.chanpay.com/mag-unify/gateway/receiveOrder.do?";
            // String urlStr =
            // "https://cpay.chanpay.com/mag-unify/gateway/receiveOrder.do?";
            Map<String, String> sPara = buildRequestPara(origMap, "RSA",
                    MERCHANT_PRIVATE_KEY, charset);
            System.out.println(urlStr + createLinkString(sPara, true));
            Object obj = buildRequests(origMap, "RSA", MERCHANT_PRIVATE_KEY,
                    charset, urlStr);
            System.out.println(obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密，部分接口，有参数需要加密
     *
     * @param src       原值
     * @param publicKey 畅捷支付发送的平台公钥
     * @return RSA加密后的密文
     */
    public String encrypt(String src, String publicKey) {
        return encrypt(src, publicKey, charset);
    }

    public String encrypt(String src, String publicKey, String charset) {
        try {
            byte[] bytes = RSA.encryptByPublicKey(src.getBytes(charset), publicKey);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encrypt(String src) {
        return encrypt(src, Constant.chanpayPublicKey, charset);
    }

    /**
     * 异步通知验签仅供参考
     */
    public void notifyVerify() {

        String sign = "kkqC/K3COMiQpugQj1Dtqw7568Y7UDsDTY9aihmurROXSpy+BaFyfz5o8PHyUM1opW3rMXCTIH21spjnsadH1Qsl5IrymJnG1M79nQ4cGgVsOsqoAe714RNHqXIDrGw49vpXuFbxFi7Ey4Yhd2gQDmuBYWaj0hecLWlWtd93slM=";

        Map<String, String> paramMap = new HashMap<String, String>();
        //新网银

        paramMap.put("notify_id", "5958170d60b14bfe9dd06a51af6b5532");
        paramMap.put("notify_type", "trade_status_sync"); //代收付与此不同
        paramMap.put("notify_time", "20170901142633");
        paramMap.put("_input_charset", "UTF-8");
        paramMap.put("version", "1.0");
        paramMap.put("outer_trade_no", "20170901001");
        paramMap.put("inner_trade_no", "101150424305443607560");
        paramMap.put("trade_status", "TRADE_SUCCESS");
        paramMap.put("trade_amount", "1.00");
        paramMap.put("gmt_create", "20170901142633");
        paramMap.put("gmt_payment", "20170901142633");
        paramMap.put("extension", "{}");


        //扫码、快捷
//		paramMap.put("notify_id", "87e8e322cc9b4dba8fc2f0e8010a1382");
//		paramMap.put("notify_type", "trade_status_sync"); //代收付与此不同
//		paramMap.put("notify_time", "20170807233141");
//		paramMap.put("_input_charset", "UTF-8");
//		paramMap.put("version", "1.0");
//		paramMap.put("outer_trade_no", "17080720052853968889");
//		paramMap.put("inner_trade_no", "101150210752881582798");
//		paramMap.put("trade_status", "TRADE_SUCCESS");
//		paramMap.put("trade_amount", "0.01");
//		paramMap.put("gmt_create", "20170807200534");
//		paramMap.put("gmt_payment", "20170807200534");
//		paramMap.put("extension", "{\"BANK_RET_DATA\":\"{'bank_type':'CFT','fee_type':'CNY','is_subscribe':'N','openid':'oMJGHs2wAz41X5GjYp4bPbcuB-EU','out_trade_no':'SG102946308070000040358','out_transaction_id':'4001502001201708075023175339','pay_result':'0','result_code':'0','status':'0','sub_appid':'wxfa2f613ed691411f','sub_is_subscribe':'Y','sub_openid':'os7Olwggpu_x6urLCdMh6uJseiUI','time_end':'20170807200533','total_fee':'1','transaction_id':'299540006994201708072263952157'}\"}");


        //代付
//		paramMap.put("uid", "");
//		paramMap.put("notify_time", "20170802175829");
//		paramMap.put("notify_id", "6c923350499747eeb0012c967e5325a6");
//		paramMap.put("notify_type", "withdrawal_status_sync"); 
//		paramMap.put("_input_charset", "UTF-8");
//		paramMap.put("version", "1.0");		
//		paramMap.put("outer_trade_no", "Y5372126986139229223");
//		paramMap.put("inner_trade_no", "102150166789473238683");
//		paramMap.put("withdrawal_status", "WITHDRAWAL_SUCCESS");
//		paramMap.put("withdrawal_amount", "0.01");
//		paramMap.put("gmt_withdrawal", "20170802175821");
//		paramMap.put("return_code", "S0001");
//		paramMap.put("fail_reason", "交易成功");	


        //代扣
//		paramMap.put("notify_time", "20170803000841");
//		paramMap.put("notify_id", "64ac64bed1444554a195b52ea105cefa");
//		paramMap.put("notify_type", "trade_status_sync"); 
//		paramMap.put("_input_charset", "UTF-8");
//		paramMap.put("version", "1.0");	
//		paramMap.put("outer_trade_no", "15016560129750|359469");
//		paramMap.put("inner_trade_no", "101150165603996435603");
//
//		paramMap.put("trade_status", "TRADE_SUCCESS");
//		paramMap.put("trade_amount", "100.00");
//		paramMap.put("gmt_create", "20170802144046");
//		paramMap.put("gmt_payment", "20170802144046");
//		paramMap.put("extension", "{\"apiResultMsg\":\"交易成功\",\"apiResultcode\":\"S\",\"channelTransTime\":\"20170802\",\"instPayNo\":\"DI111353501080200743557\",\"paymentSeqNo\":\"20170802FI031263767\",\"unityResultCode\":\"S0001\",\"unityResultMessage\":\"交易成功\"}");
//		

        String text = createLinkString(paramMap, false);
        System.out.println("ori_text:" + text);
        try {
            System.out.println(RSA.verify(text, sign, MERCHANT_PUBLIC_KEY,
                    charset));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 公共请求参数设置
     */
    public Map<String, String> setCommonMap(Map<String, String> origMap) {
        // 2.1 基本参数
        origMap.put("Version", "1.0");
        origMap.put("PartnerId", Constant.chanpayMerchantNo);//生产环境测试商户号

        origMap.put("InputCharset", charset);// 字符集
        origMap.put("TradeDate", DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd"));// 商户请求时间
        origMap.put("TradeTime", DateFormatUtils.format(System.currentTimeMillis(), "HHmmss"));// 商户请求时间
        origMap.put("Memo", null);
        return origMap;
    }

    /**
     * 2.1 鉴权绑卡  nmg_api_auth_req；商户采集方式，
     */
    public void nmg_biz_api_auth_req() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        origMap = setCommonMap(origMap);
        // 2.1 鉴权绑卡 api 业务参数
        origMap.put("Service", "nmg_biz_api_auth_req");// 鉴权绑卡的接口名(商户采集方式)

        String trxId = Long.toString(System.currentTimeMillis());
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("ExpiredTime", "90m");// 订单有效期
        origMap.put("MerUserId", "0000011");// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("BkAcctTp", "01");// 卡类型（00 – 银行贷记卡;01 – 银行借记卡;）
        origMap.put("BkAcctNo", this.encrypt("6212261202004578077", MERCHANT_PUBLIC_KEY, charset));// 卡号
        //System.out.println(this.encrypt("621483011*******", MERCHANT_PUBLIC_KEY, charset));
        origMap.put("IDTp", "01");// 证件类型 （目前只支持身份证 01：身份证）
        origMap.put("IDNo", this.encrypt("111111", MERCHANT_PUBLIC_KEY, charset));// 证件号
        //System.out.println(this.encrypt("13010*********", MERCHANT_PUBLIC_KEY, charset));
        origMap.put("CstmrNm", this.encrypt("XXXX", MERCHANT_PUBLIC_KEY, charset));// 持卡人姓名
        origMap.put("MobNo", this.encrypt("15867133397", MERCHANT_PUBLIC_KEY, charset));// 银行预留手机号
        //信用卡
//		origMap.put("CardCvn2", "004");// cvv2码
//		origMap.put("CardExprDt", "09/21");// 有效期


//		origMap.put("NotifyUrl", "http://dev.chanpay.com/receive.php");// 异步通知url
        origMap.put("SmsFlag", "1");
        origMap.put("Extension", "");
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.2 鉴权绑卡 nmg_page_api_auth_req；前台模式
     */
    public void nmg_page_api_auth_req() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        origMap = setCommonMap(origMap);
        // 2.1 鉴权绑卡 api 业务参数
        origMap.put("Service", "nmg_page_api_auth_req");// 鉴权绑卡的接口名(前台模式)；
        //String trxId = "201756796880882";
        String trxId = Long.toString(System.currentTimeMillis());

        origMap.put("TrxId", trxId);// 订单号
        origMap.put("ExpiredTime", "90m");// 订单有效期
        origMap.put("MerUserId", "zyr003");// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("NotifyUrl", "http://dev.chanpay.com/receive.php");// 异步通知url
        origMap.put("ReturnUrl", "http://www.baidu.com");// 回跳地址，可空
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.3 鉴权绑卡确认 api nmg_api_auth_sms
     */
    public void nmg_api_auth_sms() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_auth_sms");// 鉴权绑卡确认的接口名
        // 2.1 鉴权绑卡  业务参数
        String trxId = "201756796897";
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("OriAuthTrxId", "1501123356102");// 原鉴权绑卡订单号
        origMap.put("SmsCode", "499426");// 鉴权短信验证码
        origMap.put("NotifyUrl", "http://dev.chanpay.com/receive.php");// 异步通知地址
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.4 支付请求接口 api nmg_biz_api_quick_payment
     */

    private void nmg_biz_api_quick_payment() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_biz_api_quick_payment");// 支付的接口名
        // 2.2 业务参数
        String trxId = Long.toString(System.currentTimeMillis());

        origMap.put("TrxId", trxId);// 订单号

        //origMap.put("TrxId", "201703131045234230112");// 订单号
        origMap.put("OrdrName", "畅捷支付");// 商品名称
        origMap.put("MerUserId", "0000001");// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId", "200001160097");// 子账户号
        origMap.put("SubMerchantNo", "");// 子商户号
        origMap.put("ExpiredTime", "40m");// 订单有效期
        origMap.put("CardBegin", "430000");// 卡号前6位
        origMap.put("CardEnd", "7200");// 卡号后4位
        origMap.put("TrxAmt", "0.01");// 交易金额
        origMap.put("TradeType", "11");// 交易类型
        origMap.put("SmsFlag", "1");
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }


    /**
     * 2.5 支付确认接口： api nmg_api_quick_payment_smsconfirm
     */

    private void nmg_api_quick_payment_smsconfirm() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_quick_payment_smsconfirm");// 请求的接口名称
        // 2.2 业务参数
        String trxId = Long.toString(System.currentTimeMillis());
        origMap.put("TrxId", trxId);// 订单号

        //origMap.put("TrxId", "101149785980144593760");// 订单号
        origMap.put("OriPayTrxId", "1501123506844");// 原有支付请求订单号
        origMap.put("SmsCode", "695535");// 短信验证码
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.6 支付请求接口(直付通) api nmg_zft_api_quick_payment
     */

    private void nmg_zft_api_quick_payment() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_zft_api_quick_payment");// 支付接口名称
        // 2.2 业务参数
        origMap.put("TrxId", "2017031310452344543522");// 订单号
        origMap.put("OrdrName", "畅捷支付");// 商品名称
        origMap.put("MerUserId", "20201");// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId", "200001160097");// 子账户号
        origMap.put("SubMerchantNo", "200001160097");// 子商户号
        origMap.put("ExpiredTime", "40m");// 订单有效期
//		origMap.put("BkAcctTp", "01");// 卡类型（00 – 银行贷记卡;01 – 银行借记卡;）
//		origMap.put("BkAcctNo", this.encrypt("6200000000000000", MERCHANT_PUBLIC_KEY, charset));// 卡号
//		origMap.put("IDTp", "01");// 证件类型 （目前只支持身份证 01：身份证）
//		origMap.put("IDNo", this.encrypt("360000000000000000", MERCHANT_PUBLIC_KEY, charset));// 证件号
//		origMap.put("CstmrNm", this.encrypt("XXX", MERCHANT_PUBLIC_KEY, charset));// 持卡人姓名
//		origMap.put("MobNo", this.encrypt("13511111111", MERCHANT_PUBLIC_KEY, charset));// 银行预留手机号
//		origMap.put("EnsureAmount", "1");//担保金额
        origMap.put("TrxAmt", "30");// 交易金额
        origMap.put("TradeType", "11");// 交易类型
        origMap.put("SmsFlag", "1");//短信发送标识
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.2.7. 直接支付请求接口(畅捷前台)  nmg_quick_onekeypay
     */
    private void nmg_quick_onekeypay() throws Exception {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_quick_onekeypay");// 直接支付
        // 2.2 业务参数
        //origMap.put("TrxId", "2017031310052312");// 商户网站唯一订单号
        String trxId = Long.toString(System.currentTimeMillis());
        origMap.put("TrxId", trxId);// 订单号

        origMap.put("OrdrName", "测试商品"); // 商品名称
        origMap.put("OrdrDesc", "[{'商品型号':'D007','商品性能':'Test'}]");// 商品描述
        origMap.put("MerUserId", "1700000001");// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId", "200001160097");// 生产环境
        origMap.put("SubMerchantNo", "");// 子商户号
        origMap.put("ExpiredTime", "40m");// 订单有效期
//		origMap.put("BkAcctTp", "01");// 卡类型（00 – 银行贷记卡;01 – 银行借记卡;）
//		origMap.put("BkAcctNo", this.encrypt("430000000000000000", MERCHANT_PUBLIC_KEY, charset));// 卡号
//		origMap.put("IDTp", "01");// 证件类型 （目前只支持身份证 01：身份证）
//		origMap.put("IDNo", this.encrypt("1300000000000000", MERCHANT_PUBLIC_KEY, charset));// 证件号
//		origMap.put("CstmrNm", this.encrypt("XXX", MERCHANT_PUBLIC_KEY, charset));// 持卡人姓名
//		origMap.put("MobNo", this.encrypt("1380000000", MERCHANT_PUBLIC_KEY, charset));// 银行预留手机号

//		origMap.put("CardCvn2", "");//cvv2
//		origMap.put("CardExprDt", "07/21");//有效期

        //origMap.put("EnsureAmount", "1");//担保金额
        origMap.put("TrxAmt", "0.01");// 交易金额
        origMap.put("TradeType", "11");// 交易类型
        origMap.put("AccessChannel", "web");//用户终端类型  web  wap
        origMap.put("ReturnUrl", "http://www.baidu.com");//同步回调地址
        origMap.put("NotifyUrl", "");//异步回调地址
        origMap.put("Extension", "");//扩展字段

        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);

    }

    /**
     * 2.2.8  支付请求接口(畅捷前台)  nmg_nquick_onekeypay
     */
    private void nmg_nquick_onekeypay() throws Exception {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_nquick_onekeypay");// 支付请求接口
        // 2.2 业务参数
        //origMap.put("TrxId", "2017031310052312");// 商户网站唯一订单号
        String trxId = Long.toString(System.currentTimeMillis());
        origMap.put("TrxId", trxId);// 订单号

        origMap.put("OrdrName", "测试商品"); // 商品名称
        origMap.put("OrdrDesc", "");// 商品描述
        origMap.put("MerUserId", "1700000000");// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId", "200001160097");// 生产环境
        origMap.put("SubMerchantNo", "");// 子商户号

        origMap.put("BkAcctTp", "01");// 卡类型（00 – 银行贷记卡;01 – 银行借记卡;）	实际上应该不需要这个字段
        origMap.put("CardBegin", "430000");// 卡号前6位
        origMap.put("CardEnd", "4700");// 卡号后4位
        origMap.put("ExpiredTime", "40m");// 订单有效期
        //origMap.put("EnsureAmount", "1");//担保金额
        origMap.put("TradeType", "11");// 交易类型
        origMap.put("TrxAmt", "0.01");// 交易金额

        origMap.put("AccessChannel", "web");//用户终端类型  web  wap
        origMap.put("ReturnUrl", "http://www.baidu.com");//同步回调地址
        origMap.put("NotifyUrl", "");//异步回调地址
        origMap.put("Extension", "");//扩展字段

        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);

    }

    /**
     * 2.9 用户鉴权绑卡信息查询 api nmg_api_auth_info_qry
     */

    private void nmg_api_auth_info_qry() throws Exception {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_auth_info_qry");// 用户鉴权绑卡信息查询接口名
        // 2.2 业务参数
        origMap.put("TrxId", "20170313100523171");// 商户网站唯一订单号
        origMap.put("MerUserId", "0000001"); // 用户标识（测试时需要替换一个新的meruserid）
        //origMap.put("CardBegin", "430000");// 卡号前6位
        //origMap.put("CardEnd", "4700");// 卡号后4位
        origMap.put("BkAcctTp", "01");// 卡类型（00 – 银行贷记卡;01 – 银行借记卡）
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 用户鉴权解绑 nmg_api_auth_unbind  普通方式
     */

    private void nmg_api_auth_unbind() throws Exception {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_auth_unbind");// 用户鉴权解绑接口名
        // 2.2 业务参数
        String trxId = Long.toString(System.currentTimeMillis());
        origMap.put("TrxId", trxId);// 商户网站唯一订单号
        origMap.put("MerchantNo", "200001160097");// 子商户号
        origMap.put("MerUserId", "0000001"); // 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("UnbindType", "1"); // 解绑模式。0为物理解绑，1为逻辑解绑
//		origMap.put("CardId", "");// 卡号标识
        origMap.put("CardBegin", "436742");// 卡号前6位
        origMap.put("CardEnd", "7269");// 卡号后4位
        origMap.put("Extension", "");// 扩展字段
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.10 用户退款请求接口： nmg_api_refund
     */

    private void nmg_api_refund() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_refund");// 支付的接口名
        // 2.2 业务参数
        origMap.put("TrxId", "2017030915302088");// 订单号
        origMap.put("OriTrxId", "f85af8cca7584bbebb88678e8a16bf26");// 原有支付请求订单号
        origMap.put("TrxAmt", "0.09");// 退款金额
        origMap.put("RefundEnsureAmount", null);// 退担保金额
//		origMap.put("RoyaltyParameters",
//				"[{'userId':'13890009900','PID':'2','account_type':'101','amount':'100.00'},{'userId':'13890009900','PID':'2','account_type':'101','amount':'100.00'}]");// 退款分润账号集
        origMap.put("NotifyUrl", "www.baidu.com");// 异步通知地址
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.11 商户短信验证码重发  ：nmg_api_quick_payment_resend
     */

    private void nmg_sms_resend() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        // 2.2 业务参数
        origMap.put("Service", "nmg_api_quick_payment_resend");
        origMap.put("TrxId", "2017030915102022");// 订单号
        origMap.put("OriTrxId", "20170309131120");// 原业务请求订单号
        origMap.put("TradeType", "auth_order");// 原业务订单类型
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }


    /**
     * 2.12 商户日退款对账单文件接口：nmg_api_refund_trade_file
     */
    public void nmg_api_refund_trade_file() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_refund_trade_file");// 请求的接口名称
        // 2.9 日退款对账单文件
        origMap.put("TransDate", "20160728");// 交易日期
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * *
     * <p>
     * <p>
     * 2.13：确认收货接口：nmg_api_quick_payment_receiptconfirm
     */

    public void nmg_api_quick_payment_receiptconfirm() throws Exception {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("TrxId", "2334554654232342");//订单号
        origMap.put("OrderTrxId", "2334554654232342");//原支付请求订单号
        origMap.put("Service", "nmg_api_quick_payment_receiptconfirm");
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);

    }

    /**
     * 2.14 商户鉴权/支付/退款订单状态查询接口 api tzt_order_query
     */

    private void nmg_api_query_trade() throws Exception {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_query_trade");// 请求的接口名
        // 2.2 业务参数
        origMap.put("TrxId", "2017030915202027");// 订单号
        origMap.put("OrderTrxId", "2017030915302088");// 原业务请求订单号
        origMap.put("TradeType", "refund_order");// 原业务订单类型
        this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);
    }

    /**
     * 2.15 商户日交易对账单文件:nmg_api_everyday_trade_file
     */
    public void nmg_api_everyday_trade_file() {

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service", "nmg_api_everyday_trade_file");// 请求的接口名称
        // 2.11 日支付对账文件
        origMap.put("TransDate", "20170710");// 交易日期
//		this.gatewayPost(origMap, charset, MERCHANT_PRIVATE_KEY);

        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        if (obj instanceof String) {
            System.out.println((String) obj);
        } else {
            this.downloadFile((byte[]) obj);
        }

    }


    public void downloadFile(byte[] bt) {
        // 确定写出文件的位置
        File file = new File("C:\\TestQuick.xls");
        // 建立输出字节流
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            // 用FileOutputStream 的write方法写入字节数组
            fos.write(bt);
            System.out.println("文件下载成功");
            // 为了节省IO流的开销，需要关闭
            if (fos != null) {
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        ChanpayGateway test = new ChanpayGateway();

        test.nmg_biz_api_auth_req(); // 2.1 鉴权请求---API
//		test.nmg_page_api_auth_req(); //2.2 鉴权请求 ---畅捷前端
//		test.nmg_api_auth_sms(); // 2.3 鉴权请求确认---API
//		test.nmg_biz_api_quick_payment(); //2.4 支付请求---API
//		test.nmg_api_quick_payment_smsconfirm(); //2.5 支付确认---API
//		test.nmg_zft_api_quick_payment(); //2.6 支付请求（直付通）
//		test.nmg_quick_onekeypay();  //2.7 直接请求---畅捷前端
//		test.nmg_nquick_onekeypay();  //2.8 支付请求---畅捷前端
//		test.nmg_api_auth_info_qry(); // 2.9 鉴权绑卡查询
//		test.nmg_api_auth_unbind(); // 鉴权解绑（普通）
//		test.nmg_api_refund();//商户退款请求
//		

//		test.nmg_sms_resend(); //2.11 短信重发
//		test.nmg_api_query_trade(); //2.14 订单状态查询
//		test.nmg_api_refund_trade_file(); //2.12 退款对账单
//		test.nmg_api_everyday_trade_file(); //2.15 交易对账单
//		test.nmg_api_quick_payment_receiptconfirm();// 2.13 确认收货接口
//		test.notifyVerify(); // 测试异步通知验签
    }

}
