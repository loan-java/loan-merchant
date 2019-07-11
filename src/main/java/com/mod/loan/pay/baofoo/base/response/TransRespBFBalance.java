package com.mod.loan.pay.baofoo.base.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * loan-pay 2019/4/23 huijin.shuailijie Init
 */
@XStreamAlias("trans_reqData")
public class TransRespBFBalance {

    private String account_type;
    private String currency;
    private Double balance;


    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
