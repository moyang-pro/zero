package com.moyang.zero.service.auth;


import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.SysPrivilege;
import com.moyang.zero.entity.SysRole;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.manager.SysMemberManager;
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
	private SysMemberManager sysMemberManager;

	@Override
	public SysMember loadUserByUsername(String emy, String platCode){
		checkParams(emy);
		return sysMemberManager.loadUserByUsername(emy, platCode);
	}

	@Override
	public List<SysRole> loadRolesByUser(String emy, String platCode) {
		checkParams(emy);
		return sysMemberManager.loadRolesByUser(emy, platCode);
	}

	@Override
	public List<SysPrivilege> loadPrivByUser(String emy, String platCode) {
		checkParams(emy);
		return sysMemberManager.loadPrivByUser(emy, platCode);
	}

	@Override
	public SysMemberDetail loadAllInfoByUser(String emy, String platCode) {
		checkParams(emy);
		return sysMemberManager.loadAllInfoByUser(emy, platCode);
	}

	private void checkParams(String emy) {
		if (StringUtils.isBlank(emy)) {
			throw new BusinessException("参数错误：墨阳账号emy为空");
		}
	}

}
