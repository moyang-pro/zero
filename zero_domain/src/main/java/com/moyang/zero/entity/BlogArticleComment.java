package com.moyang.zero.entity;

import com.moyang.zero.entity.Template;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客文章评论回复表
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogArticleComment对象", description="博客文章评论回复表")
public class BlogArticleComment extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论回复的用户账号")
    private String cmtUser;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "文章ID")
    private Long articleId;

    @ApiModelProperty(value = "文章评论回复ID")
    private Long parentId;

    @ApiModelProperty(value = "是否为回复 0否 1是")
    private Integer reply;

    @ApiModelProperty(value = "排序位次")
    private Integer sortIndex;


}
