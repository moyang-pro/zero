package com.moyang.zero.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳集团----企业部门信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MntDept对象", description="墨阳集团----企业部门信息表")
public class MntDept extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门等级 1 2 3 4级部门")
    private Integer level;

    @ApiModelProperty(value = "是否是一级部门")
    private Integer isParent;

    @ApiModelProperty(value = "父级部门 编码")
    private String parentCode;

    @ApiModelProperty(value = "部门描述")
    private String description;

    @ApiModelProperty(value = "编制")
    private Integer headCount;

    @ApiModelProperty(value = "负责人姓名")
    private String managerName;

    @ApiModelProperty(value = "负责人墨阳eum")
    private String managerEum;

    @ApiModelProperty(value = "所属企业编码")
    private String orgCode;

    @ApiModelProperty(value = "所属企业名称")
    private String orgName;


}
