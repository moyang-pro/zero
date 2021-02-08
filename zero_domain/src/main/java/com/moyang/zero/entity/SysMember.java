package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳空间 ---平台---成员信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysMember对象", description="墨阳空间 ---平台---成员信息表")
public class SysMember extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户emy账号 平台上独一无二")
    private String emy;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nick;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "电话")
    private String telphone;

    private String email;

    @ApiModelProperty(value = "墨阳空间 唯一账号")
    private String eum;

    @ApiModelProperty(value = "所属平台 编码")
    private String platCode;


}
