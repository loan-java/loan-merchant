package com.mod.loan.pay.baofoo.demo;

import com.mod.loan.pay.baofoo.base.TransContent;
import com.mod.loan.pay.baofoo.base.TransHead;
import com.mod.loan.pay.baofoo.base.request.TransReqBF0040001;
import com.mod.loan.pay.baofoo.base.response.TransRespBF0040001;
import com.mod.loan.pay.baofoo.domain.RequestParams;
import com.mod.loan.pay.baofoo.http.SimpleHttpResponse;
import com.mod.loan.pay.baofoo.rsa.RsaCodingUtil;
import com.mod.loan.pay.baofoo.util.BaofooClient;
import com.mod.loan.pay.baofoo.util.SecurityUtil;
import com.mod.loan.pay.baofoo.util.TransConstant;

import java.util.ArrayList;
import java.util.List;

public class BF0040001SDKDemo {

    /**
     * @param args
     * @throws Exception
     */
    @SuppressWarnings({"static-access", "unchecked"})
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        String dataType = TransConstant.data_type_xml; // 数据类型 xml/json

        TransContent<TransReqBF0040001> transContent = new TransContent<TransReqBF0040001>(
                dataType);

        List<TransReqBF0040001> trans_reqDatas = new ArrayList<TransReqBF0040001>();

        TransReqBF0040001 transReqData = new TransReqBF0040001();
        transReqData.setTrans_no("P1123123123123");
        transReqData.setTrans_money("1");
        transReqData.setTo_acc_name("帅立杰");
        transReqData.setTo_acc_no("6222021202041701419");
        transReqData.setTo_bank_name("中国工商银行");
        transReqData.setTrans_summary("测试");
        transReqData.setTrans_card_id("330124199212274817");
        transReqData.setTrans_mobile("15868417851");
        trans_reqDatas.add(transReqData);
        transContent.setTrans_reqDatas(trans_reqDatas);

        String bean2XmlString = transContent.obj2Str(transContent);
        System.out.println("报文：" + bean2XmlString);

        String keyStorePath = "/Users/shuailijie/Desktop/项目汇总/宝付代付API接口文档/测试环境信息/证书/m_pri.pfx";
        String keyStorePassword = "123456";
        String pub_key = "/Users/shuailijie/Desktop/项目汇总/宝付代付API接口文档/测试环境信息/证书/baofoo_pub.cer";
        String origData = bean2XmlString;

        /**
         * 加密规则：项目编码UTF-8
         * 第一步：BASE64 加密
         * 第二步：商户私钥加密
         */
        origData = new String(SecurityUtil.Base64Encode(origData));//Base64.encode(origData);
        String encryptData = RsaCodingUtil.encryptByPriPfxFile(origData,
                keyStorePath, keyStorePassword);

        System.out.println("----------->【私钥加密-结果】" + encryptData);

        // 发送请求
        String requestUrl = "https://paytest.baofoo.com/baofoo-fopay/pay/BF0040001.do";
        String memberId = "100000178"; // 商户号
        String terminalId = "100000859"; // 终端号

        RequestParams params = new RequestParams();
        params.setMemberId(Integer.parseInt(memberId));
        params.setTerminalId(Integer.parseInt(terminalId));
        params.setDataType(dataType);
        params.setDataContent(encryptData);// 加密后数据
        params.setVersion("4.0.0");
        params.setRequestUrl(requestUrl);
        SimpleHttpResponse response = BaofooClient.doRequest(params);

        System.out.println("宝付请求返回结果：" + response.getEntityString());

        TransContent<TransRespBF0040001> str2Obj = new TransContent<TransRespBF0040001>(
                dataType);
        String reslut = response.getEntityString();
        /**
         * 在商户终端正常的情况下宝付同步返回会以密文形式返回,如下：
         *
         * 此时要先宝付提供的公钥解密：RsaCodingUtil.decryptByPubCerFile(reslut, pub_key)
         *
         * 再次通过BASE64解密：new String(new Base64().decode(reslut))
         *
         * 在商户终端不正常或宝付代付系统异常的情况下宝付同步返回会以明文形式返回
         */
        System.out.println(reslut);
        //明文返回处理可能是报文头参数不正确、或其他的异常导致；
        if (reslut.contains("trans_content")) {
            //明文返回
            //我报文错误处理
            str2Obj = (TransContent<TransRespBF0040001>) str2Obj.str2Obj(
                    reslut, TransRespBF0040001.class);
            // 业务逻辑判断
        } else {
            //密文返回
            //第一步：公钥解密
            reslut = RsaCodingUtil.decryptByPubCerFile(reslut, pub_key);
            //第二步BASE64解密
            reslut = SecurityUtil.Base64Decode(reslut);
            System.out.println(reslut);
            str2Obj = (TransContent<TransRespBF0040001>) str2Obj.str2Obj(
                    reslut, TransRespBF0040001.class);
            // 业务逻辑判断
            TransHead list = str2Obj.getTrans_head();

            System.out.println(list.getReturn_code() + ":" + list.getReturn_msg());
        }
    }

}
