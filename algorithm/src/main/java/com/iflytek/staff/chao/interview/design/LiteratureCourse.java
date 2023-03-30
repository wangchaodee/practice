package com.iflytek.staff.chao.interview.design;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : wangchaodee
 * @Description: 文学
 */
public class LiteratureCourse extends AbstractCourse {

    public LiteratureCourse() {
        majorName= "⽂学";
    }

    @Override
    public void printCourseOperation() {
        Set<Character>  chineseChars = new HashSet<>();
        int limit =3 ;
        while (chineseChars.size() <3){
            chineseChars.add(generateChineseCharacter()) ;
        }
        StringBuffer str = new StringBuffer();
        for(Character c : chineseChars){
            str.append(c);
        }
        System.out.printf("3个随机汉字 : %s \n" ,str);
    }

    // Unicode 汉字区间 0x4E00 → 0x9FA5
    private char generateChineseCharacter(){
       return  (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }

}
