package com.moyang.zero.controller;

import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.common.util.http.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: moyang
 * @ClassName: MyErrorController
 * @Date: 2022/2/21 22:08
 * @Description:
 * @Version: V1.0
 **/
@RestController
@Api(value = "filter错误处理")
public class MyErrorController extends BasicErrorController {

	public MyErrorController() {
		super(new DefaultErrorAttributes(), new ErrorProperties());
	}

	/**
	 * shiro 认证错误
	 */
	private final static String AUTHENTICATION_EXCEPTION = "org.apache.shiro.authc.AuthenticationException";

	/**
	 * shiro 鉴权错误
	 */
	private final static String AUTHORIZATION_EXCEPTION = "org.apache.shiro.authz.AuthorizationException";

	/**
	 * shiro 未认证错误
	 */
	private final static String UNAUTHENTICATED_EXCEPTION = "org.apache.shiro.authz.UnauthenticatedException";

	/**
	 * shiro 未授权错误
	 */
	private final static String UNAUTHORIZED_EXCEPTION = "org.apache.shiro.authz.UnauthorizedException";
	@Override
	@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity error(HttpServletRequest request) {
		getErrorProperties().setIncludeException(true);
		getErrorProperties().setIncludeMessage(ErrorProperties.IncludeAttribute.ALWAYS);
		getErrorProperties().setIncludeStacktrace(ErrorProperties.IncludeStacktrace.ALWAYS);
		Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
		//错误信息
		String msg = (String)body.get("message");
		int code = ResultCodeEnum.FAIL.getCode();
        switch(msg){
	        case AUTHENTICATION_EXCEPTION:
            case AUTHORIZATION_EXCEPTION :
	            code = ResultCodeEnum.NO_LOGIN.getCode();
               break;
	        case UNAUTHENTICATED_EXCEPTION:
	        case UNAUTHORIZED_EXCEPTION :
		        code = ResultCodeEnum.NO_AUTH.getCode();
		        break;
            default :
	            break;
        }
		return new ResponseEntity<>(Result.fail(code, msg), HttpStatus.OK);
	}
}
