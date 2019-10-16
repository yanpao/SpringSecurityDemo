package com.wismap.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseRouteController {

    @RequestMapping(value = "/login")
    public String LoginPage()
    {
        return "login";
    }

    @RequestMapping(value = "/")
    public String Index()
    {
        return "index";
    }

}
