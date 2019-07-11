package com.mod.loan.pay.baofoo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * loan-pay 2019/4/20 huijin.shuailijie Init
 */
@Component
public class BaofooPayConfig {

    @Value("${baofoo.key.store.path:}")
    private String baofooKeyStorePath;


    @Value("${baofoo.key.store.password:}")
    private String baofooKeyStorePassword;


    @Value("${baofoo.pub.key.path:}")
    private String baofooPubKeyPath;


    @Value("${baofoo.pay.url:}")
    private String baofooPayUrl;


    @Value("${baofoo.query.url:}")
    private String baofooQueryUrl;


    @Value("${baofoo.balance.url:}")
    private String baofooBalanceUrl;


    @Value("${baofoo.member.id:}")
    private String baofooMemberId;

    @Value("${baofoo.terminal.id:}")
    private String baofooTerminalId;

    @Value("${baofoo.key.string:}")
    private String baofooKeyString;


    @Value("${baofoo.balance.terminal.id:}")
    private String baofooBalanceTerminalId;

    @Value("${baofoo.version:}")
    private String baofooVersion;

    @Value("${baofoo.repay.pri.key.path:}")
    private String baofooRepayPriKeyPath;

    @Value("${baofoo.repay.pub.key.path:}")
    private String baofooRepayPubKeyPath;

    @Value("${baofoo.repay.key.password:}")
    private String baofooRepayKeyPassword;

    @Value("${baofoo.repay.query.url:}")
    private String baofooRepayQueryUrl;

    @Value("${baofoo.repay.member.id:}")
    private String baofooRepayMemberId;

    @Value("${baofoo.repay.terminal.id:}")
    private String baofooRepayTerminalId;

    @Value("${baofoo.repay.version:}")
    private String baofooRepayVersion;

    public String getBaofooKeyStorePath() {
        return baofooKeyStorePath;
    }

    public void setBaofooKeyStorePath(String baofooKeyStorePath) {
        this.baofooKeyStorePath = baofooKeyStorePath;
    }

    public String getBaofooPubKeyPath() {
        return baofooPubKeyPath;
    }

    public void setBaofooPubKeyPath(String baofooPubKeyPath) {
        this.baofooPubKeyPath = baofooPubKeyPath;
    }

    public String getBaofooPayUrl() {
        return baofooPayUrl;
    }

    public void setBaofooPayUrl(String baofooPayUrl) {
        this.baofooPayUrl = baofooPayUrl;
    }

    public String getBaofooMemberId() {
        return baofooMemberId;
    }

    public void setBaofooMemberId(String baofooMemberId) {
        this.baofooMemberId = baofooMemberId;
    }

    public String getBaofooTerminalId() {
        return baofooTerminalId;
    }

    public void setBaofooTerminalId(String baofooTerminalId) {
        this.baofooTerminalId = baofooTerminalId;
    }

    public String getBaofooVersion() {
        return baofooVersion;
    }

    public void setBaofooVersion(String baofooVersion) {
        this.baofooVersion = baofooVersion;
    }

    public String getBaofooKeyStorePassword() {
        return baofooKeyStorePassword;
    }

    public void setBaofooKeyStorePassword(String baofooKeyStorePassword) {
        this.baofooKeyStorePassword = baofooKeyStorePassword;
    }

    public String getBaofooQueryUrl() {
        return baofooQueryUrl;
    }

    public void setBaofooQueryUrl(String baofooQueryUrl) {
        this.baofooQueryUrl = baofooQueryUrl;
    }

    public String getBaofooBalanceUrl() {
        return baofooBalanceUrl;
    }

    public void setBaofooBalanceUrl(String baofooBalanceUrl) {
        this.baofooBalanceUrl = baofooBalanceUrl;
    }

    public String getBaofooRepayPriKeyPath() {
        return baofooRepayPriKeyPath;
    }

    public void setBaofooRepayPriKeyPath(String baofooRepayPriKeyPath) {
        this.baofooRepayPriKeyPath = baofooRepayPriKeyPath;
    }

    public String getBaofooRepayPubKeyPath() {
        return baofooRepayPubKeyPath;
    }

    public void setBaofooRepayPubKeyPath(String baofooRepayPubKeyPath) {
        this.baofooRepayPubKeyPath = baofooRepayPubKeyPath;
    }

    public String getBaofooRepayKeyPassword() {
        return baofooRepayKeyPassword;
    }

    public void setBaofooRepayKeyPassword(String baofooRepayKeyPassword) {
        this.baofooRepayKeyPassword = baofooRepayKeyPassword;
    }

    public String getBaofooRepayQueryUrl() {
        return baofooRepayQueryUrl;
    }

    public void setBaofooRepayQueryUrl(String baofooRepayQueryUrl) {
        this.baofooRepayQueryUrl = baofooRepayQueryUrl;
    }

    public String getBaofooRepayMemberId() {
        return baofooRepayMemberId;
    }

    public void setBaofooRepayMemberId(String baofooRepayMemberId) {
        this.baofooRepayMemberId = baofooRepayMemberId;
    }

    public String getBaofooRepayTerminalId() {
        return baofooRepayTerminalId;
    }

    public void setBaofooRepayTerminalId(String baofooRepayTerminalId) {
        this.baofooRepayTerminalId = baofooRepayTerminalId;
    }

    public String getBaofooRepayVersion() {
        return baofooRepayVersion;
    }

    public void setBaofooRepayVersion(String baofooRepayVersion) {
        this.baofooRepayVersion = baofooRepayVersion;
    }

    public String getBaofooBalanceTerminalId() {
        return baofooBalanceTerminalId;
    }

    public void setBaofooBalanceTerminalId(String baofooBalanceTerminalId) {
        this.baofooBalanceTerminalId = baofooBalanceTerminalId;
    }

    public String getBaofooKeyString() {
        return baofooKeyString;
    }

    public void setBaofooKeyString(String baofooKeyString) {
        this.baofooKeyString = baofooKeyString;
    }
}
