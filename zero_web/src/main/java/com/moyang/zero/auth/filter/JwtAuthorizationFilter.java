package com.moyang.zero.auth.filter;

import com.moyang.zero.auth.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moyang.zero.common.constant.ApplicationConstant.MOYANG_PRE_UPPER;

/**
 * @Author: moyang
 * @ClassName: JwtAuthorizationFilter
 * @Date: 2021/8/19 23:24
 * @Description: 用户登录鉴权过滤器
 * @Version: V1.0
 **/
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	@Resource
	private UserDetailsService userDetailsService;


	/**
	 * 会从 Spring Security 配置文件那里传过来
	 */
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 判断是否有 token，并且进行认证
		Authentication token = getAuthentication(request);
		if (token == null) {
			chain.doFilter(request, response);
			return;
		}
		// 认证成功
		SecurityContextHolder.getContext().setAuthentication(token);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization == null || ! authorization.startsWith(MOYANG_PRE_UPPER)) {
			return null;
		}

		String token = authorization.split("-")[1];
		String username = JwtUtil.getUsername(token);
		UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			return null;
		}
		if (! JwtUtil.verify(token, username, userDetails.getPassword())) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}

