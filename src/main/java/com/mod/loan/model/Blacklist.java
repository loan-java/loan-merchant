package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.mod.loan.util.TimeUtils;

@Table(name = "tb_blacklist")
public class Blacklist {
    @Id
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    private String merchant;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 身份证
     */
    @Column(name = "cert_no")
    private String certNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 类型 1:灰名单(失效时间动态化） 2:永久黑名单  0:正常
     */
    private Integer type;

    /**
     * 标签，1-正常/2-老赖/3-代偿/4-特殊行业/5-学生/6-高负债/7-欺诈(欠款本人欺诈) /8-非本人(身份信息被冒用)/ 9-故意拖欠/10-疾病或死亡/11-其他
     */
    private Integer tag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 失效时间
     */
    @Column(name = "invalid_time")
    @DateTimeFormat(pattern = TimeUtils.dateformat1)
    private Date invalidTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * @param merchant
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }

    /**
     * 获取手机号
     *
     * @return tel - 手机号
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置手机号
     *
     * @param tel 手机号
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 获取身份证
     *
     * @return cert_no - 身份证
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * 设置身份证
     *
     * @param certNo 身份证
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类型 1:灰名单(失效时间动态化） 2:永久黑名单  0:正常
     *
     * @return type - 类型 1:灰名单(失效时间动态化） 2:永久黑名单  0:正常
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型 1:灰名单(失效时间动态化） 2:永久黑名单  0:正常
     *
     * @param type 类型 1:灰名单(失效时间动态化） 2:永久黑名单  0:正常
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取标签，1-正常/2-老赖/3-代偿/4-特殊行业/5-学生/6-高负债/7-欺诈(欠款本人欺诈) /8-非本人(身份信息被冒用)/ 9-故意拖欠/10-疾病或死亡/11-其他
     *
     * @return tag - 标签，1-正常/2-老赖/3-代偿/4-特殊行业/5-学生/6-高负债/7-欺诈(欠款本人欺诈) /8-非本人(身份信息被冒用)/ 9-故意拖欠/10-疾病或死亡/11-其他
     */
    public Integer getTag() {
        return tag;
    }

    /**
     * 设置标签，1-正常/2-老赖/3-代偿/4-特殊行业/5-学生/6-高负债/7-欺诈(欠款本人欺诈) /8-非本人(身份信息被冒用)/ 9-故意拖欠/10-疾病或死亡/11-其他
     *
     * @param tag 标签，1-正常/2-老赖/3-代偿/4-特殊行业/5-学生/6-高负债/7-欺诈(欠款本人欺诈) /8-非本人(身份信息被冒用)/ 9-故意拖欠/10-疾病或死亡/11-其他
     */
    public void setTag(Integer tag) {
        this.tag = tag;
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
     * 获取失效时间
     *
     * @return invalid_time - 失效时间
     */
    public Date getInvalidTime() {
        return invalidTime;
    }

    /**
     * 设置失效时间
     *
     * @param invalidTime 失效时间
     */
    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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
}