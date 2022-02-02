package com.moyang.zero.controller;

import com.moyang.zero.auth.util.LoginContext;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.service.IFileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * @Author: moyang
 * @ClassName: FileController
 * @Date: 2022/2/2 20:14
 * @Description: 文件存储 控制器
 * @Version: V1.0
 **/
@Slf4j
@RestController
@RequestMapping(ZERO_API +"/file")
public class FileController {

	private static final long MAX_FILE_SIZE = 1024 * 1024 * 10;

	@Resource
	IFileService fileService;

	@PostMapping("/blog/upload")
	@ApiOperation(value = "墨阳空间-新用户注册")
	Result<String> fileUpload(@RequestParam("image") MultipartFile file){
		if (file.isEmpty()) {
			return Result.fail("空文件！");
		}
		if (file.getSize() > MAX_FILE_SIZE) {
			return Result.fail("文件太大了！");
		}
		System.out.println(LoginContext.getCurrentUser());
        String url = fileService.upload(file, LoginContext.getCurrentUser());
		if (StringUtils.isNotEmpty(url)) {
			return Result.success(url);
		}

		return Result.fail();
	}
}
