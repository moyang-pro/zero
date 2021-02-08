package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳空间 ---平台---日志信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysLog对象", description="墨阳空间 ---平台---日志信息表")
public class SysLog extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户emy账号 平台上独一无二")
    private String emy;

    @ApiModelProperty(value = "来源平台 编码")
    private String platCode;

    @ApiModelProperty(value = "来源ip")
    private String ip;

    @ApiModelProperty(value = "打印日志的方法名")
    private String method;

    @ApiModelProperty(value = "页面路径")
    private String page;

    @ApiModelProperty(value = "请求的地址")
    private String uri;

    @ApiModelProperty(value = "用户做了什么：描述")
    private String what;

    @ApiModelProperty(value = "0 info; 1 error; 2 warn; 3 debug")
    private Boolean status;


}
