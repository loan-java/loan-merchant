package com.mod.loan.controller.operation;

import com.google.common.collect.Maps;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.mapper.AppEntryMapper;
import com.mod.loan.model.AppEntry;
import com.mod.loan.service.AppEntryService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by maxiang on 2017/9/26.
 */
@RestController
@RequestMapping(value = "operation")
public class EntryController {
	public static final Logger logger = LoggerFactory.getLogger(EntryController.class);

	@Autowired
	AppEntryMapper appEntryMapper;
	@Autowired
	AppEntryService appEntryService;

	@RequestMapping(value = "entry_list")
	public ModelAndView entry_list(ModelAndView view) {
		view.setViewName("operation/entry_list");
		return view;
	}

	@RequestMapping(value = "entry_list_ajax")
	public ResultMessage entry_list_ajax(Page page, String entryStatus) {
		Map<String, Object> queryParams = Maps.newHashMap();
		if (!StringUtils.isEmpty(entryStatus)) {
			queryParams.put("entryStatus", entryStatus);
		}
		queryParams.put("merchant", RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, appEntryService.findInfoList(page, queryParams), page);
	}

	@RequestMapping(value = "entry_edit")
	public ModelAndView entry_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("operation/entry_add");
		} else {
			view.addObject("id", id);
			view.setViewName("operation/entry_update");
		}
		return view;
	}

	@RequestMapping(value = "entry_edit_ajax")
	public ResultMessage entry_update_ajax(AppEntry appEntry) {
		appEntry.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isEmpty(appEntry.getEntryRemark())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请填写备注。");
		}
		if (appEntry.getEntryIdx() > 2147483647) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "请输入正确的顺序。");
		}
		if (appEntry.getId() == null) {
			appEntryMapper.insertSelective(appEntry);
		} else {
			appEntryMapper.updateByPrimaryKeySelective(appEntry);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "entry_detail_ajax")
	public ResultMessage entry_detail_ajax(AppEntry appEntry) {
		appEntry.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, appEntryMapper.selectOne(appEntry));
	}
}
