package com.moyang.zero.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客文章信息主表
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogArticle对象", description="用户信息表")
public class BlogArticle extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章简要描述")
    private String articleDes;

    @ApiModelProperty(value = "文章html内容")
    private String articleContent;

    @ApiModelProperty(value = "emy账号:作者")
    private String author;

    @ApiModelProperty(value = "文章原始内容")
    private String articleText;

    @ApiModelProperty(value = "文章类型")
    private Integer articleType;

    @ApiModelProperty(value = "文章生命周期状态")
    private Integer articleStatus;

    @ApiModelProperty(value = "文章封面图片链接")
    private String coverUrl;

    @ApiModelProperty(value = "文章可见性")
    private Integer publishType;

    @ApiModelProperty(value = "引用文章")
    private String quote;

}
