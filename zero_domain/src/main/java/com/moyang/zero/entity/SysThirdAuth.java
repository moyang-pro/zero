package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳空间 ---平台---账号授权信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysThirdAuth对象", description="墨阳空间 ---平台---账号授权信息表")
public class SysThirdAuth extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户emy账号 平台上独一无二")
    private String eAccount;

    @ApiModelProperty(value = "来源平台 编码")
    private String platCode;

    @ApiModelProperty(value = "登录类型 登录类别，如：系统用户、邮箱、手机，或者第三方的QQ、微信、微博；")
    private String identifyType;

    @ApiModelProperty(value = "识别标识 身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;

    @ApiModelProperty(value = "授权凭证 站内账号是密码、第三方登录是Token；")
    private String credential;

    @ApiModelProperty(value = "是否验证 0 yes 1 no")
    private Integer isVerified;


}
