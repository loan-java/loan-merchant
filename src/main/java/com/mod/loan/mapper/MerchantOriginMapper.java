package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MerchantOrigin;

public interface MerchantOriginMapper extends MyBaseMapper<MerchantOrigin> {

    List<MerchantOrigin> findOriginListByManagerId(Long managerId);

    /**
     * 删除渠道账户关联
     *
     * @param managerId
     * @param ids
     */
    void deleteManagerOrigin(@Param("managerId") Long managerId, @Param("ids") List<Long> ids);

    /**
     * 插入渠道账户关联
     *
     * @param managerId
     * @param ids
     */
    void insertManagerOrigin(@Param("managerId") Long managerId, @Param("ids") List<Long> ids);

    /**
     * 获取后台账号关联的渠道，用户注册记录
     *
     * @param
     * @return
     */
    List<Map<String, Object>> findOriginRegisterByManagerId(Map<String, Object> param);

    List<Map<String, Object>> findCurrentOriginStatisticsList(Map<String, Object> param);

    int countCurrentOriginStatistics(Map<String, Object> param);

    int countOriginRegisterByManagerId(Map<String, Object> param);

    List<Map<String, Object>> findOriginList(Map<String, Object> param);

    int merchantOriginCount(Map<String, Object> param);

    List<Map<String, Object>> findOriginStatistics(Map<String, Object> param);

    List<Map<String, Object>> findOriginOrderList(Map<String, Object> param);

}