package com.moyang.zero.service.impl;

import com.moyang.zero.common.constant.ApplicationConstant;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.manager.SysMemberManager;
import com.moyang.zero.mapper.SysMemberMapper;
import com.moyang.zero.req.RegisterReq;
import com.moyang.zero.service.ISysMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 墨阳空间 ---平台---成员信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Service
public class SysMemberServiceImpl extends ServiceImpl<SysMemberMapper, SysMember> implements ISysMemberService {


	@Resource
	RedisUtil redisUtil;

	@Resource
	SysMemberManager sysMemberManager;

	@Override
	public boolean registerNewMember(RegisterReq req) {
		//校验验证码
		String code = req.getCheckCode();
		String checkCode = redisUtil.getString(req.getPhone());
		if (StringUtils.isBlank(checkCode)){
			throw new BusinessException("验证码已失效或错误，请重试！");
		}
		if (!checkCode.equals(code)){
			throw new BusinessException("验证码不正确，请重试！");
		}
		String emy = req.getEmy();
		String platCode = req.getPlatCode();
		SysMember exist = sysMemberManager.getMemberInfoByEmyAndPlat(emy,platCode);
		if (exist != null){
			throw new BusinessException("账号已经被注册，请换个账号");
		}
		SysMember sysMember = new SysMember();
		sysMember.setAvatar(ApplicationConstant.AVATAR_DEFAULT);
		sysMember.setTelephone(req.getPhone());
		sysMember.setEmy(emy);
		sysMember.setPlatCode(platCode);
		return false;
	}

	@Override
	public String getCheckCode(String phone) {
		String code = redisUtil.getString(phone);
        if (StringUtils.isBlank(code)){
        	code = "123456";
        	redisUtil.set(phone,"123456");
        }
		return code;
	}
}
