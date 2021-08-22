package com.moyang.zero.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moyang.zero.auth.util.JwtUtil;
import com.moyang.zero.common.util.http.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moyang.zero.common.constant.ApplicationConstant.MOYANG_PRE_UPPER;

/**
 * @Author: moyang
 * @ClassName: JwtAuthenticationFilter
 * @Date: 2021/8/19 22:47
 * @Description: Jwt登录授权过滤器
 * @Version: V1.0
 **/
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	/**
	* 过滤器一定要设置 AuthenticationManager，所以此处我们这么编写，这里的 AuthenticationManager
	* 我会从 Security 配置的时候传入
	*/
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        /*
        运行父类 UsernamePasswordAuthenticationFilter 的构造方法，能够设置此滤器指定
        方法为 POST [\login]
        */
		super();
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// 从请求的 POST 中拿取 username 和 password 两个字段进行登入
		String username = request.getParameter("emy");
		String password = request.getParameter("pwd");
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		// 设置一些客户 IP 啥信息，后面想用的话可以用，虽然没啥用
		setDetails(request, token);
		// 交给 AuthenticationManager 进行鉴权
		return getAuthenticationManager().authenticate(token);
	}

	/**
	* 鉴权成功进行的操作，我们这里设置返回加密后的 token
	*/
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
		handleResponse(request, response, authResult, null);
	}

	/**
	* 鉴权失败进行的操作，我们这里就返回 用户名或密码错误 的信息
	*/
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
		handleResponse(request, response, null, failed);
	}

	private void handleResponse(HttpServletRequest request, HttpServletResponse response, Authentication authResult, AuthenticationException failed) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		Result<String> result = new Result<>();
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		if (authResult != null) {
			// 处理登入成功请求
			User user = (User) authResult.getPrincipal();
			String token = JwtUtil.sign(user.getUsername(), user.getPassword());
			result.setCode(HttpStatus.OK.value());
			result.setSuccess(true);
			result.setMessage("登入成功");
			result.setData(MOYANG_PRE_UPPER + token);
			response.setStatus(HttpStatus.OK.value());
			response.getWriter().write(mapper.writeValueAsString(result));
		} else {
			// 处理登入失败请求
			result.setCode(HttpStatus.BAD_REQUEST.value());
			result.setMessage("用户名或密码错误");
			result.setSuccess(false);
			result.setData(null);
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getWriter().write(mapper.writeValueAsString(result));
		}
	}
}

