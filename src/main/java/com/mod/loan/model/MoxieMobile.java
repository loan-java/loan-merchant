package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_moxie_mobile")
public class MoxieMobile {
    @Id
    private Long id;

    private Long uid;

    /**
     * 魔蝎唯一标识
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     */
    private String status;

    /**
     * 加密请求报文，用来显示报告
     */
    private String message;


    private String phone;

    /**
     * 备注
     */
    private String remark;

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
     * 获取魔蝎唯一标识
     *
     * @return task_id - 魔蝎唯一标识
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置魔蝎唯一标识
     *
     * @param taskId 魔蝎唯一标识
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 获取task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     *
     * @return status - task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     *
     * @param status task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取加密请求报文，用来显示报告
     *
     * @return message - 加密请求报文，用来显示报告
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置加密请求报文，用来显示报告
     *
     * @param message 加密请求报文，用来显示报告
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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