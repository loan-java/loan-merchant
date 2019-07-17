package com.mod.loan.service.pay.kuaiqian;

/**
 * @author xiang.zhang
 * @version 1.0
 * @date 2012-05-01
 */

import com.alibaba.fastjson.JSON;
import com.bill99.schema.asap.commons.Mpf;
import com.mod.loan.pay.kuaiqian.config.KuaiqianPayConfig;
import com.mod.loan.pay.kuaiqian.schema.commons.Version;
import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.MaMbrinfoRequestHead;
import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.MaMbrinfoResponseHead;
import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.MaSealPkiDataType;
import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.balance.*;
import com.mod.loan.pay.kuaiqian.util.Bill99PkiInCrytoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class KuaiQianBalanceQueryService {

    @Resource
    private KuaiqianPayConfig kuaiqianPayConfig;

    //WS请求URL
    //private String wsURL = "http://192.168.63.248:8086/apiservices/services/balance.wsdl";
    //private String wsURL = "http://sandbox.99bill.com/apiservices/services/balance.wsdl";
    //WS请求超时时间
    private int timeout = 60000;

    //功能编码10012310239
    //private String memberCode = "10012852527";
//    private String memberCode = "10012138842";
//    private String featureCode = "F48";
    //功能密钥，开通功能后，邮件发送到所注册的邮箱
    //private String key = "BC8BLQUD545E4TAC";
//    private String key = "XSD889YSFS37NZWS";

    private String WS_success = "1000";
//    private String VERSION_Service = "ma.mbrinfo.balance";
//    private String VERSION_version = "1";


    public long queryBalanceFen() {
        try {
            return queryBalance();
        } catch (Exception e) {
            log.error("快钱查询商户余额失败: " + e.getMessage(), e);
        }
        return 0L;
    }

    private long queryBalance() throws Exception {

        //拼装请求参数 根据业务修改参数值
        Version version = new Version();
        version.setVersion("1");
        version.setService(kuaiqianPayConfig.getQueryBalanceService());

        MaMbrinfoRequestHead requestHeader = new MaMbrinfoRequestHead();
        requestHeader.setVersion(version);
        requestHeader.setAppId(kuaiqianPayConfig.getAppId());
        requestHeader.setRequestId("1000");

        BalanceApiRequestType balanceApiRequestType = new BalanceApiRequestType();
        //balanceApiRequestType.setMemberCode(memberCode);
        //balanceApiRequestType.setAcctType(1);
        balanceApiRequestType.setMemberAcctCode(kuaiqianPayConfig.getQueryBalanceMemberAcctCode());
        balanceApiRequestType.setMerchantMemberCode(kuaiqianPayConfig.getKuaiqianMemberCode());

        //请求加密参数
        MaSealPkiDataType requestMaSealPkiDataType;
        try {
            //请求加密明文
            String requestOriginalData = this.requestOriginalData(balanceApiRequestType, kuaiqianPayConfig.getFunctionKey());
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! " + requestOriginalData);
            Mpf mpf = createMpf(kuaiqianPayConfig.getKuaiqianMemberCode(), kuaiqianPayConfig.getKuaiqianFetureCode());
//			Mpf mpf = createMpf(ApiRequestType.getMemberCode(),featureCode);

            //调用加密工具方法得到加密对象
            requestMaSealPkiDataType = Bill99PkiInCrytoUtils.seadInCryto(mpf, requestOriginalData);
        } catch (Exception e) {
//            System.out.println(e.getMessage() + " 请检查[策略置文件strategy.xml,私钥,公钥证书等]");
            throw new Exception("快钱查询余额加密请求失败, 请检查[策略置文件strategy.xml,私钥,公钥证书等]: " + e.getMessage(), e);
        }

        BalanceRequestBody requestBody = new BalanceRequestBody();
        requestBody.setBalanceApiRequestType(balanceApiRequestType);
        requestBody.setMaSealPkiDataType(requestMaSealPkiDataType);

        BalanceRequest request = new BalanceRequest();
        request.setRequestBody(requestBody);
        request.setRequestHeader(requestHeader);

        //调用WS 获取结果
        BalanceResponse response = this.balanceFormWS(request);
//        System.out.println(response.getResponseHeader().getErrorMsg());
        //获取返回请求头，得到请求处理结果
        MaMbrinfoResponseHead responseHead = response.getResponseHeader();
        if (!WS_success.endsWith(responseHead.getErrorCode())) {
            throw new Exception("快钱查询余额返回结果失败: " + JSON.toJSONString(responseHead));
        }

        //验签标记
        boolean flag;
        try {
            BalanceApiResponseType balanceApiResponseType = response.getResponseBody().getBalanceApiResponseType();
            MaSealPkiDataType responseMaSealPkiDataType = response.getResponseBody().getMaSealPkiDataType();

            //返回加密明文
            String resopnseOriginalData = this.responseOriginalData(balanceApiResponseType, kuaiqianPayConfig.getFunctionKey());

            Mpf mpf = createMpf(kuaiqianPayConfig.getKuaiqianMemberCode(), kuaiqianPayConfig.getKuaiqianFetureCode());
//			Mpf mpf = createMpf(ApiResponseType.getMemberCode(),featureCode);

            //调用解密工具方法得到解密验证结果
            flag = Bill99PkiInCrytoUtils.unseadInCryto(mpf, resopnseOriginalData, responseMaSealPkiDataType);

            //验签成功
            if (!flag) {
                throw new Exception("快钱查询余额返回验签失败");
            }

            return balanceApiResponseType.getAmount();
        } catch (Exception e) {
            throw new Exception("快钱查询余额失败: " + e.getMessage(), e);
        }
    }

    /**
     * @param balanceRequest
     * @return
     */
    private BalanceResponse balanceFormWS(BalanceRequest balanceRequest) throws Exception {

        log.info("快钱余额查询结果:{}", JSON.toJSONString(balanceRequest));

        URL wsdlUrl = new URL(kuaiqianPayConfig.getKuaiqianQueryBalanceUrl());
        // 取得Web 服务
        BalanceSoap11Stub service = (BalanceSoap11Stub) new BalanceServiceLocator()
                .getBalanceSoap11(wsdlUrl);
        service.setTimeout(timeout);

        // 调用WS服务
        BalanceResponse result = service.balance(balanceRequest);

        log.info("快钱余额查询结果:{}", JSON.toJSONString(result));
        return result;

    }

    private Mpf createMpf(String memberCode, String fetureCode) {
        Mpf mpf = new Mpf();
        mpf.setMemberCode(memberCode);
        mpf.setFeatureCode(fetureCode);
        return mpf;
    }

    /**
     * request 原数据
     *
     * @param requestType
     * @param key
     * @return
     */
    private String requestOriginalData(BalanceApiRequestType requestType,
                                       String key) {
        StringBuffer sb = new StringBuffer();
        // 去掉 int默认值为零 如果为零则替换为 ""
        String acctType = this.null2String(requestType.getAcctType());
        acctType = "0".equals(acctType) ? "" : acctType;

        sb.append("&memberCode=")
                .append(this.null2String(requestType.getMemberCode()))
                .append("&acctType=")
                .append(acctType)
                .append("&memberAcctCode=")
                .append(this.null2String(requestType.getMemberAcctCode()))
                .append("&merchantMemberCode=")
                .append(this.null2String(requestType
                        .getMerchantMemberCode())).append("&key=")
                .append(this.null2String(key));
        return sb.toString();
    }

    /**
     * response 原数据
     *
     * @param responseType
     * @param key
     * @return
     */
    private String responseOriginalData(BalanceApiResponseType responseType,
                                        String key) {
        StringBuffer sb = new StringBuffer();
        sb.append("&amount=")
                .append(this.null2String(responseType.getAmount()))
                .append("&key=").append(this.null2String(key));
//        System.out.println("余额amount：" + this.null2String(responseType.getAmount()));
        return sb.toString();
    }

    /**
     * 转换时间格式为  yyyyMMddkkmmss
     *
     * @return
     */
    private String toDateStr(Date date) {
        String patten2 = "yyyyMMddkkmmss";
        SimpleDateFormat df2 = new SimpleDateFormat(patten2);
        return df2.format(date).toString();
    }

    private String null2String(Object obj) {
        return ((obj == null) ? "" : obj.toString());
    }
}
