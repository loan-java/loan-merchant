package com.mod.loan.util.chanpay.dsf;

import com.mod.loan.config.Constant;

import java.util.HashMap;
import java.util.Map;

public class BaseParameter {

    public Map<String,String> requestBaseParameter(){
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap.put(BaseConstant.SERVICE, "cjt_dsf");// 接口名
        origMap.put(BaseConstant.VERSION, "1.0");
        origMap.put(BaseConstant.PARTNER_ID, Constant.chanpayMerchantNo); //生产环境测试商户号
        origMap.put(BaseConstant.TRADE_DATE, BaseConstant.DATE);
        origMap.put(BaseConstant.TRADE_TIME, BaseConstant.TIME);
        origMap.put(BaseConstant.INPUT_CHARSET, BaseConstant.CHARSET);// 字符集
        origMap.put(BaseConstant.MEMO, "");// 备注
        return origMap;
    }
}
