package com.mod.loan.pay.chanpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.exception.BizException;
import com.mod.loan.util.MoneyUtil;
import com.mod.loan.pay.chanpay.dsf.BaseParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ author liujianjian
 * @ date 2019/6/18 20:41
 */
@Slf4j
@Component
public class ChanpayApiRequest extends BaseParameter {

    private static ChanpayGateway chanpay = new ChanpayGateway();

    public long queryPayBalanceFen() {
        return MoneyUtil.yuan2Fen(queryPayBalance());
    }

    //商户余额查询
    public double queryPayBalance() {
        try {
            Map<String, String> map = this.requestBaseParameter();
            map.put("TransCode", "C00005");
            map.put("OutTradeNo", ChanPayUtil.generateOutTradeNo());
//        map.put("AcctNo", ChanPayUtil.encrypt("200000920146777",
//                BaseConstant.MERCHANT_PUBLIC_KEY, BaseConstant.CHARSET));
//        map.put("AcctName", ChanPayUtil.encrypt("测试",
//                BaseConstant.MERCHANT_PUBLIC_KEY, BaseConstant.CHARSET));

            JSONObject json = doPost(map);
            return Double.valueOf(json.getString("PayBalance")); //出款户余额
        } catch (Exception e) {
            log.error("畅捷查询商户余额失败: " + e.getMessage(), e);
        }
        return 0D;
    }


    private JSONObject doPost(Map<String, String> origMap) throws Exception {
        log.info("畅捷 api 请求开始, params: " + JSON.toJSONString(origMap));
        String result = chanpay.gatewayPost(origMap);
        log.info("畅捷 api 请求结束, result: " + result);

        JSONObject json = JSON.parseObject(result);
        String acceptStatus = json.getString("AcceptStatus");
        String appRetMsg = json.getString("AppRetMsg");
        String appRetCode = json.getString("AppRetcode");
        String status = json.getString("Status");
        String retCode = json.getString("RetCode");
        String retMsg = json.getString("RetMsg");

        if ("F".equals(acceptStatus) || "F".equals(status)) {
            throw new BizException(StringUtils.isNotBlank(retCode) ? retCode : appRetCode, StringUtils.isNotBlank(retMsg) ? retMsg : appRetMsg);
        }
        return json;
    }

}
