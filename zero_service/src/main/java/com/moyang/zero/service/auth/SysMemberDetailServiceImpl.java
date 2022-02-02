package com.moyang.zero.service.auth;


import com.moyang.zero.entity.auth.SysMemberDetail;
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
	public SysMemberDetail loadUserByUsername(String emy, String platCode){
		return null;
	}

}
