package com.mod.loan.controller.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.util.AliOssStaticUtil;

@RestController
@RequestMapping(value = "upload")
public class UploadController {

	/**
	 * 上传图片文件
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "img_upload_ajax", method = { RequestMethod.POST })
	public String img_upload_ajax(MultipartFile file) {
		String originalFileName = file.getOriginalFilename();
		String suffixName = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
		if (!".jpg".equalsIgnoreCase(suffixName) && !".png".equalsIgnoreCase(suffixName) && !".jpeg".equalsIgnoreCase(suffixName) && !".gif".equalsIgnoreCase(suffixName)) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "文件格式不正确！").toString();
		}
		// 大小不超过5MB
		if (file.getSize() > 1024 * 1024 * 5) {
			return new ResultMessage(ResponseEnum.M4000.getCode(), "文件大小超过限制！").toString();
		}
		return this.fileUpload(file, suffixName).toString();
	}
	
	private ResultMessage fileUpload(MultipartFile file, String suffixName) {
		Map<String, Object> data = new HashMap<>();
		InputStream is = null;
		Long currentDate = new Date().getTime();
		String fileName = currentDate + suffixName;
		try {
			is = file.getInputStream();
			String fileUrl = AliOssStaticUtil.ossUploadFile(Constant.env, is, fileName);
			data.put("relativePath", fileUrl);
			data.put("absolutePath", Constant.img_prefix + "/" + fileUrl);
			return new ResultMessage(ResponseEnum.M2000, data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new ResultMessage(ResponseEnum.M4000.getCode(), "上传失败，请重试");
	}
}
