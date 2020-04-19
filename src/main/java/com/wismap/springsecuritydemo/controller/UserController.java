package com.wismap.springsecuritydemo.controller;

import com.alibaba.fastjson.JSON;
import com.wismap.springsecuritydemo.model.User;
import com.wismap.springsecuritydemo.service.IUserService;
import com.wismap.springsecuritydemo.utils.BaseController;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET,produces = "application/json")
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
    public String SelectUserInfo(@RequestParam(value = "loginname") String loginname)
    {
            User user = userService.select(loginname);
            return JSON.toJSONString(user);
    }

    @PostMapping(value = "update", produces = "application/json")
    public String UpdateUser(@RequestBody User user)
    {
        User result = userService.update(user);
        if (result!=null) {
            return JSON.toJSONString(result);
        }
        else
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).toString();
        }
    }

    @PostMapping(value = "delete",produces = "application/json")
    public String DeleteUser(@RequestParam(value = "loginname") String loginname)
    {
        Integer result = userService.delete(loginname);
        if ()
    }

    @RequestMapping(value = "/Authorize",method = RequestMethod.POST,produces = "application/json")
    public String AuthorizeRole(@RequestParam(value = "loginname") String loginname,
                             @RequestParam(value = "roleids") List<Long> RoleIDs)
    {
        if (userService.AuthorizeRole(loginname,RoleIDs))
        {
            return renderSuccess();
        }
        else
        {
            return renderError("授予角色失败");
        }
    }

    @RequestMapping(value = "/Revoke",method = RequestMethod.POST,produces = "application/json")
    public String RevokeRole(@RequestParam(value = "loginname") String loginname,
                             @RequestParam(value = "roleids") List<Long> RoleIDs)
    {
        if (userService.RevokeRole(loginname,RoleIDs))
        {
            return renderSuccess();
        }
        else
        {
            return renderError("取消角色授权失败");
        }
    }

}
