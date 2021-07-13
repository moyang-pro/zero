package com.moyang.zero.controller;


import com.moyang.zero.entity.Template;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

	public List<Template> getList(){
		return Collections.emptyList();
	}


}
