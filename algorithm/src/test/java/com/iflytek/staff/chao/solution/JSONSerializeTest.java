package com.iflytek.staff.chao.solution;

import junit.framework.TestCase;

/**
 * @author : hamilton
 * @Description: 测试JSON序列化
 * @date Date : 2023年02月07日 20:22
 */
public class JSONSerializeTest  {

    public void testSerialize() {

        try {
            String json = JSONSerialize.serialize(new User("张三" ,"合肥市123456路" ,31,true));
            System.out.println(json);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    class User{
        String name ;
        String address ;
        int age ;
        boolean male ;

        public User(String name, String address, int age, boolean male) {
            this.name = name;
            this.address = address;
            this.age = age;
            this.male = male;
        }
    }
}