package com.moyang.zero.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 博客系统用户表
 * </p>
 *
 * @author moyang
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BlogUser对象", description="博客系统用户表")
public class BlogUser extends Template {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客博主emy")
    private String blogUser;

    @ApiModelProperty(value = "性别(1:男2:女)")
    private Integer gender;

    @ApiModelProperty(value = "个人头像")
    private String avatar;

    @ApiModelProperty(value = "出生年月日")
    private LocalDate birthday;

    @ApiModelProperty(value = "自我简介最多150字")
    private String summary;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "QQ号")
    private String qqNumber;

    @ApiModelProperty(value = "微信号")
    private String weChat;

    @ApiModelProperty(value = "职业")
    private String occupation;

    @ApiModelProperty(value = "github地址")
    private String github;

    @ApiModelProperty(value = "gitee地址")
    private String gitee;

    @ApiModelProperty(value = "博主是否为VIP用户 0否 1普通VIP 2超级VIP")
    private Integer vipCode;

    @ApiModelProperty(value = "博客积分")
    private Integer point;

    @ApiModelProperty(value = "文章数")
    private Integer articleCount;

    @ApiModelProperty(value = "关注数量")
    private Integer followCount;

    @ApiModelProperty(value = "粉丝数量")
    private Integer followedCount;

    @ApiModelProperty(value = "问答数量")
    private Integer faqCount;


}
