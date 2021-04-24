package com.iflytek.staff.chao.service;

import com.iflytek.staff.chao.aop.PointCutAnnotation;
import org.springframework.stereotype.Service;

//@Slf4j
@Service
public class AnnotationServiceAdd {


    @PointCutAnnotation(name = "annotation add()")
    public void add() {
        System.out.println("AnnotationService add ");
    }
}
