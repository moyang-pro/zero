package com.moyang.zero.auth.realm;

import com.moyang.zero.auth.token.JwtToken;
import com.moyang.zero.auth.util.JwtUtil;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.entity.auth.SysPrivilegeDetail;
import com.moyang.zero.entity.auth.SysRoleDetail;
import com.moyang.zero.service.auth.ISysMemberDetailService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @Author: moyang
 * @ClassName: MyRealm
 * @Date: 2022/1/31 15:13
 * @Description: realm 自定义
 * @Version: V1.0
 **/
public class MyRealm extends AuthorizingRealm {

	@Resource
	private ISysMemberDetailService sysMemberDetailService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 鉴权
	 * @param principals token
	 * @return 权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = JwtUtil.getUsername(principals.toString());
		String platCode = JwtUtil.getPlatCode(principals.toString());
		SysMemberDetail sysMemberDetail = sysMemberDetailService.loadUserByUsername(username, platCode);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//获得用户的角色，及权限进行绑定
		for(SysRoleDetail roleDetail : sysMemberDetail.getSysRoleList()){
			simpleAuthorizationInfo.addRole(roleDetail.getRoleCode());
			for(SysPrivilegeDetail privilegeDetail : roleDetail.getSysPrivilegeList()){
				simpleAuthorizationInfo.addStringPermission(privilegeDetail.getPrivCode());
			}
		}
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证过程
	 * @param authenticationToken token
	 * @return 认证成功与否
	 * @throws AuthenticationException 认证失败
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String token = (String) authenticationToken.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JwtUtil.getUsername(token);
		if (username == null) {
			throw new AuthenticationException("token 无效！");
		}
		String platCode = JwtUtil.getPlatCode(token);
		SysMemberDetail sysMemberDetail = sysMemberDetailService.loadUserByUsername(username, platCode);
		if (sysMemberDetail == null) {
			throw new AuthenticationException("用户" + username + "不存在") ;
		}
		if (!JwtUtil.verify(token, username, platCode, sysMemberDetail.getPassword())) {
			throw new AuthenticationException("账户密码错误!");
		}
		return new SimpleAuthenticationInfo(token, token, "my_realm");
	}
}