package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.LoginRecordMapper;
import com.mod.loan.mapper.ManagerMapper;
import com.mod.loan.model.Manager;
import com.mod.loan.service.ManagerService;
import com.mod.loan.util.HttpUtils;

/**
 * @author wgy
 */
@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager, Long> implements ManagerService {

    public static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private LoginRecordMapper loginRecordMapper;

    @Override
    public List<Map<String, Object>> findManagerList(Manager manager, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("accountStatus", manager.getAccountStatus());
        param.put("merchant", manager.getMerchant());
        param.put("startIndex", page.getStartIndex());
        param.put("pageSize", page.getPageSize());
        param.put("roleId", manager.getRoleId());
        page.setTotalCount(managerMapper.managerCount(param));
        return managerMapper.findManagerList(param);
    }

    @Override
    public boolean verifyIp(Manager manager, String ip) {
        try {
			List<String> ips = loginRecordMapper.selectRecentlyIps(manager.getId(), manager.getMerchant());
            if (ips.size() != 0 && ips.contains(ip)) {
                return true;
            }
            if (HttpUtils.getIPAddressBaiDu(ip).equals(HttpUtils.getIPAddressBaiDu(manager.getLastLoginIp()))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("ip验证异常。uid={},ip={}", manager.getId(), ip);
            logger.error("ip验证异常。", e);
        }
        return false;
    }

	@Override
	public Manager selectById(Long id) {
		return managerMapper.selectById(id);
	}
}
