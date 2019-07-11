package com.mod.loan.service.third.baofoo;

import com.mod.loan.pay.baofoo.base.TransContent;
import com.mod.loan.pay.baofoo.base.TransHead;
import com.mod.loan.pay.baofoo.base.response.TransRespBFBalance;
import com.mod.loan.pay.baofoo.config.BaofooPayConfig;
import com.mod.loan.pay.baofoo.util.HttpUtil;
import com.mod.loan.pay.baofoo.util.SecurityUtil;
import com.mod.loan.pay.baofoo.util.TransConstant;
import com.mod.loan.util.ConstantUtils;
import com.mod.loan.util.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ author liujianjian
 * @ date 2019/5/13 17:26
 */
@Service
public class BaofooBalanceQueryService {

    public static final Logger log = LoggerFactory.getLogger(BaofooBalanceQueryService.class);

    private String dataType = TransConstant.data_type_xml;

    @Resource
    private BaofooPayConfig baofooPayConfig;

    public long queryBalanceFen() {
        return MoneyUtil.yuan2Fen(queryBalanceYuan());
    }

    private Double queryBalanceYuan() {
        TransContent<TransRespBFBalance> strObj = new TransContent<TransRespBFBalance>(
                dataType);

        Map<String, String> PostParams = new HashMap<String, String>();
        PostParams.put("member_id", baofooPayConfig.getBaofooMemberId());//	商户号
        PostParams.put("terminal_id", baofooPayConfig.getBaofooBalanceTerminalId());//	终端号
        PostParams.put("return_type", dataType);//	返回报文数据类型xml 或json
        PostParams.put("trans_code", "BF0001");//	交易码
        PostParams.put("version", baofooPayConfig.getBaofooVersion());//版本号
        PostParams.put("account_type", String.valueOf(ConstantUtils.ONE));//帐户类型--0:全部、1:基本账户、2:未结算账户、3:冻结账户、4:保证金账户、5:资金托管账户；

        String Md5AddString = "member_id=" + PostParams.get("member_id") + ConstantUtils.MAK + "terminal_id=" + PostParams.get("terminal_id") + ConstantUtils.MAK + "return_type=" + PostParams.get("return_type") + ConstantUtils.MAK + "trans_code=" + PostParams.get("trans_code") + ConstantUtils.MAK + "version=" + PostParams.get("version") + ConstantUtils.MAK + "account_type=" + PostParams.get("account_type") + ConstantUtils.MAK + "key=" + baofooPayConfig.getBaofooKeyString();
        log.info("Md5拼接字串:{}", Md5AddString);//商户在正式环境不要输出此项以免泄漏密钥，只在测试时输出以检查验签失败问题
        String Md5Sing = SecurityUtil.MD5(Md5AddString).toUpperCase();//必须为大写
        PostParams.put("sign", Md5Sing);
        String re_Url = baofooPayConfig.getBaofooBalanceUrl();//正式请求地址
        String retrunString = HttpUtil.RequestForm(re_Url, PostParams);
        log.info("返回:{}" , retrunString);
        strObj = (TransContent<TransRespBFBalance>) strObj.str2Obj(
                retrunString, TransRespBFBalance.class);

        TransHead list = strObj.getTrans_head();
        log.info(list.getReturn_code() + ":" + list.getReturn_msg());
        if (ConstantUtils.BAOFOO_SUCCESSCODE.equals(list.getReturn_code())) {
            return strObj.getTrans_reqDatas().get(0).getBalance();
        }
        return ConstantUtils.DEFAULT_BALANCE;
    }
}
