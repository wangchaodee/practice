package com.iflytek.staff.chao.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author : hamilton
 * @Description: 获取网络资源
 * @date Date : 2023年02月11日 17:41
 */
public class URLDemo {
    public static void main(String[] args) {
        try {
            getUrl("https://leetcode.cn/u/wang-chao-xk/");
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    private static void getUrl(String urls) throws IOException  {
        URL url = new URL(urls);

        /* 字节流 */
        InputStream is = url.openStream();

        /* 字符流 */
        InputStreamReader isr = new InputStreamReader(is, "utf-8");

        /* 提供缓存功能 */
        BufferedReader br = new BufferedReader(isr);

        String line;
        int l =0 ;
        while ((line = br.readLine()) != null) {
            System.out.printf("line : %s , content: %s ,\n",l++,line);
        }

        br.close();
    }
}
