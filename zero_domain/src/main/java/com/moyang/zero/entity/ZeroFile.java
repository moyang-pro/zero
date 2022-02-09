package com.moyang.zero.entity;

import com.moyang.zero.entity.Template;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件上传记录
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ZeroFile对象", description="文件上传记录")
public class ZeroFile extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名字")
    private String fileName;

    @ApiModelProperty(value = "文件链接")
    private String fileUrl;


}
