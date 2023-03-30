package com.iflytek.staff.chao.interview.design;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class EnglishCourse extends AbstractCourse {

    public EnglishCourse() {
        majorName="英语";
    }

    @Override
    public void printCourseOperation() {
        int m = random.nextInt(26);
        Set<Character> englistSet = new LinkedHashSet<>();
        while (englistSet.size()<m){
            englistSet.add(generateEnglishCharacter());
        }
        StringBuffer str = new StringBuffer();
        for(Character c : englistSet){
            str.append(c);
        }
        System.out.printf("m : %s , 字符串 : \n" ,m,str);
    }

    // 小写字母区间  97 - 122
    private char generateEnglishCharacter(){
        return  (char)(97 +  random.nextInt(26));
    }

}
