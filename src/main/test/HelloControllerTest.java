import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.mod.loan.Application;
import com.mod.loan.config.Constant;
import com.mod.loan.util.AliOssStaticUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: whw
 * @Date: 2019/5/14/014 11:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {


    /**
     * 向"/test"地址发送请求，并打印返回结果
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        System.out.println(Constant.env);
    }


    /**
     * 获取指定文件
     */
    @Test
    public void listFile() throws IOException {
        String bucketName = Constant.bucket_name_mobile;
        String objectName="2019/0514/7920567.txt";
        String str = AliOssStaticUtil.ossGetFile(objectName,bucketName);
        System.out.println(str);
        JSONObject jsonObject = JSON.parseObject(str);
    }



}
