package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user_address_list")
public class UserAddressList {
    @Id
    private Long uid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 通讯录信息，json:  c关系，n名字，p电话
     * [{"c":"","n":"110","p":"17326061209"},{"c":"","n":"客服助手","p":"18262054833"}]
     */
    @Column(name = "address_list")
    private String addressList;

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

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取通讯录信息，吴光宇^1586712,135|马翔^qsqsqs,sqws
     *
     * @return address_list - 通讯录信息，吴光宇^1586712,135|马翔^qsqsqs,sqws
     */
    public String getAddressList() {
        return addressList;
    }

    /**
     * 设置通讯录信息，吴光宇^1586712,135|马翔^qsqsqs,sqws
     *
     * @param addressList 通讯录信息，吴光宇^1586712,135|马翔^qsqsqs,sqws
     */
    public void setAddressList(String addressList) {
        this.addressList = addressList == null ? null : addressList.trim();
    }
}