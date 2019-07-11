package com.mod.loan.pay.baofoo.demo;

import com.mod.loan.pay.baofoo.base.TransContent;
import com.mod.loan.pay.baofoo.base.response.TransRespBF40001Async;
import com.mod.loan.pay.baofoo.rsa.RsaCodingUtil;
import com.mod.loan.pay.baofoo.util.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BF0040001SDKDemoServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 477054662948506585L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String member_id = req.getParameter("member_id");
        String terminal_id = req.getParameter("terminal_id");
        String data_type = req.getParameter("data_type");
        String data_content = req.getParameter("data_content");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (data_content == null || "".equals(data_content)) {
            log("接受数据不能为空！");
            throw new RuntimeException("接受数据不能为空！");
        }

        log("接收的返回数据：member_id" + member_id + ",terminal_id:" + terminal_id + ",data_content:" + data_content);
        String pub_key = "d:\\CER\\baofoo_pub.cer";
        TransContent<TransRespBF40001Async> str2Obj = new TransContent<TransRespBF40001Async>(data_type);
        // 密文返回
        // 第一步：公钥解密
        data_content = RsaCodingUtil.decryptByPubCerFile(data_content, pub_key);
        // 第二步BASE64解密
        data_content = SecurityUtil.Base64Decode(data_content);
        log("data_content:" + data_content);
        str2Obj = (TransContent<TransRespBF40001Async>) str2Obj.str2Obj(data_content, TransRespBF40001Async.class);
        // 业务逻辑判断
        System.out.println(str2Obj.getTrans_reqDatas());


        out.write("OK");

    }

    public void log(String msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t: " + msg);
    }

}
