package com.zjuee.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date> {
    public Date convert(String source) {
        if(source == null || source.length() == 0) {
            throw new RuntimeException("日期格式有误");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            return df.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
