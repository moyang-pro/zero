package com.moyang.zero.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.enums.PlatCodeEnum;
import com.moyang.zero.common.util.MyBeanCopier;
import com.moyang.zero.common.util.redis.RedisUtil;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.SysPrivilege;
import com.moyang.zero.entity.SysRole;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.mapper.SysMemberMapper;
import com.moyang.zero.mapper.SysPrivilegeMapper;
import com.moyang.zero.mapper.SysRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.moyang.zero.common.constant.RedisKeyConstant.REDIS_DEFAULT_EXPIRE;
import static com.moyang.zero.common.constant.RedisKeyConstant.REDIS_PREFIX_USER;
import static com.moyang.zero.common.constant.SymbolConstant.DASH;

/**
 * @program: zero
 * @description: 管理sys_member表的数据
 * @packageName: com.moyang.zero.manager
 * @author: moyang
 * @date: 2021-07-17 23:24
 **/
@Component
@Slf4j
public class SysMemberManager {

	@Resource
	SysMemberMapper sysMemberMapper;

	@Resource
	private SysRoleMapper sysRoleMapper;

	@Resource
	private SysPrivilegeMapper sysPrivilegeMapper;

	@Resource
	private RedisUtil redisUtil;


	public SysMember loadUserByUsername(String emy, String platCode){
		if (StringUtils.isBlank(platCode)) {
			platCode = PlatCodeEnum.DEFAULT_CODE.getCode();
		}
		return new LambdaQueryChainWrapper<>(sysMemberMapper)
				.eq(SysMember::getEmy, emy).eq(SysMember::getPlatCode, platCode)
				.eq(SysMember::getDelFlag, DelEnum.valid())
				.one();
	}

	public List<SysRole> loadRolesByUser(String emy, String platCode) {
		List<SysRole> listRole = sysRoleMapper.findRolesByUser(emy, platCode);
		if (CollectionUtils.isEmpty(listRole)) {
			return Collections.emptyList();
		}
		return listRole;
	}

	public List<SysPrivilege> loadPrivByUser(String emy, String platCode) {
		List<SysPrivilege> listPriv = sysPrivilegeMapper.findPrivListByUser(emy, platCode);
		if (CollectionUtils.isEmpty(listPriv)) {
			return Collections.emptyList();
		}
		return listPriv;
	}

	public SysMemberDetail loadAllInfoByUser(String emy, String platCode) {
		String userKey = REDIS_PREFIX_USER + platCode + DASH + emy;
		String jsonObject = redisUtil.getString(userKey);
		log.debug("user info jsonObject string: {}", jsonObject);
		if (StringUtils.isNotEmpty(jsonObject)) {
			try {
				JSONObject userJson = JSONObject.parseObject(jsonObject);
				return JSON.toJavaObject(userJson,SysMemberDetail.class);
			} catch (JSONException jsonException) {
				log.error("平台：{} 用户：{}的信息从Redis中获取类型转换失败！原因：{}", platCode, emy, jsonException.getMessage());
				SysMemberDetail userInfo =  getUserDetailFromDb(emy, platCode);
				String jsonString = JSONObject.toJSONString(userInfo);
				log.debug("user info json string: {}", jsonString);
				redisUtil.set(userKey, jsonString, REDIS_DEFAULT_EXPIRE);
				return userInfo;
			}
		}
		SysMemberDetail userInfo =  getUserDetailFromDb(emy, platCode);
		String jsonString = JSONObject.toJSONString(userInfo);
		redisUtil.set(userKey, jsonString, REDIS_DEFAULT_EXPIRE);
		log.debug("user info json string: {}", jsonString);
		return userInfo;
	}

	private SysMemberDetail getUserDetailFromDb(String emy, String platCode) {
		SysMember sysMember = loadUserByUsername(emy, platCode);
		SysMemberDetail sysMemberDetail = MyBeanCopier.copyBean(sysMember, SysMemberDetail::new);
		List<SysRole> roleList = loadRolesByUser(emy, platCode);
		List<SysPrivilege> privilegeList = loadPrivByUser(emy, platCode);
		sysMemberDetail.setSysRoleList(roleList);
		sysMemberDetail.setSysPrivilegeList(privilegeList);
		return sysMemberDetail;
	}


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
	 * @param emy 用户账号
	 * @param pwd 密码
	 * @param plat 平台编码
	 * @return 用户信息
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
