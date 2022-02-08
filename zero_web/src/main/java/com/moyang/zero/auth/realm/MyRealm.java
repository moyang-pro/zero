package com.moyang.zero.auth.realm;

import com.moyang.zero.auth.token.JwtToken;
import com.moyang.zero.auth.util.JwtUtil;
import com.moyang.zero.auth.util.LoginContext;
import com.moyang.zero.common.enums.HttpErrorEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.SysPrivilege;
import com.moyang.zero.entity.SysRole;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.service.auth.ISysMemberDetailService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
		SysMemberDetail sysMemberDetail = sysMemberDetailService.loadAllInfoByUser(username, platCode);
		log.info("user request doGetAuthorizationInfo 鉴权........................ ");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (sysMemberDetail != null) {
	        LoginContext.setLoginContextBySysMember(sysMemberDetail);
	        //获得用户的角色，及权限进行绑定
	        for(SysRole role : sysMemberDetail.getSysRoleList()){
		        simpleAuthorizationInfo.addRole(role.getRoleCode());
	        }
	        for(SysPrivilege privilege : sysMemberDetail.getSysPrivilegeList()){
		        simpleAuthorizationInfo.addStringPermission(privilege.getPrivCode());
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
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws BusinessException {
		log.info("user request doGetAuthenticationInfo 认证........................");
		String token = (String) authenticationToken.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JwtUtil.getUsername(token);
		if (username == null) {
			throw new BusinessException(HttpErrorEnum.TOKEN_EXPIRED);
		}
		String platCode = JwtUtil.getPlatCode(token);
		SysMember sysMember = sysMemberDetailService.loadUserByUsername(username, platCode);
		if (sysMember == null) {
			throw new BusinessException("用户" + username + "不存在") ;
		}
		if (!JwtUtil.verify(token, username, platCode, sysMember.getPassword())) {
			throw new BusinessException("账户密码错误!");
		}
		return new SimpleAuthenticationInfo(token, token, "myRealm");
	}
}
