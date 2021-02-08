package com.moyang.zero.entity;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳集团---企业成员信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MntMember对象", description="墨阳集团---企业成员信息表")
public class MntMember extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户墨阳账号 union")
    private String eum;

    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    @ApiModelProperty(value = "电话")
    private String telphone;

    private String idCard;

    private String email;

    @ApiModelProperty(value = "所属部门编码")
    private String deptCode;

    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态  0 正式工人 1实习生 2试用期人员 3离职人员")
    private Integer state;

    @ApiModelProperty(value = "工作状态 0全职 1兼职 2双职")
    private Integer workState;

    private String workDegree;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "籍贯")
    private String birthplace;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "最高学历")
    private String degree;

    @ApiModelProperty(value = "体重")
    private Double weight;

    @ApiModelProperty(value = "身高")
    private Double height;

    @ApiModelProperty(value = "婚育情况")
    private String marriage;

    @ApiModelProperty(value = "目前居住地址")
    private String currentAddr;

    @ApiModelProperty(value = "户籍所在地")
    private String residence;

    @ApiModelProperty(value = "专业")
    private String professional;


}
