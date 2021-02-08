package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳---应用信息信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MyApplication对象", description="墨阳---应用信息信息表")
public class MyApplication extends Template {

    private static final long serialVersionUID = 1L;

    private String appCode;

    private String appName;

    private String description;

    private String myPlatCode;

    @ApiModelProperty(value = "平台名")
    private String platName;

    @ApiModelProperty(value = "负责人eum")
    private String managerEum;

    @ApiModelProperty(value = "负责人姓名")
    private String managerName;


}
