package com.mod.loan.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.enums.UserOriginEnum;
import com.mod.loan.common.model.Page;
import com.mod.loan.common.model.RequestThread;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.mapper.*;
import com.mod.loan.model.*;
import com.mod.loan.model.moxie.CallInContactList;
import com.mod.loan.model.moxie.CallOutContactList;
import com.mod.loan.model.moxie.ContactList;
import com.mod.loan.model.moxie.Linkmans;
import com.mod.loan.service.UserService;
import com.mod.loan.util.AliOssStaticUtil;
import com.mod.loan.util.ExcelUtil;
import com.mod.loan.util.TimeUtils;
import com.mod.loan.util.moxie.AddressListUtil;
import com.mod.loan.util.moxie.ContactUtil;
import com.mod.loan.util.moxie.MoxieOssUtil;
import com.mod.loan.util.rongze.RongZeRequestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserBankMapper userBankMapper;
    @Autowired
    private UserIdentMapper userIdentMapper;
    @Autowired
    private MoxieMobileMapper moxieMobileMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderAuditMapper orderAuditMapper;

    @Autowired
    private OrderUserMapper orderUserMapper;

    @RequestMapping(value = "user_select")
    public ModelAndView user_select(ModelAndView view) {
        view.setViewName("user/user_select");
        return view;
    }

    @RequestMapping(value = "user_list")
    public ModelAndView user_list(ModelAndView view) {
        view.setViewName("user/user_list");
        return view;
    }

    @RequestMapping(value = "user_list_ajax", method = {RequestMethod.POST})
    public ResultMessage user_list_ajax(User user, Page page, String startTime, String endTime) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userName", StringUtils.isNotEmpty(user.getUserName()) ? user.getUserName() : null);
        param.put("userPhone", StringUtils.isNotEmpty(user.getUserPhone()) ? user.getUserPhone() : null);
        param.put("merchant", RequestThread.get().getMerchant());
        param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
        param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);
        param.put("userOrigin", StringUtils.isNotEmpty(user.getUserOrigin()) ? user.getUserOrigin() : null);
        return new ResultMessage(ResponseEnum.M2000, userService.findUserList(param, page), page);
    }

    @RequestMapping(value = "user_detail")
    public ModelAndView user_detail(ModelAndView view, Long id) {
        view.addObject("id", id);
        view.setViewName("user/user_detail");
        return view;
    }

    @RequestMapping(value = "user_detail_ajax", method = {RequestMethod.POST})
    public ResultMessage user_detail_ajax(Long id) {
        Map<String, Object> data = new HashMap<>();
        User user = userService.selectByPrimaryKey(id);
        if (!user.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4004);
        }
        data.put("user", user);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        data.put("userInfo", userInfo);
        UserBank userBank = userBankMapper.selectOneByUid(id);
        data.put("userBank", userBank);
        UserIdent userIdent = userIdentMapper.selectByPrimaryKey(id);
        data.put("userIdent", userIdent);
        MoxieMobile moxieMobile = moxieMobileMapper.selectLastOne(id);
        data.put("moxieMobileTaskId", moxieMobile != null ? moxieMobile.getTaskId() : null);
        // 共债记录
        data.putAll(orderMapper.countDebtRecord(user.getUserPhone()));
        // 提单历史
        data.putAll(orderMapper.countUserOrderRecord(RequestThread.get().getMerchant(), id));
        return new ResultMessage(ResponseEnum.M2000, data);
    }

    @RequestMapping(value = "user_zfb_mxreport")
    public ModelAndView user_report_zfb(ModelAndView view, String taskId) {
        view.addObject("taskId", taskId);
        view.setViewName("user/user_zfb_mxreport");
        return view;
    }

    @RequestMapping(value = "user_zfb_mxreport_ajax")
    public String user_zfb_mxreport_ajax(String taskId) throws IOException {
        return MoxieOssUtil.zfbData(Constant.env, taskId);
    }

    @RequestMapping(value = "user_zfb_zmscore_ajax")
    public String user_zfb_zmscore_ajax(String taskId) throws IOException {
        String url = String.format(Constant.moxie_zfb_zmscore, taskId);
        Response response = Jsoup.connect(url).header("Authorization", "token " + Constant.moxie_token).ignoreHttpErrors(true).ignoreContentType(true).execute();
        return response.body();
    }

    @RequestMapping(value = "user_carrier_jxreport")
    public ModelAndView user_carrier_jxreport(ModelAndView view, String taskId) {
        view.addObject("taskId", taskId);
        view.setViewName("user/user_carrier_jxreport");
        return view;
    }

    @RequestMapping(value = "user_carrier_jxreport_ajax", method = {RequestMethod.POST})
    public ResultMessage user_carrier_jxreport_ajax(String taskId) {
        MoxieMobile moxieMobile = moxieMobileMapper.selectByTaskId(taskId);
        Map<String, Object> data = new HashMap<String, Object>();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(moxieMobile.getUid());
        String carrierReport = MoxieOssUtil.mobileJxlReport(Constant.env, taskId);
        data.put("userInfo", userInfo);
        JSONObject carrierReportJson = JSONObject.parseObject(carrierReport);
        // 获取呼入和呼出的通话记录信息
        String contact_list = carrierReportJson.getString("contact_list");
        carrierReportJson.remove("contact_list");// 去掉呼入和呼出的通话记录信息
        if (null != contact_list) {
            try {
                // 从itf接口获取通讯录数据
                String url = Constant.server_itf_url + "user/address_list?uid=" + userInfo.getUid();
                Response execute = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).execute();
                List<Linkmans> addressList = AddressListUtil.getLinkmans(execute.body());
                Map<String, String> map = AddressListUtil.getMap(addressList);
                List<ContactList> listContactList = JSON.parseArray(contact_list, ContactList.class);
                List<CallInContactList> callInList = ContactUtil.getCallInContactList(map, listContactList);
                List<CallOutContactList> callOutList = ContactUtil.getCallOutContactList(map, listContactList);
                data.put("callInList", callInList);
                data.put("callOutList", callOutList);
            } catch (IOException e) {
                logger.error("从itf获取通讯录失败，uid={}", userInfo.getUid());
            }
        }
        data.put("carrierReport", carrierReportJson);
        return new ResultMessage(ResponseEnum.M2000, data);
    }

    @RequestMapping(value = "user_magic_wand_report")
    public ModelAndView user_magic_wand_report(ModelAndView view, String taskId) {
        view.addObject("taskId", taskId);
        view.setViewName("user/user_magic_wand_report");
        return view;
    }

    @RequestMapping(value = "user_magic_wand_ajax")
    public String user_magic_wand_ajax(String taskId) throws IOException {
        String itfUrl = Constant.server_itf_url + "moxie/magic_wand_report?taskId=" + taskId;
        String data = "";
        try {
            Response execute = Jsoup.connect(itfUrl).ignoreContentType(true).ignoreHttpErrors(true).execute();
            data = execute.body();
        } catch (Exception e) {
            logger.error("oss获取魔杖报告异常", e);
        }
        return data;
    }

    @RequestMapping(value = "user_audit_list")
    public ModelAndView user_audit_list(ModelAndView view, Long id) {
        view.addObject("id", id);
        view.setViewName("user/user_audit_list");
        return view;
    }

    @RequestMapping(value = "user_audit_list_ajax")
    public ResultMessage user_audit_list_ajax(Long id) {
        return new ResultMessage(ResponseEnum.M2000, orderAuditMapper.findAuditListByUid(id, RequestThread.get().getMerchant()));
    }

    @RequestMapping(value = "user_recycle_list")
    public ModelAndView user_recycle_list(ModelAndView view, Long id) {
        view.addObject("id", id);
        view.setViewName("user/user_recycle_list");
        return view;
    }

    @RequestMapping(value = "user_basic_edit")
    public ModelAndView user_basic_edit(ModelAndView view, Long id) {
        view.addObject("id", id);
        view.setViewName("user/user_basic_edit");
        return view;
    }

    @RequestMapping(value = "user_basic_ajax", method = {RequestMethod.POST})
    public ResultMessage user_basic_ajax(Long id) {
        User user = userService.selectByPrimaryKey(id);
        if (user == null || !user.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000);
        }
        return new ResultMessage(ResponseEnum.M2000, user);
    }

    @RequestMapping(value = "user_basic_update_ajax", method = {RequestMethod.POST})
    public ResultMessage user_basic_update_ajax(User user) {
        if (user == null || !user.getMerchant().equals(RequestThread.get().getMerchant())) {
            return new ResultMessage(ResponseEnum.M4000.getCode(), "用户不存在");
        }
        return new ResultMessage(ResponseEnum.M2000, userService.updateByPrimaryKeySelective(user));
    }

    //@RequestMapping(value = "export_report_user_list")
    public void export_report(HttpServletResponse response, String userOrigin, String startTime, String endTime) {
        if (StringUtils.isBlank(userOrigin) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
            return;
        }
        try {
            String[] title = null;
            String sheetName = null;
            String[] columns = null;
            List<Map<String, Object>> list = null;
            String downloadFileName = TimeUtils.parseTime(new Date(), TimeUtils.dateformat4);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchant", RequestThread.get().getMerchant());
            param.put("userOrigin", userOrigin);
            param.put("startTime", StringUtils.isNotEmpty(startTime) ? startTime : null);
            param.put("endTime", StringUtils.isNotEmpty(endTime) ? endTime : null);

            downloadFileName += "-用户渠道列表";
            title = new String[]{"渠道编号", "用户姓名", "用户手机", "注册时间", "是否借款"};
            sheetName = "用户渠道列表";
            columns = new String[]{"user_origin", "user_name", "user_phone", "create_time", "borrow_flag"};
            list = userService.exportUserOriginReport(param);
            HSSFWorkbook workbook = new HSSFWorkbook();
            ExcelUtil.createSheet(workbook, sheetName, title, ExcelUtil.mapToArray(list, columns));
            ExcelUtil.excelExp(response, downloadFileName, workbook);
        } catch (Exception e) {
            logger.error("用户渠道列表报告导出异常。", e);
        }
    }

    //===================================运营商相关数据以下=====================================================================
    @RequestMapping(value = "user_carrier_info")
    public ModelAndView user_carrier_info(ModelAndView view, String id) {
        view.addObject("id", id);
        if (id == null) new RuntimeException("缺少必要参数");
        User user = userService.selectByPrimaryKey(Long.valueOf(id));
        if (user == null) new RuntimeException("用户不存在");
        if (StringUtil.isEmpty(user.getUserOrigin()) || user.getUserOrigin().equals(UserOriginEnum.JH.getCode())) {
            view.setViewName("user/user_carrier_info");
        } else if (user.getUserOrigin().equals(UserOriginEnum.RZ.getCode())) {
            view.setViewName("user/user_carrier_info_rz");
        }
        return view;
    }

    @RequestMapping(value = "user_smses_info")
    public ModelAndView user_smses_info(ModelAndView view, String id) {
        view.addObject("id", id);
        if (id == null) new RuntimeException("缺少必要参数");
        User user = userService.selectByPrimaryKey(Long.valueOf(id));
        if (user == null) new RuntimeException("用户不存在");
        if (StringUtil.isEmpty(user.getUserOrigin()) || user.getUserOrigin().equals(UserOriginEnum.JH.getCode())) {
            view.setViewName("user/user_smses_info");
        } else if (user.getUserOrigin().equals(UserOriginEnum.RZ.getCode())) {
            view.setViewName("user/user_smses_info_rz");
        }
        return view;
    }

    @RequestMapping(value = "user_mobile_ajax", method = {RequestMethod.POST})
    public ResultMessage user_mobile_ajax(Long id) throws Exception {
        MoxieMobile moxieMobile = null;
        if (id == null) {
            return new ResultMessage(ResponseEnum.M4000, "缺少必要参数");
        }
        User user = userService.selectByPrimaryKey(id);
        if (user == null) {
            return new ResultMessage(ResponseEnum.M4000, "用户不存在");
        }
        moxieMobile = moxieMobileMapper.selectLastOne(id);
        if (moxieMobile == null || moxieMobile.getRemark() == null)

            if (user.getUserOrigin().equals(UserOriginEnum.RZ.getCode())) {
                OrderUser orderUser = orderUserMapper.getUidLastOrder(Integer.valueOf(UserOriginEnum.RZ.getCode()), user.getId());
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("order_no", orderUser.getOrderNo());
                jsonObject1.put("type", "1");
                String mxMobile = RongZeRequestUtil.doPost(Constant.rongZeQueryUrl, "api.charge.data", jsonObject1.toJSONString());
                //判断运营商数据
                JSONObject jsonObject = JSONObject.parseObject(mxMobile);
                if (!jsonObject.containsKey("code") || !jsonObject.containsKey("data") || jsonObject.getInteger("code") != 200) {
                    throw new Exception("推送用户补充信息:下载运营商数据解析失败");
                }
                String dataStr = jsonObject.getString("data");
                JSONObject all = JSONObject.parseObject(dataStr);
                JSONObject data = all.getJSONObject("data");
                JSONObject report = data.getJSONObject("report");
                JSONObject members = report.getJSONObject("members");
                //上传
                String mxMobilePath = AliOssStaticUtil.uploadStr(members.toJSONString(), user.getId());
                if (StringUtils.isBlank(mxMobilePath)) {
                    throw new Exception("推送用户补充信息:运营商数据上传失败");
                }
                moxieMobile = new MoxieMobile();
                moxieMobile.setUid(user.getId());
                moxieMobile.setPhone(user.getUserPhone());
                //oss上文件的地址存在remark这个字段
                moxieMobile.setRemark(mxMobilePath);
                moxieMobileMapper.insertSelective(moxieMobile);
            } else {
                return new ResultMessage(ResponseEnum.M4000, "运营商数据不存在");
            }
        String str = AliOssStaticUtil.ossGetFile(moxieMobile.getRemark(), Constant.bucket_name_mobile);
        JSONObject jsonObject = JSON.parseObject(str);
        if (StringUtil.isEmpty(user.getUserOrigin()) || user.getUserOrigin().equals(UserOriginEnum.JH.getCode())) {
            return new ResultMessage(ResponseEnum.M2000, jsonObject.getJSONObject("data"));
        } else if (user.getUserOrigin().equals(UserOriginEnum.RZ.getCode())) {
            JSONArray jsonArray = jsonObject.getJSONArray("transactions");
            if (jsonArray.size() > 0) {
                return new ResultMessage(ResponseEnum.M2000, jsonArray.get(0));
            }
        }
        return new ResultMessage(ResponseEnum.M2000, null);
    }

    //===================================运营商相关数据以上=====================================================================

}
