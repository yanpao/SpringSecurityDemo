package com.wismap.springsecuritydemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wismap.springsecuritydemo.model.Role;
import com.wismap.springsecuritydemo.service.IRoleService;
import com.wismap.springsecuritydemo.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/all",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String AllRoles()
    {
        List<Role> allRoles = roleService.selectALL();
        return JSON.toJSONString(allRoles);
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String SelectRole(@RequestParam(value = "rolename")String rolename)
    {
        JSONObject role = roleService.selectByRoleName(rolename);
        return role.toJSONString();
    }

    @RequestMapping(value = "/authorize",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String Authorize(@RequestParam(value = "roleid")Long roleid,
                            @RequestParam(value = "privileges")Long[] priIds)
    {
        if (roleService.Authorize(roleid,priIds))
        {
            return renderSuccess("角色授权成功");
        }
        else
        {
            return renderError();
        }
    }


}
