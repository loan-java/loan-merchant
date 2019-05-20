package com.mod.loan.util.rongze;

import com.mod.loan.config.Constant;
import com.mod.loan.util.StringUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @ author liujianjian
 * @ date 2019/5/15 16:31
 */
public class BizDataUtil {

    private static final String orderNoPre = ""; //订单号前缀
//    private static final String orderNoPre = "RZ";

    //加密请求的业务数据
    public static String encryptBizData(String bizData, String despwd) throws Exception {
        if (StringUtils.isBlank(bizData)) return "";
        return StandardDesUtils.encrypt(bizData, despwd);
    }

    //根据 des_key 解密接收到的业务数据
    public static String decryptBizData(String encryptStr, String desKey) throws Exception {
        if (StringUtils.isBlank(encryptStr)) return "";

        String despwd = RSAUtils.decrypt(desKey, Constant.orgPrivateKey);
        return StandardDesUtils.decrypt(encryptStr, despwd);
    }

    //处理融泽订单号，如果融泽没传订单号，则系统生成
    public static String getRZOrderNo(String orderNo) {
        return StringUtils.isBlank(orderNo) ? bindRZOrderNo(StringUtil.getOrderNumber("")) : bindRZOrderNo(orderNo);
    }

    public static String bindRZOrderNo(String orderNo) {
        return StringUtils.isBlank(orderNo) ? "" : orderNoPre + orderNo;
    }

    public static String unbindRZOrderNo(String orderNo) {
        //去掉系统加的前缀获取原订单号
        return StringUtils.isBlank(orderNo) ? "" : StringUtils.isNotBlank(orderNoPre) ? orderNo.substring(orderNoPre.length()) : orderNo;
    }

}
