package com.zjuee.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换工具类
 */
public class ConvertUtils {

    /**
     * 日期转换为字符串
     * @param source
     * @return
     */
    public static String dateToString(Date source) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(source);
    }
}
