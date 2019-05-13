package com.mod.loan.kuaiqian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * loan-own-pay 2019/5/5 huijin.shuailijie Init
 */
@Component
public class KuaiqianPayConfig {

    @Value("${kuaiqian.member.code}")
    private String kuaiqianMemberCode;

    @Value("${kuaiqian.feture.code}")
    private String kuaiqianFetureCode;

    @Value("${kuaiqian.pay.url}")
    private String kuaiqianPayUrl;

    @Value("${kuaiqian.query.url}")
    private String kuaiqianQueryUrl;

    @Value("${kuaiqian.query.balance.url}")
    private String kuaiqianQueryBalanceUrl;

    @Value("${kuaiqian.version}")
    private String kuaiqianVersion;

    @Value("${kuaiqian.member.id}")
    private String kuaiqianMemberId;

    @Value("${kuaiqian.terminal.id}")
    private String kuaiqianTerminalId;

    @Value("${kuaiqian.repay.query.url}")
    private String kuaiqianRepayQueryUrl;

    @Value("${kuaiqian.function.key}")
    private String functionKey;

    @Value("${kuaiqian.query.balance.memberAcctCode}")
    private String queryBalanceMemberAcctCode;

    @Value("${kuaiqian.app.id}")
    private String appId;

    @Value("${kuaiqian.query.balance.service}")
    private String queryBalanceService;

    public String getKuaiqianMemberCode() {
        return kuaiqianMemberCode;
    }

    public void setKuaiqianMemberCode(String kuaiqianMemberCode) {
        this.kuaiqianMemberCode = kuaiqianMemberCode;
    }

    public String getKuaiqianFetureCode() {
        return kuaiqianFetureCode;
    }

    public void setKuaiqianFetureCode(String kuaiqianFetureCode) {
        this.kuaiqianFetureCode = kuaiqianFetureCode;
    }

    public String getKuaiqianPayUrl() {
        return kuaiqianPayUrl;
    }

    public void setKuaiqianPayUrl(String kuaiqianPayUrl) {
        this.kuaiqianPayUrl = kuaiqianPayUrl;
    }

    public String getKuaiqianQueryUrl() {
        return kuaiqianQueryUrl;
    }

    public void setKuaiqianQueryUrl(String kuaiqianQueryUrl) {
        this.kuaiqianQueryUrl = kuaiqianQueryUrl;
    }

    public String getKuaiqianVersion() {
        return kuaiqianVersion;
    }

    public void setKuaiqianVersion(String kuaiqianVersion) {
        this.kuaiqianVersion = kuaiqianVersion;
    }

    public String getKuaiqianQueryBalanceUrl() {
        return kuaiqianQueryBalanceUrl;
    }

    public void setKuaiqianQueryBalanceUrl(String kuaiqianQueryBalanceUrl) {
        this.kuaiqianQueryBalanceUrl = kuaiqianQueryBalanceUrl;
    }

    public String getFunctionKey() {
        return functionKey;
    }

    public void setFunctionKey(String functionKey) {
        this.functionKey = functionKey;
    }

    public String getQueryBalanceMemberAcctCode() {
        return queryBalanceMemberAcctCode;
    }

    public void setQueryBalanceMemberAcctCode(String queryBalanceMemberAcctCode) {
        this.queryBalanceMemberAcctCode = queryBalanceMemberAcctCode;
    }

    public String getQueryBalanceService() {
        return queryBalanceService;
    }

    public void setQueryBalanceService(String queryBalanceService) {
        this.queryBalanceService = queryBalanceService;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKuaiqianMemberId() {
        return kuaiqianMemberId;
    }

    public String getKuaiqianTerminalId() {
        return kuaiqianTerminalId;
    }

    public String getKuaiqianRepayQueryUrl() {
        return kuaiqianRepayQueryUrl;
    }
}
