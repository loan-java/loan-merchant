package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user_device")
public class UserDevice {
    @Id
    private Long id;

    private Long uid;

    /**
     * 设备id
     */
    private String deviceid;

    private String ip;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 城市
     */
    private String city;

    /**
     * 网络类型
     */
    @Column(name = "net_type")
    private String netType;

    /**
     * 手机品牌
     */
    @Column(name = "phone_brand")
    private String phoneBrand;

    /**
     * 手机型号
     */
    @Column(name = "phone_model")
    private String phoneModel;

    /**
     * 手机系统版本
     */
    @Column(name = "phone_system")
    private String phoneSystem;

    /**
     * 分辨率
     */
    @Column(name = "phone_resolution")
    private String phoneResolution;

    /**
     * 内存
     */
    @Column(name = "phone_memory")
    private String phoneMemory;

    /**
     * 运营商
     */
    private String isp;

    /**
     * 应用名称
     */
    @Column(name = "client_alias")
    private String clientAlias;

    /**
     * 应用版本
     */
    @Column(name = "client_version")
    private String clientVersion;

    /**
     * 创建时间
     */
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
     * 获取设备id
     *
     * @return deviceid - 设备id
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * 设置设备id
     *
     * @param deviceid 设备id
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取经纬度
     *
     * @return location - 经纬度
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置经纬度
     *
     * @param location 经纬度
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取网络类型
     *
     * @return net_type - 网络类型
     */
    public String getNetType() {
        return netType;
    }

    /**
     * 设置网络类型
     *
     * @param netType 网络类型
     */
    public void setNetType(String netType) {
        this.netType = netType == null ? null : netType.trim();
    }

    /**
     * 获取手机品牌
     *
     * @return phone_brand - 手机品牌
     */
    public String getPhoneBrand() {
        return phoneBrand;
    }

    /**
     * 设置手机品牌
     *
     * @param phoneBrand 手机品牌
     */
    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand == null ? null : phoneBrand.trim();
    }

    /**
     * 获取手机型号
     *
     * @return phone_model - 手机型号
     */
    public String getPhoneModel() {
        return phoneModel;
    }

    /**
     * 设置手机型号
     *
     * @param phoneModel 手机型号
     */
    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel == null ? null : phoneModel.trim();
    }

    /**
     * 获取手机系统版本
     *
     * @return phone_system - 手机系统版本
     */
    public String getPhoneSystem() {
        return phoneSystem;
    }

    /**
     * 设置手机系统版本
     *
     * @param phoneSystem 手机系统版本
     */
    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem == null ? null : phoneSystem.trim();
    }

    /**
     * 获取分辨率
     *
     * @return phone_resolution - 分辨率
     */
    public String getPhoneResolution() {
        return phoneResolution;
    }

    /**
     * 设置分辨率
     *
     * @param phoneResolution 分辨率
     */
    public void setPhoneResolution(String phoneResolution) {
        this.phoneResolution = phoneResolution == null ? null : phoneResolution.trim();
    }

    /**
     * 获取内存
     *
     * @return phone_memory - 内存
     */
    public String getPhoneMemory() {
        return phoneMemory;
    }

    /**
     * 设置内存
     *
     * @param phoneMemory 内存
     */
    public void setPhoneMemory(String phoneMemory) {
        this.phoneMemory = phoneMemory == null ? null : phoneMemory.trim();
    }

    /**
     * 获取运营商
     *
     * @return isp - 运营商
     */
    public String getIsp() {
        return isp;
    }

    /**
     * 设置运营商
     *
     * @param isp 运营商
     */
    public void setIsp(String isp) {
        this.isp = isp == null ? null : isp.trim();
    }

    /**
     * 获取应用名称
     *
     * @return client_alias - 应用名称
     */
    public String getClientAlias() {
        return clientAlias;
    }

    /**
     * 设置应用名称
     *
     * @param clientAlias 应用名称
     */
    public void setClientAlias(String clientAlias) {
        this.clientAlias = clientAlias == null ? null : clientAlias.trim();
    }

    /**
     * 获取应用版本
     *
     * @return client_version - 应用版本
     */
    public String getClientVersion() {
        return clientVersion;
    }

    /**
     * 设置应用版本
     *
     * @param clientVersion 应用版本
     */
    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion == null ? null : clientVersion.trim();
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
}