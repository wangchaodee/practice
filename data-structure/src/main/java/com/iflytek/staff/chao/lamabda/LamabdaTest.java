package com.iflytek.staff.chao.lamabda;

import java.util.Arrays;
import java.util.List;

/**
 * @author chaowang5
 * @Description:
 * @Date Created on 2019/12/13.
 */
public class LamabdaTest {


    public static void main(String[] args) {


    }

    public void test() {
        List list = Arrays.asList(new Employee("zhangsan", 56, 19999), new Employee("lisi", 36, 169999),
                new Employee("wangwu", 66, 369999));

    }


    class Employee {

        private String name;
        private Integer age;
        private Integer salary;


        public Employee(String name, Integer age, Integer salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }
    }
}
