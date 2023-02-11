package com.iflytek.staff.chao.solution;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author : hamilton
 * @Description: 序列化JSON
 * @date Date : 2023年02月07日 20:05
 */
public class JSONSerialize {

    public static <T> String serialize(T object) throws IllegalAccessException {
        StringBuilder str = new StringBuilder();
        str.append("{");
        Class<? extends Object> clazz = object.getClass() ;
        //迭代类的属性数组
        for(Field field : clazz.getDeclaredFields()){
//            field.setAccessible(true);
            Object fieldValue = field.get(object);
            String fieldName = field.getName();
            if(fieldValue!= null) {
                str.append("\"").append(fieldName).append("\" : \"")
                        .append(fieldValue).append("\",");
            }
//            field.setAccessible(false);
        }
        str.deleteCharAt(str.length()-1);
        str.append("}");
        return str.toString();
    }
}
