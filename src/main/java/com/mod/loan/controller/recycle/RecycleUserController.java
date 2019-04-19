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
import com.mod.loan.model.RecycleUser;
import com.mod.loan.model.dto.RecycleUserDto;
import com.mod.loan.service.RecycleUserService;

/**
 * Created by chenanle on 2018/6/28.
 */
@RestController
@RequestMapping(value = "recycle")
public class RecycleUserController {

    @Autowired
    private RecycleUserService recycleUserService;

    @RequestMapping(value = "recycle_user_list")
    public ModelAndView recycle_group_user_list(ModelAndView view) {
        view.setViewName("recycle/recycle_user_list");
        return view;
    }

    @RequestMapping(value = "recycle_user_list_ajax", method = {RequestMethod.POST})
    public ResultMessage recycle_group_user_list_ajax(String recycleName, String groupName, Page page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("recycleName", StringUtils.isNotEmpty(recycleName) ? recycleName : null);
        param.put("groupName", StringUtils.isNotEmpty(groupName) ? groupName : null);
        param.put("merchant", RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, recycleUserService.findRecycleUserList(param, page), page);
    }

    @RequestMapping(value = "real_recycle_user_list_ajax", method = {RequestMethod.POST})
    public ResultMessage real_recycle_user_list_ajax() {
        return new ResultMessage(ResponseEnum.M2000, recycleUserService.findRealRecycleUserList(RequestThread.get().getMerchant()));
    }

    /**
     * 催收组长分配
     *
     * @return
     */
    @RequestMapping(value = "fenpei_recycle_user_list_ajax", method = {RequestMethod.POST})
    public ResultMessage fenpei_recycle_user_list_ajax() {
        Map<String, Object> param = new HashMap<>();
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("uid", RequestThread.get().getUid());
        return new ResultMessage(ResponseEnum.M2000, recycleUserService.findFenPeiRecycleUserList(param));
    }

    @RequestMapping(value = "recycle_user_edit")
    public ModelAndView recycle_user_edit(ModelAndView view, Long id) {
        if (id == null) {
            view.setViewName("recycle/recycle_user_add");
        } else {
            view.addObject("id", id);
            view.setViewName("recycle/recycle_user_edit");
        }
        return view;
    }

    @RequestMapping(value = "recycle_user_edit_ajax", method = {RequestMethod.POST})
    public ResultMessage recycle_user_edit_ajax(RecycleUserDto recycleUserDto) {
        if (recycleUserDto.getGroupId() == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "催收组名不能为空");
        }
        if (recycleUserDto.getFollowUserId() == null) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "催收人员不能为空");
        }
        RecycleUser recycleUser = new RecycleUser();
        recycleUser.setFollowUserId(recycleUserDto.getFollowUserId());
        recycleUser.setMerchant(RequestThread.get().getMerchant());
        recycleUser.setGroupId(recycleUserDto.getGroupId());
        recycleUser.setRemark(recycleUserDto.getRemark());
        recycleUser.setStatus(recycleUserDto.getStatus());
        if (recycleUserDto.getId() == null) {
            RecycleUser record = new RecycleUser();
            record.setFollowUserId(recycleUser.getFollowUserId());
            record.setMerchant(recycleUser.getMerchant());
            if (recycleUserService.selectOne(record) != null) {
                return new ResultMessage(ResponseEnum.M4000.getCode(), "该人员已加入组请勿多次添加");
            } else {
                recycleUser.setCreateTime(new Date());
                recycleUserService.insertSelective(recycleUser);
            }
        } else {
            recycleUser.setId(recycleUserDto.getId());
            recycleUserService.updateByPrimaryKeySelective(recycleUser);
        }
        return new ResultMessage(ResponseEnum.M2000);
    }

    @RequestMapping(value = "recycle_user_detail_ajax")
    public ResultMessage recycle_user_detail_ajax(RecycleUserDto recycleUserDto) {
        recycleUserDto.setMerchant(RequestThread.get().getMerchant());
        return new ResultMessage(ResponseEnum.M2000, recycleUserService.findRecycleUserDetail(recycleUserDto));
    }

    @RequestMapping(value = "recycle_user_select")
    public ModelAndView recycle_user_select(ModelAndView view) {
        view.setViewName("recycle/recycle_user_select");
        return view;
    }

    @RequestMapping(value = "recycle_login_record")
    public ModelAndView recycle_login_record(ModelAndView view,Long followUserId) {
        view.addObject("followUserId",followUserId);
        view.setViewName("recycle/recycle_login_record");
        return view;
    }

    @RequestMapping(value = "recycle_login_record_ajax")
    public ResultMessage recycle_login_record_ajax(Long followUserId) {
        Map<String,Object> param = new HashMap<>();
        param.put("merchant",RequestThread.get().getMerchant());
        param.put("followUserId",followUserId);
        return new ResultMessage(ResponseEnum.M2000,recycleUserService.findRecycleLoginRecord(param));
    }
}
