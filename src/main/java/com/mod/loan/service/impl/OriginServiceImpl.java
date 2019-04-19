package com.mod.loan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.MerchantOriginMapper;
import com.mod.loan.model.MerchantOrigin;
import com.mod.loan.service.OriginService;

@Service
public class OriginServiceImpl extends BaseServiceImpl<MerchantOrigin, Long> implements OriginService {

    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private MerchantOriginMapper merchantOriginMapper;

    @Override
    public List<MerchantOrigin> findOriginListByManagerId(Long managerId) {
        return merchantOriginMapper.findOriginListByManagerId(managerId);
    }

    @Override
    public void updateManagerOrigin(Long managerId, List<Long> ids) {
        // 如果一个都没选中，则删除所有关联关系
        if (ids.size() == 0) {
            merchantOriginMapper.deleteManagerOrigin(managerId, null);
            return;
        }
        // 删除未选择的渠道，保留已选择的渠道
        merchantOriginMapper.deleteManagerOrigin(managerId, ids);
        List<MerchantOrigin> list = merchantOriginMapper.findOriginListByManagerId(managerId);
        Map<Long, Long> map = new HashMap<>();
        list.forEach(item -> {
            map.put(item.getId(), item.getId());
        });
        List<Long> new_ids = new ArrayList<>();
        for (Long origin_id : ids) {
            if (!map.containsKey(origin_id)) {
                new_ids.add(origin_id);
            }
        }
        if (new_ids.size() > 0) {
            merchantOriginMapper.insertManagerOrigin(managerId, new_ids);
        }
    }

    @Override
    public List<Map<String, Object>> findOriginRegisterByManagerId(Long originId, Long managerId, String startTime, String endTime, Page page) {
        Map<String, Object> param = new HashMap<>();
        param.put("originId", originId);
        param.put("managerId", managerId);
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(merchantOriginMapper.countOriginRegisterByManagerId(param));
        return merchantOriginMapper.findOriginRegisterByManagerId(param);
    }

    @Override
    public List<Map<String, Object>> findOriginList(Map<String, Object> param, Page page) {
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        page.setTotalCount(merchantOriginMapper.merchantOriginCount(param));
        return merchantOriginMapper.findOriginList(param);
    }

    @Override
    public List<Map<String, Object>> findCurrentOriginStatisticsList(Map<String, Object> param, Page page) {
        param.put("startIndex",page.getStartIndex());
        param.put("pageSize",page.getPageSize());
        page.setTotalCount(merchantOriginMapper.countCurrentOriginStatistics(param));
        return merchantOriginMapper.findCurrentOriginStatisticsList(param);
    }

    @Override
    public List<Map<String, Object>> findOriginStatistics(Map<String, Object> param) {
        List<Map<String, Object>> data = redisMapper.get(RedisConst.CURRENT_ORIGIN_TWO_DAYS + param.get("merchant"), new TypeReference<List<Map<String, Object>>>() {
        });
        if (data == null) {
            data = merchantOriginMapper.findOriginStatistics(param);
            redisMapper.set(RedisConst.CURRENT_ORIGIN_TWO_DAYS + param.get("merchant"), data, 60);
        }
        return data;
    }

    @Override
    public List<Map<String, Object>> findOriginOrderList(Map<String, Object> param) {
        String key = "%s:%s:%s:%s";
        key = String.format(key, param.get("merchant"), param.get("userType"), param.get("userOrigin"), param.get("startTime"), param.get("endTime"));
        List<Map<String, Object>> data = redisMapper.get(RedisConst.ORIGIN_ORDER_STATISTICS + key, new TypeReference<List<Map<String, Object>>>() {
        });
        if (data == null) {
            data = merchantOriginMapper.findOriginOrderList(param);
            redisMapper.set(RedisConst.ORIGIN_ORDER_STATISTICS + key, data, 180);
        }
        return data;
    }

}
