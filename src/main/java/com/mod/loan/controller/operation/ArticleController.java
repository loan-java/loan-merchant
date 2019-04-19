package com.mod.loan.controller.operation;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Article;
import com.mod.loan.service.ArticleService;
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
public class ArticleController {

	public static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "article_list")
	public ModelAndView article_list(ModelAndView view) {
		view.setViewName("operation/article_list");
		return view;
	}
	
	@RequestMapping(value = "article_choose_list")
	public ModelAndView article_choose_list(ModelAndView view, Long typeId) {
		view.addObject("typeId", typeId);
		view.setViewName("operation/article_choose_list");
		return view;
	}

	@RequestMapping(value = "article_list_ajax", method = { RequestMethod.POST })
	public ResultMessage article_list_ajax(Article article, Page page) {
		article.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, articleService.findArticleList(article, page), page);
	}

	@RequestMapping(value = "article_edit")
	public ModelAndView article_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("operation/article_add");
		} else {
			view.addObject("id", id);
			view.setViewName("operation/article_edit");
		}
		return view;
	}

	@RequestMapping(value = "article_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage article_edit_ajax(Article article) {
		article.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isBlank(article.getArticleTitle())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "标题不能为空");
		}
		if (StringUtils.isBlank(article.getArticleContent())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "内容不能为空");
		}
		if (article.getId() == null) {
			articleService.insertSelective(article);
		} else {
			articleService.updateByPrimaryKeySelective(article);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "article_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage article_detail_ajax(Article article) {
		article.setMerchant(RequestThread.get().getMerchant());
		return new ResultMessage(ResponseEnum.M2000, articleService.selectOne(article));
	}
	
}
