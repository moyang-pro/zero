package com.moyang.zero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyang.zero.entity.SysPrivilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 墨阳平台、应用--权限信息表 Mapper 接口
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {

	/**
	 * 查找用户所有权限
	 * @param emy 墨阳账号
	 * @param platCode 平台编码
	 * @return 权限信息
	 */
	List<SysPrivilege> findPrivListByUser(@Param("emy") String emy, @Param("platCode") String platCode);

}
