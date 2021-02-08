package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳平台、应用--权限信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysPrivilege对象", description="墨阳平台、应用--权限信息表")
public class SysPrivilege extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限编码")
    private String privCode;

    private String privName;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value = "当前权限固定路径（组）")
    private String url;

    @ApiModelProperty(value = "墨阳平台/应用编码")
    private String myCode;


}
