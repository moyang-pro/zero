package com.moyang.zero.entity;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳集团-----企业信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MntOrg对象", description="墨阳集团-----企业信息表")
public class MntOrg extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业编号")
    private String orgCode;

    @ApiModelProperty(value = "企业名")
    private String orgName;

    @ApiModelProperty(value = "注册地址")
    private String registAddress;

    private String workAddress;

    @ApiModelProperty(value = "统一信用代码")
    private String registNo;

    @ApiModelProperty(value = "企业电话")
    private String telphone;

    @ApiModelProperty(value = "开户银行")
    private String payBank;

    @ApiModelProperty(value = "注册日期")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "法人姓名")
    private String legalPerson;

    @ApiModelProperty(value = "法人身份证")
    private String lpIdCard;

    private String lpPhone;

    @ApiModelProperty(value = "注册资本")
    private String taxAmount;


}
