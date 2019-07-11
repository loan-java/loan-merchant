package com.mod.loan.pay.kuaiqian.util;


import com.mod.loan.pay.kuaiqian.dto.common.SealDataType;
import com.mod.loan.pay.kuaiqian.dto.notify.popay.NotifyHead;
import com.mod.loan.pay.kuaiqian.dto.notify.popay.NotifyResponse;
import com.mod.loan.pay.kuaiqian.dto.notify.popay.NotifyResponseBody;
import com.mod.loan.pay.kuaiqian.dto.pay.Pay2bankHead;
import com.mod.loan.pay.kuaiqian.dto.pay.Pay2bankOrder;
import com.mod.loan.pay.kuaiqian.dto.pay.Pay2bankRequest;
import com.mod.loan.pay.kuaiqian.dto.pay.RequestBody;
import com.mod.loan.pay.kuaiqian.dto.query.Pay2bankSearchHead;
import com.mod.loan.pay.kuaiqian.dto.query.Pay2bankSearchRequest;
import com.mod.loan.pay.kuaiqian.dto.query.Pay2bankSearchRequestParam;
import com.mod.loan.pay.kuaiqian.dto.query.SearchRequestBody;
import com.mod.loan.model.User;
import com.mod.loan.model.UserBank;
import com.mod.loan.util.ConstantUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 工具类
 *
 * @author zhiwei.ma
 */
public class CCSUtil {


    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 创建request
     *
     * @return
     */
    public static Pay2bankSearchRequest genSearchRequest(String membercode_head, String version) {
        Pay2bankSearchRequest request = new Pay2bankSearchRequest();
        Pay2bankSearchHead head = new Pay2bankSearchHead();
        head.setMemberCode(membercode_head);
        head.setVersion(version);
        SearchRequestBody requestBody = new SearchRequestBody();
        SealDataType sealDataType = new SealDataType();
        requestBody.setSealDataType(sealDataType);
        request.setPay2bankSearchHead(head);
        request.setSearchRequestBody(requestBody);
        return request;
    }

    /**
     * 创建request
     *
     * @return
     */
    public static Pay2bankRequest genPayRequest(String membercode_head, String version) {
        Pay2bankRequest request = new Pay2bankRequest();
        Pay2bankHead head = new Pay2bankHead();
        head.setMemberCode(membercode_head);
        head.setVersion(version);
        RequestBody requestBody = new RequestBody();
        SealDataType sealDataType = new SealDataType();
        requestBody.setSealDataType(sealDataType);
        request.setPay2bankHead(head);
        request.setRequestBody(requestBody);
        return request;
    }

    /**
     * 生成一笔订单
     *
     * @return
     */
    public static Pay2bankOrder genOrder(User user, UserBank userBank, String amount, String serials_no) {
        Pay2bankOrder order = new Pay2bankOrder();
        //商家订单号 必填
        order.setOrderId(serials_no);
        //金额（分） 必填
        order.setAmount(String.valueOf((int) (Double.valueOf("1050.00") * 100)));
        //银行名称 必填
        order.setBankName(userBank.getCardName());
        //收款人姓名  必填
        order.setCreditName(user.getUserName());
        //收款人手机号  非必填
        order.setMobile(user.getUserPhone());
        //银行卡号 必填
        order.setBankAcctId(userBank.getCardNo());
        //备注 非必填
        //order.setRemark("模拟交易成功");
        //手续费作用方：0收款方付费1付款方付费  非必填 默认1
        order.setFeeAction(String.valueOf(ConstantUtils.ONE));
        return order;
    }


    /**
     * 生成一笔查询订单
     *
     * @return
     */
    public static Pay2bankSearchRequestParam genParam(User user, UserBank userBank, String amount, String serials_no) {
        Pay2bankSearchRequestParam order = new Pay2bankSearchRequestParam();
        //页码 必填 正整数
        order.setTargetPage(String.valueOf(ConstantUtils.ONE));
        //每页条数  必填  1-20  正整数
        order.setPageSize(String.valueOf(ConstantUtils.FIVE));
        //商家订单号
        order.setOrderId(serials_no);//test_20180322092536  test_20171120174007
        //金额（分）
        order.setAmount(String.valueOf((int) (Double.valueOf("1050.00") * 100)));
        //银行名称
        order.setBankName(userBank.getCardName());
        //收款人姓名
        order.setCreditName(user.getUserName());
        //银行卡号
        order.setBankAcctId(userBank.getCardNo());
        //开始时间 必填
        order.setStartDate(getTodayBeginTime(DATETIME_FORMAT)); //2017-11-19 08:12:12
        //结束时间 必填  结束-开始<=7天
        order.setEndDate(getTodayEndTime(DATETIME_FORMAT)); //2017-11-21 23:59:59
        return order;
    }


    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }


    /*
     * @Description:设置时间格式获取当天0点时间
     * @Param:
     * @return:
     * @Author: huijin.shuailijie
     * @Date: 2019/4/4
     */
    public static String getTodayBeginTime(String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(zero);
        return dateString;
    }


    /*
     * @Description:设置时间格式获取明天0点时间
     * @Param:
     * @return:
     * @Author: huijin.shuailijie
     * @Date: 2019/4/4
     */
    public static String getTodayEndTime(String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(zero);
        return dateString;
    }

    public static NotifyResponse genResponse(String membercode_head, String version) {
        NotifyResponse response = new NotifyResponse();
        NotifyHead head = new NotifyHead();
        head.setMemberCode(membercode_head);
        head.setVersion(version);
        NotifyResponseBody responseBody = new NotifyResponseBody();
        SealDataType sealDataType = new SealDataType();
        responseBody.setSealDataType(sealDataType);
        responseBody.setIsReceived("1");
        response.setNotifyHead(head);
        response.setNotifyResponseBody(responseBody);
        return response;
    }
}
