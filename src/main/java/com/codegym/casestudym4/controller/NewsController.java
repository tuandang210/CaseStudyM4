package com.codegym.casestudym4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class NewsController {

    @GetMapping("/1")
    public ModelAndView showNew1(){
        return new ModelAndView("/views/news/new1");
    }

    @GetMapping("/2")
    public ModelAndView showNew2(){
        return new ModelAndView("/views/news/new2");
    }

    @GetMapping("/3")
    public ModelAndView showNew3(){
        return new ModelAndView("/views/news/new3");
    }

    @GetMapping("/4")
    public ModelAndView showNew4(){
        return new ModelAndView("/views/news/new4");
    }
}
