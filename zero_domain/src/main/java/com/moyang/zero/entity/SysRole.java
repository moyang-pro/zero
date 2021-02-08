package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统角色信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysRole对象", description="系统角色信息表")
public class SysRole extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码 MYSR /MYPR")
    private String roleCode;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "角色类型：空间角色 平台角色 应用角色")
    private String type;

    @ApiModelProperty(value = "墨阳编码 查看my_code表")
    private String myCode;


}
