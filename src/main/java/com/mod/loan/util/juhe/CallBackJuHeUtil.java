package com.mod.loan.util.juhe;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.util.HttpUtils;
import com.mod.loan.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kk
 */
public class CallBackJuHeUtil {
    private static Logger logger = LoggerFactory.getLogger(CallBackJuHeUtil.class);

    public static boolean callBack(String host, JSONObject object) {
        object.put("timeStamp", System.currentTimeMillis() / 1000);
        String sign = SignUtil.getSign(object);
        object.put("sign", sign);

        String url = host + "/apiext/orderCallback/orderInfo";
        String response = HttpUtils.restRequest(url, object.toJSONString(), "POST");
        JSONObject res = JSON.parseObject(response);
        if ("200".equals(res.getString("code"))) {
            boolean i = true;
        }
        System.out.println(response + " === " + res.getString("0"));
        return false;
    }

//    /**
//     * unicode转字符串
//     *
//     * @param unicode unicode串
//     * @return 字符串
//     */
//    public static String unicodeToString(String unicode) {
//        StringBuffer sb = new StringBuffer();
//        String[] hex = unicode.split("\\\\u");
//        for (int i = 1; i < hex.length; i++) {
//            int index = Integer.parseInt(hex[i], 16);
//            sb.append((char) index);
//        }
//        return sb.toString();
//    }
}
