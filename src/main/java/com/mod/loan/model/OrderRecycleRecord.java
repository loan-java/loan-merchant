package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order_recycle_record")
public class OrderRecycleRecord {
    @Id
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;


    @Column(name = "uid")
    private Long uid;

    /**
     * 商户
     */
    private String merchant;

    /**
     * 0-未催收，1-催收中，2-停止催收，3-完成催收
     */
    @Column(name = "follow_status")
    private Integer followStatus;

    /**
     * 催收人id
     */
    @Column(name = "follow_user_id")
    private Long followUserId;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 催收标签：0-其他1-承诺还款2-谈判-高负债3-谈判-还款意愿低4-无人接听5-关机6-无法接通7-设置8-通话中9-停机10-跳票11-家人代偿12-线下已还款13-失联（本人通讯录无效）14-拒绝还款'
     */
    @Column(name = "type")
    private Integer type;

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


    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    /**
     * 获取0-未催收，1-催收中，2-停止催收，3-完成催收
     *
     * @return follow_status - 0-未催收，1-催收中，2-停止催收，3-完成催收
     */
    public Integer getFollowStatus() {
        return followStatus;
    }

    /**
     * 设置0-未催收，1-催收中，2-停止催收，3-完成催收
     *
     * @param followStatus 0-未催收，1-催收中，2-停止催收，3-完成催收
     */
    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    /**
     * 获取催收人id
     *
     * @return follow_user_id - 催收人id
     */
    public Long getFollowUserId() {
        return followUserId;
    }

    /**
     * 设置催收人id
     *
     * @param followUserId 催收人id
     */
    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取催收标签
     *
     * @return
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置催收标签
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}