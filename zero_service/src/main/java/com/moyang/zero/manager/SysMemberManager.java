package com.moyang.zero.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moyang.zero.common.constant.ApplicationConstant;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.mapper.SysMemberMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: zero
 * @description: 管理sys_member表的数据
 * @packageName: com.moyang.zero.manager
 * @author: moyang
 * @date: 2021-07-17 23:24
 **/
@Component
public class SysMemberManager {

	@Resource
	SysMemberMapper sysMemberMapper;

	public  SysMember getMemberInfoByEmyAndPlat(String emy,String plat){
		if (StringUtils.isBlank(emy)){
			return null;
		}
		//平台默认是moyang
		plat = StringUtils.isNotBlank(plat) ? plat : ApplicationConstant.APP_NAME;
		LambdaQueryWrapper<SysMember> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(SysMember::getEmy,emy);
		lambdaQueryWrapper.eq(SysMember::getPlatCode,plat);
		lambdaQueryWrapper.eq(SysMember::getDelFlag, DelEnum.TRUE.getCode());
		return  sysMemberMapper.selectOne(lambdaQueryWrapper);
	}
}
