package com.mod.loan.pay.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mod.loan.common.exception.BizException;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class YeepayUtil {

    public static final String BASE_URL = "baseURL";
    public static final String PAYMENT_URL = "paymentURI";
    public static final String PAYMENTQUERY_URL = "paymentqueryURI";
    public static final String customeramountQuery_URL = "customeramountQueryURI";
    public static final String batchsend_URL = "batchsendURI";
    public static final String REMITDAYDOWNLOAD_URL = "remitdaydownloadURI";


    //获取父商编
    public static String getParentMerchantNo() {
        return Config.getInstance().getValue("groupNumber");
    }

    //获取子商编
    public static String getMerchantNo() {
        return Config.getInstance().getValue("customerNumber");
    }

    public static String getUrl(String payType) {
        return Config.getInstance().getValue(payType);
    }

    //获取密钥P12
    public static String getPrivateKey() {
        return Config.getInstance().getValue("privatekey");
    }

    public static JSONObject yeepayYOP(Map<String, Object> map, String Uri) throws Exception {
        YopRequest request = new YopRequest("OPR:" + getMerchantNo(), getPrivateKey());

        Set<Entry<String, Object>> entry = map.entrySet();
        for (Entry<String, Object> s : entry) {
            request.addParam(s.getKey(), s.getValue());
        }
        log.info("易宝请求: " + JSON.toJSONString(request));

        //向YOP发请求
        YopResponse response = YopRsaClient.post(Uri, request);

        checkFailResp(response);
        return parseResult(response.getStringResult());
    }

    public static void checkFailResp(YopResponse resp) throws BizException {
        if ("FAILURE".equals(resp.getState())) {
            if (resp.getError() != null)
                throw new BizException(resp.getError().getCode(), resp.getError().getMessage());
        }
    }

    public static JSONObject parseResult(String result) {
        if (StringUtils.isBlank(result)) return null;

        return JSON.parseObject(result);
    }

    //将获取到的response转换成json格式
    public static Map<String, Object> parseResponse(String yopresponse) {

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap = JSON.parseObject(yopresponse,
                new TypeReference<TreeMap<String, Object>>() {
                });
        System.out.println("将response转化为map格式之后: " + jsonMap);
        return jsonMap;
    }

    public static String getRandom(int length) {
        Random random = new Random();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ret.append(Integer.toString(random.nextInt(10)));
        }
        return ret.toString();
    }

    /**
     * 新的下载对账单接口
     *
     * @param
     * @return
     */
    public static String download(Map<String, String> params, String path) {
        InputStream returnStream = null; //从yop返回的请求对账文件的流
        OutputStream outputStream = null; //输出到项目中的流
        String parentMerchantNo = getParentMerchantNo();
        String merchantNo = getMerchantNo();

        String date = params.get("date"); //日期格式  yyyy-mm-dd
        String dataType = params.get("dataType"); //出款具有不同类型
        String OPRkey = getPrivateKey();  //父商编私钥

        YopRequest request = new YopRequest("OPR:" + parentMerchantNo, OPRkey);

        YopResponse response = null; //获得一个yop response

        //配置接口参数
        request.addParam("parentMerchantNo", parentMerchantNo);
        request.addParam("merchantNo", merchantNo);
        request.addParam("dayString", date);
        request.addParam("dataType", dataType);

        //按照月和日参数的不同向yop发起对账文件流的请求
        //arg0:接口的uri（参见手册）
        //arg1:配置好参数的请求对象

        String fileName = "";
        String filePath = "";
        try {
            //出款日对账
            response = YopRsaClient.get(getUrl(REMITDAYDOWNLOAD_URL), request);
            fileName = "remitday-" + dataType + "-" + date + ".csv";
            System.out.println(response.toString());
            if (!response.isSuccess()) { //访问失败
                filePath = response.getError().getMessage() + " " + response.getError().getSubMessage();
                return filePath;
            }
            returnStream = response.getFile();
            if (returnStream == null) {
                System.out.println("空的呢！没交易啊!\n");
                filePath = "The status is 'SUCCESS' but it's a pity that the file is empty so cannot be downloaded.";
                return filePath;
            }
            filePath = path + File.separator + fileName;
            System.out.println("filePath=====" + filePath);
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            //测试能否输出结果
//			String result = new BufferedReader(new InputStreamReader(returnStream)).lines().collect(Collectors.joining(System.lineSeparator()));
//			System.out.println(result);

            outputStream = new FileOutputStream(file);
            byte[] bs = new byte[1024];
            int readNum;
            while ((readNum = returnStream.read(bs)) != -1) {
                outputStream.write(bs, 0, readNum);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            returnStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /*原来下载对账文件的接口*/
//    public static String yosFile(Map<String, String> params, String path) {
//        StdApi apidApi = new StdApi();
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//
//        String method = params.get("method");
//        String date = params.get("date");
//        String dataType = params.get("dataType");
//
//        String fileName = "";
//        String filePath = "";
//        try {
//
//            inputStream = apidApi.remitDayBillDownload(getMerchantNo(), date, dataType);
//            fileName = "remitday-" + dataType + "-" + date + ".csv";
//
//            filePath = path + File.separator + fileName;
//            System.out.println("filePath=====" + filePath);
//            outputStream = new FileOutputStream(new File(filePath));
//             
//            
//
//            byte[] bs = new byte[1024];
//            int readNum;
//            while ((readNum = inputStream.read(bs)) != -1) {
//                outputStream.write(bs, 0, readNum);
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            return null;
//        } finally {
//            try {
//                outputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return filePath;
//    }


}

        	

        

