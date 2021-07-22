package com.moyang.zero.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * @Description 验证码工具类
 * @ClassName VerifyUtil
 * @Author User
 * @date 2021.07.22 22:38
 */
public class VerifyUtil {

    /**
     * 验证码数组
     */
    private static  final  String verifyNumArray = "0123456789";

    /**
     * 注册验证码生成
     */

   public static String getRandomRegisterCode(int n ){
       return RandomStringUtils.random(n, verifyNumArray);
   }

    public static void main(String[] args) {
        System.out.println("---------验证码测试---------");
        System.out.println(getRandomRegisterCode(4));
    }
}
