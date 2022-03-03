package com.moyang.zero.service;

import com.moyang.zero.entity.ZeroBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 墨阳空间广告表 服务类
 * </p>
 *
 * @author moyang
 * @since 2022-03-03
 */
public interface IZeroBannerService extends IService<ZeroBanner> {

	/**
	 * 获取首页广告列表
	 * @param platCode 平台
	 * @return 广告列表
	 */
	List<ZeroBanner> getHomeTopBannerList(String platCode);
}
