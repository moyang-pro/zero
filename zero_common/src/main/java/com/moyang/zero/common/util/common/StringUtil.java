package com.moyang.zero.common.util.common;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil
 * 字符串工具类
 *
 * @author sunjialin10
 * @date 2019/11/15 17:29
 */
public class StringUtil {

    /**
     * 逗号分割
     */
    public static final Splitter SPLITTER_COMMA = Splitter.on(",").trimResults().omitEmptyStrings();

    public static final Joiner JOINER_COMMA = Joiner.on(",").skipNulls();

    /**
     * 字符串转long
     *
     * @param str          str
     * @param defaultValue 默认值
     * @return long
     */
    public static long str2Long(String str, long defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        long value;
        try {
            value = Long.parseLong(str);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    public static boolean isEmail(String email){
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
