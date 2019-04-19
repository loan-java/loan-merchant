package com.mod.loan.controller.recycle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.RecycleGroup;
import com.mod.loan.service.RecycleGroupService;


/**
 * Created by chenanle on 2018/6/28.
 */
@RestController
@RequestMapping(value = "recycle")
public class RecycleGroupController {

    @Autowired
    private RecycleGroupService recycleGroupService;

    @RequestMapping(value = "recycle_group_list")
    public ModelAndView recycle_group_list(ModelAndView view) {
        view.setViewName("recycle/recycle_group_list");
        return view;
    }

    @RequestMapping(value = "recycle_group_list_ajax", method = {RequestMethod.POST})
    public ResultMessage recycle_group_list_ajax(RecycleGroup recycleGroup, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("groupName", StringUtils.isNotEmpty(recycleGroup.getGroupName()) ? recycleGroup.getGroupName() : null);
        param.put("merchant", RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, recycleGroupService.findRecycleGroupList(param, page), page);
    }


    @RequestMapping(value = "recycle_group_edit")
    public ModelAndView modelAndView(ModelAndView view, Long id) {
        if (id == null) {
            view.setViewName("recycle/recycle_group_add");
        } else {
            view.addObject("id", id);
            view.setViewName("recycle/recycle_group_edit");
        }
        return view;
    }

    @RequestMapping(value = "recycle_group_edit_ajax", method = {RequestMethod.POST})
    public ResultMessage recycle_group_edit_ajax(RecycleGroup recycleGroup) {
        if (StringUtils.isBlank(recycleGroup.getGroupName())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "请输入组名");
        }
        recycleGroup.setMerchant(RequestThread.get().getMerchant());
        if (recycleGroup.getId() == null) {
            recycleGroup.setGroupName(recycleGroup.getGroupName());
            recycleGroup.setCreateTime(new Date());
            recycleGroupService.insertSelective(recycleGroup);
        } else {
            recycleGroupService.updateByPrimaryKeySelective(recycleGroup);
        }
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "recycle_group_detail_ajax")
    public ResultMessage recycle_group_detail_ajax(RecycleGroup recycleGroup) {
        recycleGroup.setMerchant(RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, recycleGroupService.selectOne(recycleGroup));
    }

    @RequestMapping(value = "group_list_ajax", method = {RequestMethod.POST})
    public ResultMessage group_list_ajax() {
        RecycleGroup recycleGroup = new RecycleGroup();
        recycleGroup.setMerchant(RequestThread.get().getMerchant());
        recycleGroup.setStatus(0);
        return new ResultMessage(ResponseEnum.M2000, recycleGroupService.select(recycleGroup));
    }
}
