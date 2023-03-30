package com.iflytek.staff.chao.interview.design;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: 上课
 */
public class AttendClass {


    // 学生查询
    StudentService studentService ;
    // 课程配置  Map
    Map<String , CourseAble> majorCourseMap ;


    private void registerCourse(Teacher teacher , ClassRoom classRoom , AbstractCourse course){
        course.setTeacher(teacher);
        course.setClassRoom(classRoom);
        majorCourseMap.put(course.getMajorName(), course);
    }

    protected void init(){
        studentService =new StudentService();
        studentService.loadStudentsFromCsv("students.csv");
        majorCourseMap = new HashMap<>();
        registerCourse(new Teacher("张四") , new ClassRoom("在A楼"),new  ComputerCourse());
        registerCourse(new Teacher("李五") , new ClassRoom("在B楼"),new EnglishCourse());
        registerCourse(new Teacher("王六") , new ClassRoom("在C楼"),new MathCourse());
        registerCourse(new Teacher("赵七") , new ClassRoom("在D楼"),new LiteratureCourse());
    }

    /**
     *
     * @param name
     * @return
     */
    public void attendClass(String name){
        // 查询
        // 输出 学生及上课地点  及课程操作
        String majorName = studentService.find(name);
        if("".equals(majorName)){
            return ;
        }
        System.out.printf("姓名 ：%s , 专业 :%s \n" ,name, majorName);
        CourseAble course = majorCourseMap.get(majorName);
        if(course==null) return;
        // 上课老师 、 教师地点
        course.printCourseInfo();
        // 上课操作
        course.printCourseOperation();
    }

    public static void main(String[] args) {
        AttendClass attendClass = new AttendClass() ;
        attendClass.init();

        attendClass.attendClass("张三");
        attendClass.attendClass("李四");
        attendClass.attendClass("王五");
        attendClass.attendClass("赵六");
    }


}
