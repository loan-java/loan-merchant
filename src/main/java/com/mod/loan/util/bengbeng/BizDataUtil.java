package com.mod.loan.util.bengbeng;

import com.mod.loan.config.Constant;
import org.apache.commons.lang.StringUtils;

/**
 * @ author liujianjian
 * @ date 2019/5/15 16:31
 */
public class BizDataUtil {


    //加密请求的业务数据
    public static String encryptBizData(String bizData, String despwd) throws Exception {
        if (StringUtils.isBlank(bizData)) return "";
        return StandardDesUtils.encrypt(bizData, despwd);
    }

    //根据 des_key 解密接收到的业务数据
    public static String decryptBizData(String encryptStr, String desKey) throws Exception {
        if (StringUtils.isBlank(encryptStr)) return "";

        String despwd = RSAUtils.decrypt(desKey, Constant.bengBengOrgPrivateKey);
        return StandardDesUtils.decrypt(encryptStr, despwd);
    }
}
