package com.wismap.springsecuritydemo.controller;

import com.alibaba.fastjson.JSON;
import com.wismap.springsecuritydemo.model.User;
import com.wismap.springsecuritydemo.service.IUserService;
import com.wismap.springsecuritydemo.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/info",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String getUserInfo()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User user = userService.select(username);
            return JSON.toJSONString(user);
        } else {
            String username = principal.toString();
            User user = userService.select(username);
            return JSON.toJSONString(user);
        }
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String SelectUserInfo(@RequestParam(value = "loginname") String loginname)
    {
            User user = userService.select(loginname);
            return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/AssignRole",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String AssignRole(@RequestParam(value = "loginname") String loginname,
                             @RequestParam(value = "roleids") List<Long> RoleIDs)
    {
        if (userService.AssignRole(loginname,RoleIDs))
        {
            return renderSuccess();
        }
        else
        {
            return renderError("授予角色失败");
        }
    }

}
