package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysRolePriv对象", description="用户信息表")
public class SysRolePriv extends Template {

    private static final long serialVersionUID = 1L;

    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "权限编码")
    private String privCode;

    @ApiModelProperty(value = "权限名称")
    private String privName;


}
