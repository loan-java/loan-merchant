package com.mod.loan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ author liujianjian
 * @ date 2019/5/11 21:23
 */
public class StrategyDTO implements Serializable {
    @JsonProperty("code")
    private String code;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("score")
    private String score;

    @JsonProperty("index")
    private String index;

    @JsonProperty("ruleResList")
    private List<RuleResDTO> ruleResList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<RuleResDTO> getRuleResList() {
        return ruleResList;
    }

    public void setRuleResList(List<RuleResDTO> ruleResList) {
        this.ruleResList = ruleResList;
    }

    public static class RuleResDTO implements Serializable {
        /**
         * 执行结果代码
         */
        @JsonProperty("code")
        private String code;
        /**
         * 执行结果描述
         */
        @JsonProperty("desc")
        private String desc;
        /**
         * 规则id
         */
        @JsonProperty("score")
        private String score;
        /**
         * 规则描述
         */
        @JsonProperty("labelValue")
        private Boolean labelValue;

        @JsonProperty("isDefaultVal")
        private String isDefaultVal;

        /**
         * 规则得分
         */
        @JsonProperty("rule_name")
        private String rule_name;

        /**
         * 规则得分
         */
        @JsonProperty("rule_id")
        private String rule_id;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public Boolean getLabelValue() {
            return labelValue;
        }

        public void setLabelValue(Boolean labelValue) {
            this.labelValue = labelValue;
        }

        public String getIsDefaultVal() {
            return isDefaultVal;
        }

        public void setIsDefaultVal(String isDefaultVal) {
            this.isDefaultVal = isDefaultVal;
        }

        public String getRule_name() {
            return rule_name;
        }

        public void setRule_name(String rule_name) {
            this.rule_name = rule_name;
        }

        public String getRule_id() {
            return rule_id;
        }

        public void setRule_id(String rule_id) {
            this.rule_id = rule_id;
        }
    }
}
