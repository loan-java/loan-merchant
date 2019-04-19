package com.mod.loan.controller.question;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.QuestionRef;
import com.mod.loan.service.QuestionRefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "question")
public class QuestionRefController {

	public static final Logger logger = LoggerFactory.getLogger(QuestionRefController.class);

	@Autowired
	private QuestionRefService questionRefService;

	@RequestMapping(value = "question_ref_list")
	public ModelAndView question_ref_list(ModelAndView view, Long typeId) {
		view.addObject("typeId", typeId);
		view.setViewName("question/question_ref_list");
		return view;
	}

	@RequestMapping(value = "question_ref_list_ajax", method = { RequestMethod.POST })
	public ResultMessage question_ref_list_ajax(QuestionRef questionRef, Page page) {
		return new ResultMessage(ResponseEnum.M2000, questionRefService.findQuestionRefList(questionRef, page), page);
	}
	
	@RequestMapping(value = "question_ref_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage question_ref_edit_ajax(QuestionRef questionRef, Long[] articleIds) {
		if (questionRef.getId() == null) {
			if (articleIds == null || articleIds.length == 0) {
				return new ResultMessage(ResponseEnum.M4000.getCode(), "请选择问题。");
			}
			Long typeId = questionRef.getTypeId();
			// 记录选取失败的个数。
			int failNum = 0;
			// 最大顺序号
			Integer maxIdx = questionRefService.selectMaxIdxByTypeId(typeId);
			maxIdx = maxIdx == null ? 0 : maxIdx;
			for (int i = 0; i < articleIds.length; i++) {
				try {
					questionRef = new QuestionRef();
					questionRef.setTypeId(typeId);
					questionRef.setArticleId(articleIds[i]);
					questionRef.setIdx(++maxIdx);
					questionRefService.insertSelective(questionRef);
				} catch (Exception e) {
					failNum++;
					maxIdx--;
					logger.error("questionRef新增失败。", e);
				}
			}
			if (failNum == 0) {
				return new ResultMessage(ResponseEnum.M2000);
			} else {
				int successNum = articleIds.length - failNum;
				return new ResultMessage(ResponseEnum.M4000.getCode(), "选取成功" + successNum + "个，失败" + failNum + "个。");
			}
		} else {
			questionRefService.updateByPrimaryKeySelective(questionRef);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}
	
	@RequestMapping(value = "question_ref_del_ajax", method = { RequestMethod.POST })
	public ResultMessage question_ref_del_ajax(QuestionRef questionRef) {
		questionRefService.deleteByPrimaryKey(questionRef.getId());
		return new ResultMessage(ResponseEnum.M2000);
	}
	
}
