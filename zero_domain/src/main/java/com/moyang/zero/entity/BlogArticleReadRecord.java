package com.moyang.zero.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 博客文章阅读记录
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogArticleReadRecord对象", description="博客文章阅读记录")
public class BlogArticleReadRecord extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    private Long articleId;

    @ApiModelProperty(value = "读者账号")
    private String emy;

    @ApiModelProperty(value = "平台编码")
    private String platCode;

    @ApiModelProperty(value = "IP地址")
    private String ipAddr;

    @ApiModelProperty(value = "来源地址")
    private String fromUrl;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;


}
