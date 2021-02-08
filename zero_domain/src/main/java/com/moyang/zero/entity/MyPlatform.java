package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳空间---平台信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MyPlatform对象", description="墨阳空间---平台信息表")
public class MyPlatform extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "平台名")
    private String platName;

    @ApiModelProperty(value = "平台编码 MYP001 ...")
    private String platCode;

    @ApiModelProperty(value = "平台描述")
    private String description;

    @ApiModelProperty(value = "网址")
    private String url;


}
