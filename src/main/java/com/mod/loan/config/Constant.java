package com.mod.loan.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
public class Constant {

    public final static String cookie_token = "modid";

    /**
     * 订单状态：等待复审
     */
    public final static Integer ORDER_FOR_AUDITING = 12;

    /**
     * 订单状态：待放款
     */
    public final static Integer ORDER_FOR_LENDING = 21;

    /**
     * 订单状态：放款中
     */
    public final static Integer ORDER_IN_LENDING = 22;

    /**
     * 订单状态：放款失败
     */
    public final static Integer ORDER_LEND_FAIL = 23;

    /**
     * 订单状态：已放款/还款中
     */
    public final static Integer ORDER_IN_REPAYING = 31;

    /**
     * 订单状态：逾期
     */
    public final static Integer ORDER_IS_OVERDUE = 33;

    /**
     * 订单状态：坏账
     */
    public final static Integer ORDER_BAD_LOAN = 34;

    /**
     * 订单状态：正常还款
     */
    public final static Integer ORDER_REPAYED = 41;

    /**
     * 订单状态：逾期还款
     */
    public final static Integer ORDER_OVERDUE_REPAYED = 42;

    /**
     * 订单状态：复审失败
     */
    public final static Integer ORDER_AUDIT_FAIL = 52;

    /**
     * 订单状态：取消
     */
    public final static Integer ORDER_CANCLE = 53;

    public static String accesskey_id;

    public static String access_key_secret;

    public static String env;

    public static String img_prefix;

    public static String server_h5_url;

    public static String server_itf_url;

    public static String moxie_token;

    public static String moxie_zfb_zmscore;

    public static String jwt_sercetKey;

    public static String endpoint_out;

    public static String endpoint_in;

    public static String bucket_name;

    public static String baidu_ak_value;

    public static String baidu_sn_value;

    public static String moxie_bucket_name;

    public static String moxie_mobile_jxl;

    public static String moxie_mobile_mxdata;

    public static String moxie_zfb_data;

    public static String moxie_magic_wand;

    public static String moxie_address_list;

    public static String juheCallBackUrl;

    public static String payType;

    @Value("${pay.type}")
    public void setPayType(String payType) { Constant.payType = payType; }

    @Value("${juhe.call.back.url}")
    public void setJuheCallBackUrl(String juheCallBackUrl) {
        Constant.juheCallBackUrl = juheCallBackUrl;
    }

    @Value("${oss.moxie.bucket.name:}")
    public void setMoxie_bucket_name(String moxie_bucket_name) {
        Constant.moxie_bucket_name = moxie_bucket_name;
    }

    @Value("${oss.moxie.bucket.name.mobile_jxl:}")
    public void setMoxie_mobile_jxl(String moxie_mobile_jxl) {
        Constant.moxie_mobile_jxl = moxie_mobile_jxl;
    }

    @Value("${oss.moxie.bucket.name.mobile_mxdata:}")
    public void setMoxie_mobile_mxdata(String moxie_mobile_mxdata) {
        Constant.moxie_mobile_mxdata = moxie_mobile_mxdata;
    }

    @Value("${oss.moxie.bucket.name.zfb_data:}")
    public void setMoxie_zfb_data(String moxie_zfb_data) {
        Constant.moxie_zfb_data = moxie_zfb_data;
    }

    @Value("${oss.moxie.bucket.name.magic_wand:}")
    public void setMoxie_magic_wand(String moxie_magic_wand) {
        Constant.moxie_magic_wand = moxie_magic_wand;
    }

    @Value("${oss.moxie.bucket.name.address_list:}")
    public void setMoxie_address_list(String moxie_address_list) {
        Constant.moxie_address_list = moxie_address_list;
    }

    @Value("${baidu.ak.value:}")
    public void setBaidu_ak_value(String baidu_ak_value) {
        Constant.baidu_ak_value = baidu_ak_value;
    }

    @Value("${baidu.sn.value:}")
    public void setBaidu_sn_value(String baidu_sn_value) {
        Constant.baidu_sn_value = baidu_sn_value;
    }

    @Value("${oss.endpoint.out:}")
    public void setEndpoint_out(String endpoint_out) {
        Constant.endpoint_out = endpoint_out;
    }

    @Value("${oss.endpoint.in:}")
    public void setEndpoint_in(String endpoint_in) {
        Constant.endpoint_in = endpoint_in;
    }

    @Value("${oss.static.bucket.name:}")
    public void setBucket_name(String bucket_name) {
        Constant.bucket_name = bucket_name;
    }

    @Value("${environment:}")
    public void setEnv(String env) {
        Constant.env = env;
    }

    @Value("${img.prefix:}")
    public void setImg_prefix(String img_prefix) {
        Constant.img_prefix = img_prefix;
    }

    @Value("${server.h5.url:}")
    public void setServer_h5_url(String server_h5_url) {
        Constant.server_h5_url = server_h5_url;
    }

    @Value("${server.itf.url:}")
    public void setServer_itf_url(String server_itf_url) {
        Constant.server_itf_url = server_itf_url;
    }

    @Value("${moxie.token:}")
    public void setMoxie_token(String moxie_token) {
        Constant.moxie_token = moxie_token;
    }

    @Value("${moxie.zfb.zmscore:}")
    public void setMoxie_zfb_zmscore(String moxie_zfb_zmscore) {
        Constant.moxie_zfb_zmscore = moxie_zfb_zmscore;
    }

    @Value("${jwt.sercetKey:}")
    public void setJwt_sercetKey(String jwt_sercetKey) {
        Constant.jwt_sercetKey = jwt_sercetKey;
    }

    @Value("${oss.accesskey.id:}")
    public void setAccesskey_id(String accesskey_id) {
        Constant.accesskey_id = accesskey_id;
    }

    @Value("${oss.accesskey.secret:}")
    public void setAccess_key_secret(String access_key_secret) {
        Constant.access_key_secret = access_key_secret;
    }

    /**
     * 为thymeleaf添加全局静态变量
     *
     * @param viewResolver
     */
    @Bean
    public ThymeleafViewResolver configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("ALI_OSS_FILE_URL", img_prefix);
            vars.put("server_h5_url", server_h5_url);
            vars.put("server_itf_url", server_itf_url);
            viewResolver.setStaticVariables(vars);
        }
        return viewResolver;
    }

}
