package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@ApiModel(value="Template对象", description="用户信息表")
public class Template{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createAt;

    @ApiModelProperty(value = "创建者emy账号")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateAt;

    @ApiModelProperty(value = "更新者 emy账号")
    private String updateBy;

    @ApiModelProperty(value = "删除标识 0 未删除 1已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "记录说明")
    private String remarks;

}
