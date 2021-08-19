package com.moyang.zero.auth.service.impl;

import com.moyang.zero.auth.service.ISysMemberDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: moyang
 * @ClassName: SysMemberDetailServiceImpl
 * @Date: 2021/8/18 0:07
 * @Description: //实现用户角色权限信息的服务
 * @Version: V1.0
 **/
@Service("sysMemberDetailService")
public class SysMemberDetailServiceImpl implements ISysMemberDetailService {
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}
}
