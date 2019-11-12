package com.mod.loan.service.impl;

import com.mod.loan.config.Constant;
import com.mod.loan.service.UploadService;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

@Log4j
@Service
public class UploadServiceImpl implements UploadService {

    public static final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public String uploadFile(String filePath, MultipartFile file) {
        long index = System.currentTimeMillis();
        if (createLocalFile(filePath, file, index)) {
            try {
                return Constant.FILE_VISIT_HOST + "/file/visit?f=" + URLEncoder.encode(filePath + "/" + index + file.getOriginalFilename(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("UploadServiceImpl.uploadFile URLEncoder.encode fail", e);
                return null;
            }
        }
        return null;
    }

    /**
     * 通过上传的文件名，存储到本地
     *
     * @param filePath 目录
     * @param file     文件
     * @param index
     */
    private boolean createLocalFile(String filePath, MultipartFile file, long index) {
        File localFile = new File(Constant.FILE_SAVE_PATH + "/" + filePath);
        //先创建目录
        localFile.mkdirs();
        String path = Constant.FILE_SAVE_PATH + "/" + filePath + "/" + index + file.getOriginalFilename();
        localFile = new File(path);
        FileOutputStream fos = null;
        InputStream in = null;
        try {
            if (localFile.exists()) {
                //如果文件存在删除文件
                boolean delete = localFile.delete();
                if (!delete) {
                    log.error("Delete exist file " + path + " failed!!!", new Exception("Delete exist file " + path + " failed!!!"));
                }
            }
            //创建文件
            if (!localFile.exists()) {
                //如果文件不存在，则创建新的文件
                localFile.createNewFile();
                log.info("Create file successfully,the file is " + path);
            }
            //创建文件成功后，写入内容到文件里
            fos = new FileOutputStream(localFile);
            in = file.getInputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.flush();
            log.info("Reading uploaded file and buffering to local successfully!");
        } catch (IOException e) {
            log.info("uploaded file fail.", e);
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("InputStream or OutputStream close error : {}", e);
                return false;
            }
        }
        return true;
    }
}
