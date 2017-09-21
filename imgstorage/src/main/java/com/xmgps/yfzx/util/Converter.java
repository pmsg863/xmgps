package com.xmgps.yfzx.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据类型转换器
 *
 * @author YYH
 */
public class Converter {
    // 月份格式
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
    // 日期格式
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // 时间格式
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    // 日期时间格式
    private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取指定日期的月份字符串，格式为yyyy-MM
     */
    public static String getMonthString(Date date) {
        return date == null ? "" : monthFormat.format(date);
    }

    /**
     * 获取指定日期的日期字符串，格式为yyyy-MM-dd
     */
    public static String getDateString(Date date) {
        return date == null ? "" : dateFormat.format(date);
    }

    /**
     * 获取指定日期的时间字符串，格式为HH:mm:ss
     */
    public static String getTimeString(Date date) {
        return date == null ? "" : timeFormat.format(date);
    }

    /**
     * 获取指定日期的日期时间字符串，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeString(Date date) {
        return date == null ? "" : datetimeFormat.format(date);
    }

    /**
     * 获取指定日期的月份字符串，格式为yyyy-MM
     */
    public static Date getMonth(String date) throws ParseException {
        if (date == null || date.length() == 0) {
            return null;
        }
        date = date.replace('.', '-');
        return monthFormat.parse(date);
    }

    /**
     * 获取指定日期的日期字符串，格式为yyyy-MM-dd
     */
    public static Date getDate(String date) throws ParseException {
        if (date == null || date.length() == 0) {
            return null;
        }
        date = date.replace('.', '-');
        return dateFormat.parse(date);
    }

    /**
     * 获取指定日期的时间字符串，格式为HH:mm:ss
     */
    public static Date getTime(String time) throws ParseException {
        if (time == null || time.length() == 0) {
            return null;
        }
        return timeFormat.parse(time);
    }

    /**
     * 获取指定日期的日期时间字符串，格式为yyyy-MM-dd HH:mm:ss
     */
    public static Date getDateTime(String date) throws ParseException {
        if (date == null || date.length() == 0) {
            return null;
        }
        return datetimeFormat.parse(date);
    }

    /**
     * 获取指定类所有属性(包括父类)
     *
     * @param clazz 指定类
     * @return Field[]
     */
    public static Field[] getAllDeclaredFields(Class clazz) {

        Field[] fields = clazz.getDeclaredFields();
        if (clazz.getSuperclass() != null) {
            Class superClazz = clazz.getSuperclass();
            Field[] superFields = getAllDeclaredFields(superClazz);
            Field[] allFields = new Field[fields.length + superFields.length];
            System.arraycopy(superFields, 0, allFields, 0, superFields.length);
            System.arraycopy(fields, 0, allFields, superFields.length, fields.length);
            return allFields;
        } else {
            return fields;
        }

    }

   public static BigDecimal parseBigDecimalValue(Double obj){
		if(obj==null){
			return null;
		}else{
			return BigDecimal.valueOf(obj);
		}
	}
}
