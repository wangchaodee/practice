package com.iflytek.staff.chao.interview.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class StudentService {

    Map<String,String> studentsMap ;

    public StudentService() {
        this.studentsMap = new HashMap<>();
    }

    public void loadStudentsFromCsv(String filePath){
        //     opencsv   姓名,专业
        studentsMap.put("张三","计算机") ;
        studentsMap.put("李四","英语") ;
        studentsMap.put("王五","数学") ;
        studentsMap.put("赵六","⽂学") ;
    }

    public String find(String name){
        if(studentsMap.containsKey(name))
            return studentsMap.get(name);
        return "";
    }
}
