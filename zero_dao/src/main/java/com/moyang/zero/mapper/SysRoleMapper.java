package com.moyang.zero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyang.zero.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色信息表 Mapper 接口
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 查找用户的角色信息
	 * @param emy 墨阳账号
	 * @param platCode 平台编码
	 * @return 角色信息
	 */
	List<SysRole> findRolesByUser(@Param("emy") String emy,@Param("platCode") String platCode);

}
