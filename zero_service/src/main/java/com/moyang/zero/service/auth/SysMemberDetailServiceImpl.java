package com.moyang.zero.service.auth;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.moyang.zero.common.enums.PlatCodeEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.MyBeanCopier;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.SysRole;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.entity.auth.SysPrivilegeDetail;
import com.moyang.zero.entity.auth.SysRoleDetail;
import com.moyang.zero.mapper.SysMemberMapper;
import com.moyang.zero.mapper.SysRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: moyang
 * @ClassName: SysMemberDetailServiceImpl
 * @Date: 2021/8/18 0:07
 * @Description: //实现用户角色权限信息的服务
 * @Version: V1.0
 **/
@Service("sysMemberDetailService")
public class SysMemberDetailServiceImpl implements ISysMemberDetailService {

	@Resource
	SysMemberMapper sysMemberMapper;

	@Resource
	SysRoleMapper sysRoleMapper;

	@Override
	public SysMember loadUserByUsername(String emy, String platCode){
		checkParams(emy);
		if (StringUtils.isBlank(platCode)) {
			platCode = PlatCodeEnum.DEFAULT_CODE.getCode();
		}
		return new LambdaQueryChainWrapper<>(sysMemberMapper)
				.eq(SysMember::getEmy, emy).eq(SysMember::getPlatCode, platCode)
				.one();
	}

	@Override
	public List<SysRoleDetail> loadRolesByUser(String emy, String platCode) {

		List<SysRole> listRoles = sysRoleMapper.findRolesByUser(emy, platCode);
		return MyBeanCopier.copyList(listRoles, SysRoleDetail::new);
	}

	@Override
	public List<SysPrivilegeDetail> loadPrivByUser(String emy, String platCode) {
		return null;
	}

	@Override
	public SysMemberDetail loadAllInfoByUser(String emy, String platCode) {
		SysMember sysMember = loadUserByUsername(emy, platCode);
		return MyBeanCopier.copyBean(sysMember, SysMemberDetail::new);
	}

	private void checkParams(String emy) {
		if (StringUtils.isBlank(emy)) {
			throw new BusinessException("参数错误：墨阳账号emy为空");
		}
	}

}
