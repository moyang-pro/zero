package com.moyang.zero.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.enums.PlatCodeEnum;
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

	public  SysMember getMemberInfoByEmyAndPlat(String emy, String plat){
		if (StringUtils.isBlank(emy)){
			return null;
		}
		//平台默认是ZERO_CODE
		plat = StringUtils.isNotBlank(plat) ? plat : PlatCodeEnum.DEFAULT_CODE.getCode();
		LambdaQueryWrapper<SysMember> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(SysMember::getEmy, emy);
		lambdaQueryWrapper.eq(SysMember::getPlatCode, plat);
		lambdaQueryWrapper.eq(SysMember::getDelFlag, DelEnum.FALSE.getCode());
		return  sysMemberMapper.selectOne(lambdaQueryWrapper);
	}

	/**
	 * 根据用户账号、密码、平台编码
	 * @param emy
	 * @param pwd
	 * @param plat
	 * @return
	 */
	public SysMember getMemberInfoByEmyPwdAndPlat(String emy, String pwd, String plat){

		if (StringUtils.isBlank(emy) || StringUtils.isBlank(pwd)){
			return null;
		}
		//平台默认是 MOYANG_CODE
		plat = StringUtils.isNotBlank(plat) ? plat : PlatCodeEnum.DEFAULT_CODE.getCode();
		LambdaQueryWrapper<SysMember> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(SysMember::getEmy, emy);
		lambdaQueryWrapper.eq(SysMember::getPassword, pwd);
		lambdaQueryWrapper.eq(SysMember::getPlatCode, plat);
		lambdaQueryWrapper.eq(SysMember::getDelFlag, DelEnum.FALSE.getCode());
		return  sysMemberMapper.selectOne(lambdaQueryWrapper);
	}
}
