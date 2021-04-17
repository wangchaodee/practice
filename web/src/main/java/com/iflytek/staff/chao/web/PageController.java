package com.iflytek.staff.chao.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chaowang5 on 2017/10/23.
 */
//@Controller
//@RequestMapping("/hello")
public class PageController {


    @RequestMapping(value = "first", method = RequestMethod.GET)
    public String first(Model model) {
        model.addAttribute("hello", "world");
        return "first";
    }


}
