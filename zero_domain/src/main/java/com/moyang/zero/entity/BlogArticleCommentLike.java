package com.moyang.zero.entity;

import com.moyang.zero.entity.Template;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客文章评论回复点赞表
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogArticleCommentLike对象", description="博客文章评论回复点赞表")
public class BlogArticleCommentLike extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客博主")
    private String blogOwner;

    @ApiModelProperty(value = "文章评论回复ID")
    private Long commentId;

    @ApiModelProperty(value = "是否为反向赞 0否 1是")
    private Integer dislike;


}
