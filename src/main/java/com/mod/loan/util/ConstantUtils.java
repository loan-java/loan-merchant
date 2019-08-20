package com.mod.loan.util;

/**
 * loan-pay 2019/4/23 huijin.shuailijie Init
 */
public class ConstantUtils {

    public final static String MAK = "&";//分隔符

    public final static String BAOFOO_SUCCESSCODE = "0000";
    public final static int ZERO = 0;
    public final static int ONE = 1; //融泽
    public final static int TWO = 2; //蹦蹦
    public final static int THREE = 3;
    public final static int FOUR = 4;
    public final static int FIVE = 5;
    public final static int AUDIT_ORDER = 12;//人工审核
    public final static int LOAN_ORDER = 22;//放款中
    public final static int LOAN_FAIL_ORDER = 23;//放款异常
    public final static int LOAN_SUCCESS_ORDER = 31;//已放款
    public final static int LOAN_ORDER_OVERDUE = 33;//逾期
    public final static int LOAN_ORDER_BAD_DEBT = 34;//坏账
    public final static int LOAN_ORDER_NORMAL_REPAYMENT = 41;//正常还款
    public final static int LOAN_ORDER_OVERDUE_REPAYMENT = 42;//逾期还款
    public final static Double DEFAULT_BALANCE = 100000D;

}
