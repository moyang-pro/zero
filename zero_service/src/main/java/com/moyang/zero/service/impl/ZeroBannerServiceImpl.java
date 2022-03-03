package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.entity.ZeroBanner;
import com.moyang.zero.mapper.ZeroBannerMapper;
import com.moyang.zero.service.IZeroBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 墨阳空间广告表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-03-03
 */
@Service
public class ZeroBannerServiceImpl extends ServiceImpl<ZeroBannerMapper, ZeroBanner> implements IZeroBannerService {

	@Resource
	ZeroBannerMapper zeroBannerMapper;

	@Override
	public List<ZeroBanner> getHomeTopBannerList(String appCode) {

		return new LambdaQueryChainWrapper<>(zeroBannerMapper)
				.eq(ZeroBanner::getAppCode, appCode).eq(ZeroBanner::getDelFlag, DelEnum.valid())
				.orderByDesc(ZeroBanner::getSortIndex).last(" limit 2")
				.list();
	}
}
