package com.iflytek.staff.chao.file;

import com.iflytek.staff.chao.model.User;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : hamilton
 * @Description: transient 及序列化
 * @date Date : 2023年02月11日 16:51
 */
public class TransientTest {

    public static void main(String[] args) {
        User user = new User() ;
        user.setVersion("1.1");
        user.setUsername("zhangsan_ sxx");
        user.setCode("fsjjkjsfs");
        user.setGender("male");
        serializeToFile(FileConfig.FILE_SERIALIZE_OBJECT_FILE,user);

        User user1 = deserializeFromFile(FileConfig.FILE_SERIALIZE_OBJECT_FILE);
        System.out.println("version = " + user1.getVersion());
        System.out.println("userName = " + user1.getUsername());
        System.out.println("code = " + user1.getCode());
        System.out.println("gender = " + user1.getGender());

        user.setVersion("2.3");
        User user2 = deserializeFromFile(FileConfig.FILE_SERIALIZE_OBJECT_FILE);
        System.out.println("version = " + user2.getVersion());
        System.out.println("userName = " + user2.getUsername());
        System.out.println("code = " + user2.getCode());
        System.out.println("gender = " + user2.getGender());

    }

    public static void serializeToFile(String fileName,User user) {
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(user);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserializeFromFile(String fileName ) {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            T t = (T)in.readObject();
            in.close();
            return t ;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static User deserializeFromFile(String fileName ,User t ) {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
             t = (User)in.readObject();
            in.close();
            return t ;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}


