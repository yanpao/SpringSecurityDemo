package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseRouteController extends BaseController {

    @RequestMapping(value = "/")
    public String Index()
    {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String LoginPage()
    {
        return "login";
    }

}
