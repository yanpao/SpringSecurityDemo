package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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

    @RequestMapping(value = "/test",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String Insert()
    {
        return renderSuccess("weeeee");
    }

}
