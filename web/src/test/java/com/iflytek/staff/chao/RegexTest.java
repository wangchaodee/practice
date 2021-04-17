package com.iflytek.staff.chao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chaowang5
 * @Description:
 * @Date Created on 2021/3/7.
 */
@RunWith(JUnit4.class)
public class RegexTest {

    @Test
    public void testReHtml() {
        //去除html标记
        Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
        String string = matcher.replaceAll("");
        System.out.println(string);
    }

    @Test
    public void testReplace() {
        //文字替换（全部）
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
//替换所有符合正则的数据
        System.out.println(matcher.replaceAll("Java"));

        //替换第一个符合正则的数据
        System.out.println(matcher.replaceFirst("Java"));


    }

}
