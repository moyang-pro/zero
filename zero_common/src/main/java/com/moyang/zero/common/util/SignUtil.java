package com.moyang.zero.common.util;

import com.moyang.zero.common.factory.Eum;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 签名
 * @ClassName SignUtil
 * @Author moyang
 * @Email 1542051400@qq.com
 * @date 2021.07.23 00:51
 */
public class SignUtil {
    private static final String DEFAULT_SECRET = "moyang-5251";

   public static  String productOneEum(String emy,String phone,String platCode){
         return Eum.builder().emy(emy).phone(phone).platCode(platCode).build().toString();
   }

}
