package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order_recycle_log")
public class OrderRecycleLog {
    @Id
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 商户
     */
    private String merchant;

    /**
     * 催收人id
     */
    @Column(name = "follow_user_id")
    private Long followUserId;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取商户
     *
     * @return merchant - 商户
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * 设置商户
     *
     * @param merchant 商户
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }

    

    public Long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Long followUserId) {
		this.followUserId = followUserId;
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
}