package com.mod.loan.util.rongze;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.config.Constant;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ author liujianjian
 * @ date 2019/5/15 17:04
 */
public class RongZeSignUtil {

    public static String genSign(String json) throws Exception {
        if (StringUtils.isBlank(json)) return "";

        String pendVertContent = bindPreSignStr(json);
        String sign = RongZeRSAUtils.sign(pendVertContent, Constant.rongZeOrgPrivateKey);
        return sign;
    }

    public static boolean checkSign(String json, String sign) {
        return RongZeRSAUtils.checkSign(bindPreSignStr(json), sign, Constant.rongZePublicKey);
    }

    private static String bindPreSignStr(String json) {
        Map<String, String> paramMap = JSONObject.parseObject(json, Map.class);

        StringBuffer sbfStr = new StringBuffer();
        List<String> list = new ArrayList<String>(paramMap.keySet());
        Collections.sort(list); //参数名ASCII码从小到大排序（字典序）
        for (String key : list) {
            if (StringUtils.isNotBlank(paramMap.get(key))) {
                if (key.equalsIgnoreCase("sign")) continue;
                sbfStr.append(key + "=" + paramMap.get(key) + "&");
            }
        }
        return sbfStr.toString().substring(0, sbfStr.length() - 1);
    }
}
