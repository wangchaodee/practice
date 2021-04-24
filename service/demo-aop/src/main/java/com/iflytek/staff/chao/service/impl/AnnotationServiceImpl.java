package com.iflytek.staff.chao.service.impl;

import com.iflytek.staff.chao.aop.PointCutAnnotation;
import com.iflytek.staff.chao.service.AnnotationService;
import org.springframework.stereotype.Service;

//@Slf4j
@Service("annotationService")
public class AnnotationServiceImpl implements
        AnnotationService {


    @Override
    @PointCutAnnotation(name = "annotation add()")
    public void add() {
        System.out.println("AnnotationService add ");
    }
}
