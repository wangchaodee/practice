package com.iflytek.staff.chao.model;

import java.io.Serializable;

/**
 * @author : hamilton
 * @Description: User
 * @date Date : 2023年02月11日 16:55
 */
public class  User implements Serializable {

    private  static  String version ;
    private String username ;
    private String gender ;
    private transient String code ;

    public  String getVersion() {
        return version;
    }

    public  void setVersion(String version) {
        User.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
