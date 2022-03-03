package com.moyang.zero.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 墨阳空间广告表
 * </p>
 *
 * @author moyang
 * @since 2022-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZeroBanner对象", description="墨阳空间广告表")
public class ZeroBanner extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告标题")
    private String adTitle;

    @ApiModelProperty(value = "广告描述")
    private String adDesc;

    @ApiModelProperty(value = "封面图片链接")
    private String adCoverUrl;

    @ApiModelProperty(value = "外部链接地址")
    private String adLink;

    @ApiModelProperty(value = "应用编码")
    private String appCode;

    @ApiModelProperty(value = "排序")
    private Integer sortIndex;


}
