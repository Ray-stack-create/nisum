package com.nisum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*; // fixed typo
import org.springframework.web.servlet.ModelAndView; // added missing import

@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    public ModelAndView handleHello() {
        String mssg = "Hello Spring Application";
        return new ModelAndView("hello", "message", mssg);
    }
}
