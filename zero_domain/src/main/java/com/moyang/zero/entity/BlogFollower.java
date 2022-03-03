package com.moyang.zero.entity;

import com.moyang.zero.entity.Template;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客粉丝表
 * </p>
 *
 * @author moyang
 * @since 2022-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogFollower对象", description="博客粉丝表")
public class BlogFollower extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客博主")
    private String blogOwner;

    @ApiModelProperty(value = "关注者|粉丝")
    private String follower;


}
