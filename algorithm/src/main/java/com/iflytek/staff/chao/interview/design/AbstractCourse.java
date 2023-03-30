package com.iflytek.staff.chao.interview.design;

import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public abstract class AbstractCourse implements CourseAble {

    ClassRoom classRoom ;

    Teacher teacher ;

    String majorName ;

    Random random ;

    public AbstractCourse() {
        this.random = new Random();
    }

    @Override
    public void printCourseInfo() {
        System.out.printf("专业名称：%s , 专业课老师：%s , 教师地点：%s \n" ,majorName , teacher.name, classRoom.roomAddress);
    }

    @Override
    public abstract void printCourseOperation() ;


    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getMajorName(){
        return this.majorName;
    }
}
