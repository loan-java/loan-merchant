package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_app_feedback")
public class Feedback {
    @Id
    private Long id;

    private Long uid;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "question_desc")
    private String questionDesc;

    /**
     * 链接之间“|”隔开
     */
    @Column(name = "question_img")
    private String questionImg;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "merchant")
    private String merchant;
    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return question_type
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * @param questionType
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    /**
     * @return question_desc
     */
    public String getQuestionDesc() {
        return questionDesc;
    }

    /**
     * @param questionDesc
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc == null ? null : questionDesc.trim();
    }

    /**
     * 获取链接之间“|”隔开
     *
     * @return question_img - 链接之间“|”隔开
     */
    public String getQuestionImg() {
        return questionImg;
    }

    /**
     * 设置链接之间“|”隔开
     *
     * @param questionImg 链接之间“|”隔开
     */
    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg == null ? null : questionImg.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
    
}