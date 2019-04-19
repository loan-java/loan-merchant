package com.mod.loan.controller.operation;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Notice;
import com.mod.loan.service.NoticeService;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "operation")
public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "notice_list")
	public ModelAndView notice_list(ModelAndView view) {
		view.setViewName("operation/notice_list");
		return view;
	}

	@RequestMapping(value = "notice_list_ajax", method = { RequestMethod.POST })
	public ResultMessage notice_list_ajax(Notice notice, Page page) {
		notice.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, noticeService.findNoticeList(notice, page), page);
	}

	@RequestMapping(value = "notice_edit")
	public ModelAndView notice_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("operation/notice_add");
		} else {
			view.addObject("id", id);
			view.setViewName("operation/notice_edit");
		}
		return view;
	}

	@RequestMapping(value = "notice_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage notice_edit_ajax(Notice notice) {
		notice.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isBlank(notice.getNoticeTitle())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "标题不能为空");
		}
		if (notice.getNoticeIdx() == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "排序不能为空");
		}
		if (notice.getId() == null) {
			notice.setCreateTime(new Date());
			noticeService.insertSelective(notice);
		} else {
			noticeService.updateByPrimaryKeySelective(notice);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "notice_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage notice_detail_ajax(Notice notice) {
		notice.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, noticeService.selectOne(notice));
	}

}
