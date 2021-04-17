package com.iflytek.staff.chao.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chaowang5 on 2017/12/27.
 */
//@RestController
//@RequestMapping("rest")
public class DataController {

    @RequestMapping(value = "data", method = RequestMethod.GET)
    public String data() {
        return "return type of data ";
    }
}
