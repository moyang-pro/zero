package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 平台、应用---用户角色关系表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysMemberRole对象", description="平台、应用---用户角色关系表")
public class SysMemberRole extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "墨阳编码")
    private String myCode;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "墨阳平台账号")
    private String emy;


}
