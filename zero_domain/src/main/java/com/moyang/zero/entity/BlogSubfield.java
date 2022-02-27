package com.moyang.zero.entity;

import com.moyang.zero.entity.Template;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客分栏表
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogSubfield对象", description="博客分栏表")
public class BlogSubfield extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客博主")
    private String blogOwner;

    @ApiModelProperty(value = "分栏名")
    private String subfieldName;


}
