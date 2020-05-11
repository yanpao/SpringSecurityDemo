package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.model.Role;
import com.wismap.springsecuritydemo.service.IRoleService;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="role",produces = "application/json;charset=utf-8")
public class RoleController{

    private IRoleService roleService;
    public RoleController(IRoleService roleService)
    {
        this.roleService=roleService;
    }

    @GetMapping("/all")
    public String AllRoles(@RequestParam(value = "limit") Long limit,
                           @RequestParam(value = "offset") Long offset,
                           @RequestParam(value = "roleName",required = false) String roleName,
                           @RequestParam(value = "roleNameLocal",required = false) String roleNameLocal)
    {
        return HttpResult.success(roleService.selectALL(limit,offset*limit,roleName,roleNameLocal)).toString();
    }

    @GetMapping("/privilege")
    public String  GetRolePrivilege(@RequestParam Long roleid,
                                    @RequestParam Long pritype)
    {
        return HttpResult.success(roleService.getRolePri(roleid,pritype)).toString();
    }

    @PostMapping("/insert")
    public String Insert(@RequestBody Role role)
    {
        if (roleService.insert(role)>0)
            return HttpResult.success().setMessage("新增角色成功").toString();
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("新增角色失败").toString();
    }

    @PostMapping("/delete")
    public String Delete(Long roleid)
    {
       if (roleService.delete(roleid)>0)
           return HttpResult.success().setMessage("删除角色成功").toString();
       else
           return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("删除角色失败").toString();
    }

    @PostMapping("/update")
    public String Update(@RequestBody Role role)
    {
        if (roleService.Update(role))
            return HttpResult.success().setMessage("更新角色成功").toString();
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("更新角色失败").toString();
    }

    @PostMapping("/authorize")
    public String Authorize(@RequestParam(value = "roleid")Long roleid,
                            @RequestParam(value = "privileges")Long[] priIds)
    {
        if (roleService.Authorize(roleid,priIds))
        {
            return HttpResult.success().setMessage("角色授权成功").toString();
        }
        else
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("角色授权失败").toString();
        }
    }

    @PostMapping("/revoke")
    public String Revoke(@RequestParam(value = "roleid")Long roleid,
                         @RequestParam(value = "privileges")Long[] priIds)
    {
        if (roleService.Revoke(roleid,priIds))
        {
            return HttpResult.success().setMessage("取消角色授权成功").toString();
        }
        else
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("取消角色授权失败").toString();
        }
    }


}
