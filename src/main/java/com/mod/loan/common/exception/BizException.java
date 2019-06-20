package com.mod.loan.common.exception;

import com.mod.loan.common.enums.ResponseEnum;
import org.apache.commons.lang.StringUtils;

/**
 * @ author liujianjian
 * @ date 2019/5/15 22:20
 */
public class BizException extends Exception {

    private String code;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
        this.code = ResponseEnum.M80000.getCode();
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ResponseEnum r) {
        super(r.getMessage());
        this.code = r.getCode();
    }

    public int getCodeInt() {
        return StringUtils.isNumeric(getCode()) ? Integer.parseInt(getCode()) : -1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
