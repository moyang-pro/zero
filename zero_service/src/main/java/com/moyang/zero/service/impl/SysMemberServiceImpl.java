package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.constant.ApplicationConstant;
import com.moyang.zero.common.enums.RoleEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.SignUtil;
import com.moyang.zero.common.util.VerifyUtil;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.common.util.redis.RedisUtil;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.SysMemberRole;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.manager.SysMemberManager;
import com.moyang.zero.mapper.SysMemberMapper;
import com.moyang.zero.mapper.SysMemberRoleMapper;
import com.moyang.zero.req.AccountLoginReq;
import com.moyang.zero.req.RegisterReq;
import com.moyang.zero.service.ISysMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.moyang.zero.common.constant.ApplicationConstant.APP_NAME;
import static com.moyang.zero.common.constant.RedisKeyConstant.REDIS_PREFIX_REGISTER;

/**
 * <p>
 * 墨阳空间 ---平台---成员信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Service
@Slf4j
public class SysMemberServiceImpl extends ServiceImpl<SysMemberMapper, SysMember> implements ISysMemberService {


	@Resource
	RedisUtil redisUtil;

	@Resource
	SysMemberManager sysMemberManager;

	@Resource
	SysMemberRoleMapper sysMemberRoleMapper;

	/**
	 * 验证码位数
	 */
	@Value("${register.verify.num}")
	private int verifyNum;

	@Override
	public boolean registerNewMember(RegisterReq req) {
		String emy = req.getEmy();
		String platCode = req.getPlatCode();
		//校验是否存在
		SysMember exist = sysMemberManager.getMemberInfoByEmyAndPlat(emy, platCode);
		if (exist != null){
			throw new BusinessException("账号已经被注册，请换个账号");
		}
		//校验验证码
		String code = req.getCheckCode();
		String checkCode = redisUtil.getString(REDIS_PREFIX_REGISTER + req.getPhone());
		if (StringUtils.isBlank(checkCode)){
			throw new BusinessException("验证码已失效或错误，请重新获取！");
		}
		if (!checkCode.equals(code)){
			throw new BusinessException("验证码不正确，请重试！");
		}
		SysMember sysMember = new SysMember();

		sysMember.setAvatar(ApplicationConstant.AVATAR_DEFAULT);
		sysMember.setTelephone(req.getPhone());
		sysMember.setEmy(emy);
		sysMember.setPassword(req.getPwd());
		sysMember.setPlatCode(platCode);
		sysMember.recordCreateInfo(emy,"墨阳空间新用户账号注册！");
		sysMember.valid();
		//根据平台编码 用户账号 手机号 签名生成 eum
        sysMember.setEum(SignUtil.productOneEum(emy, req.getPhone(), platCode));
        //保存新的用户信息
        this.save(sysMember);
        this.newMemberInit(sysMember);
		return true;
	}

	/**
	 * 新用户 添加角色
	 * @param sysMember 新用户
	 */
	private void newMemberInit(SysMember sysMember) {
		SysMemberRole roleInfo = new SysMemberRole();
		roleInfo.setRoleCode(RoleEnum.ROLE_MYCR.getCode());
		roleInfo.setRoleName(RoleEnum.ROLE_MYCR.getDescription());
		roleInfo.setEmy(sysMember.getEmy());
		roleInfo.setMyCode(sysMember.getPlatCode());
		roleInfo.recordCreateInfo(APP_NAME, "新用户角色初始化");
		roleInfo.valid();
		sysMemberRoleMapper.insert(roleInfo);
	}

	@Override
	public String getCheckCode(String phone) {
		String code = redisUtil.getString(phone);
        if (StringUtils.isBlank(code)){
        	code = VerifyUtil.getRandomRegisterCode(verifyNum);
        	redisUtil.set(REDIS_PREFIX_REGISTER + phone, code);
        }
		return code;
	}

	@Override
	public Result<String> userAccountLogin(AccountLoginReq req) {
		log.info("用户登录：emy == {}", req.getEmy());
		String platCode = req.getPlatCode();
		SysMember exist = sysMemberManager.getMemberInfoByEmyAndPlat(req.getEmy(), platCode);
		if (exist == null){
			return Result.fail("用户账号信息为空");
		}
		return Result.success();
	}

	@Override
	public SysMemberDetail getSysMemberInfo(String emy, String platCode) {
		SysMemberDetail sysMemberDetail =  sysMemberManager.loadAllInfoByUser(emy, platCode);
		if (sysMemberDetail != null) {
			sysMemberDetail.setPassword("");
		}
		return sysMemberDetail;
	}
}
