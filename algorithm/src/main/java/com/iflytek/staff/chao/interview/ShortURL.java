package com.iflytek.staff.chao.interview;

import sun.security.provider.MD5;

import java.util.Base64;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class ShortURL {

    MD5 md5 ;
    Base64.Encoder encoder ;
    Base64.Decoder decoder ;
     ShortURL(){
        md5 = new MD5();
        encoder = Base64.getUrlEncoder();
        decoder = Base64.getUrlDecoder();
    }

//    String generateShortURL(String longUrl){
//        String md5 = MD5
//    }
}
