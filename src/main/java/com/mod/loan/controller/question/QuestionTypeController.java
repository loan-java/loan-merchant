package com.mod.loan.controller.question;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.QuestionType;
import com.mod.loan.service.QuestionTypeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "question")
public class QuestionTypeController {

	public static final Logger logger = LoggerFactory.getLogger(QuestionTypeController.class);

	@Autowired
	private QuestionTypeService questionTypeService;

	@RequestMapping(value = "question_type_list")
	public ModelAndView question_type_list(ModelAndView view) {
		view.setViewName("question/question_type_list");
		return view;
	}

	@RequestMapping(value = "question_type_list_ajax", method = { RequestMethod.POST })
	public ResultMessage question_type_list_ajax(QuestionType questionType, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("merchant",RequestThread.get().getMerchant());
		param.put("status", questionType.getStatus() != null ? questionType.getStatus() : null);
		return new ResultMessage(ResponseEnum.M2000, questionTypeService.findQuestionTypeList(param, page), page);
	}
	
	@RequestMapping(value = "question_type_edit")
	public ModelAndView question_type_edit(ModelAndView view, Long id) {
		if (id == null) {
			view.setViewName("question/question_type_add");
		} else {
			view.addObject("id", id);
			view.setViewName("question/question_type_edit");
		}
		return view;
	}

	@RequestMapping(value = "question_type_edit_ajax", method = { RequestMethod.POST })
	public ResultMessage question_type_edit_ajax(QuestionType questionType) {
		questionType.setMerchant(RequestThread.get().getMerchant());
		if (StringUtils.isBlank(questionType.getName())) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "名称不能为空");
		}
		if (questionType.getIdx() == null) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "排序不能为空");
		}
		if (questionType.getId() == null) {
			questionTypeService.insertSelective(questionType);
		} else {
			questionTypeService.updateByPrimaryKeySelective(questionType);
		}
		return new ResultMessage(ResponseEnum.M2000);
	}

	@RequestMapping(value = "question_type_detail_ajax", method = { RequestMethod.POST })
	public ResultMessage question_type_detail_ajax(Long id) {
		return new ResultMessage(ResponseEnum.M2000, questionTypeService.selectByPrimaryKey(id));
	}
	
}
