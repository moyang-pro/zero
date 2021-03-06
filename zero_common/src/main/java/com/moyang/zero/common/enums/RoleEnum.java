package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 角色枚举
 * @ClassName RoleEnum
 * @Author moyang
 * @Email 1542051400@qq.com
 * @date 2021.07.23 22:03
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {

    /**
     * 普通用户，基础权限
     */
    ROLE_MYCR("COMMON_USER","普通用户"),
    /**
     *升级用户，部分高级权限
     */
    ROLE_MYPR("PRO_USER","专业用户"),
    /**
     * 超级用户，全部权限
     */
    ROLE_MYSR("SUPER_USER","超级用户");

    private  String code;

    private  String description;
}
