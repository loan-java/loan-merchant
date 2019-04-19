package com.mod.loan.controller.operation;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Banner;
import com.mod.loan.service.BannerService;

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
public class BannerController {

	public static final Logger logger = LoggerFactory.getLogger(BannerController.class);

	@Autowired
	private BannerService bannerService;

	@RequestMapping(value = "banner_list")
	public ModelAndView banner_list(ModelAndView view) {
		view.setViewName("operation/banner_list");
		return view;
	}

	@RequestMapping(value = "banner_list_ajax", method = { RequestMethod.POST })
	public ResultMessage banner_list_ajax(Banner banner, Page page) {
		banner.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, bannerService.findBannerList(banner, page), page);
	}

	@RequestMapping(value = "banner_edit")
	public ModelAndView banner_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("operation/banner_add");
		} else {
			view.addObject("id", id);
			view.setViewName("operation/banner_edit");
		}
		return view;
	}

	@RequestMapping(value = "banner_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage banner_edit_ajax(Banner banner) {
		banner.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isBlank(banner.getBannerImgurl())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "banner图片不能为空");
		}
		if (banner.getBannerIdx() == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "排序不能为空");
		}
		if (banner.getId() == null) {
			banner.setCreateTime(new Date());
			bannerService.insertSelective(banner);
		} else {
			bannerService.updateByPrimaryKeySelective(banner);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "banner_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage banner_detail_ajax(Banner banner) {
		banner.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, bannerService.selectOne(banner));
	}

}
