package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/15.
 */
@Controller("webController")
@RequestMapping("first")
public class WebController {

    @RequestMapping("hello")
    public String hello(Model model , HttpServletRequest request){
        System.out.println("hello in webcontroller");
        model.addAttribute("msg","show me the money");
        return "hello";
    }
}
