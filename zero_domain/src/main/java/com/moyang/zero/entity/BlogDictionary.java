package com.moyang.zero.entity;

import com.moyang.zero.entity.Template;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客系统字典表
 * </p>
 *
 * @author moyang
 * @since 2022-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogDictionary对象", description="博客系统字典表")
public class BlogDictionary extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典的key")
    private String dictCode;

    @ApiModelProperty(value = "所属类别")
    private String dictType;

    @ApiModelProperty(value = "字典的值")
    private String dictValue;


}
