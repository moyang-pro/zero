package com.moyang.zero.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 墨阳编码信息表
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MyCode对象", description="墨阳编码信息表")
public class MyCode extends Template {

    private static final long serialVersionUID = 1L;

    private Integer type;

    private String myCode;

    private String myName;

    private String description;


}
