package com.moyang.zero.entity;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门成员职位关系表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MntMemWork对象", description="部门成员职位关系表")
public class MntMemWork extends Template {

    private static final long serialVersionUID = 1L;

    private String eum;

    private String name;

    @ApiModelProperty(value = "职位编码")
    private String positionCode;

    @ApiModelProperty(value = "职位名称")
    private String positionName;

    @ApiModelProperty(value = "是否主职 0否 1是")
    private Integer mainFlag;

    @ApiModelProperty(value = "职位描述")
    private String description;

    @ApiModelProperty(value = "入职时间")
    private LocalDateTime joinTime;

    @ApiModelProperty(value = "所属部门编码")
    private String deptCode;

    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "负责人 eum 默认部门负责人")
    private String managerEum;

    @ApiModelProperty(value = "负责人姓名")
    private String managerName;


}
