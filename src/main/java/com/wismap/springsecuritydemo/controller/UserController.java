package com.wismap.springsecuritydemo.controller;

import com.wismap.springsecuritydemo.model.Position;
import com.wismap.springsecuritydemo.model.User;
import com.wismap.springsecuritydemo.service.IPositionService;
import com.wismap.springsecuritydemo.service.IUserService;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user",produces = "application/json")
public class UserController{

    private IUserService userService;
    private IPositionService positionService;

    public UserController(IUserService userService,
                          IPositionService positionService) {
        this.userService = userService;
        this.positionService=positionService;
    }

    @GetMapping("/info")
    public String getUserInfo()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User user = userService.select(username);
            return HttpResult.success(user).toString();
        } else {
            String username = principal.toString();
            User user = userService.select(username);
            return HttpResult.success(user).toString();
        }
    }

    @GetMapping("/getAllPrivileges")
    public String getAllPrivileges()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return HttpResult.success(userService.getAllPrivileges(username)).toString();
        } else {
            String username = principal.toString();
            return HttpResult.success(userService.getAllPrivileges(username)).toString();
        }
    }

    @GetMapping("/select")
    public String SelectUserInfo(@RequestParam(value = "loginname") String loginname)
    {
            User user = userService.select(loginname);
            return HttpResult.success(user).toString();
    }

    @GetMapping("/selectall")
    public String SelectAllUser(@RequestParam(required = false)String name,
                                Long limit, Long offset)
    {
        Map<String,Object> result = userService.selectAll(name,limit,offset*limit);
        return HttpResult.success(result).toString();
    }

    @PostMapping("/update")
    public String UpdateUser(@RequestBody User user)
    {
        User result = userService.update(user);
        if (result!=null) {
            return HttpResult.success(result).setMessage("更新用户信息成功").toString();
        }
        else
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("更新用户信息失败").toString();
        }
    }

    @PostMapping("/resetPw")
    public String ResetPassword(String oldpassword,String newpassword){
        try {
            Boolean success =userService.ResetPassword(oldpassword,newpassword);
            if (success)
                return HttpResult.success().setMessage("修改密码成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("修改密码失败").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping("/managePw")
    public String ManagePassword(String loginname,String password){
        try {
            Boolean success =userService.ManagePassword(loginname,password);
            if (success)
                return HttpResult.success().setMessage("修改密码成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("修改密码失败").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping("updateStatus")
    public String UpdateStatus(String loginname,Boolean status)
    {
        try {
            Boolean success =userService.UpdateStatus(loginname,status);
            if (success)
                return HttpResult.success().setMessage("用户状态修改成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("用户状态修改失败").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }


    @PostMapping("/insert")
    public String InsertUser(@RequestBody User user)
    {
        try {
            User result = userService.insert(user);
            return HttpResult.success(result).setMessage("新增用户成功").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping("/grantpac")
    public String GrantPAC(Integer userid,String paccode)
    {
        try
        {
            if (userService.GrantPAC(userid,paccode))
            {
                return HttpResult.success().setMessage("授予区域成功").toString();
            }
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("区域授权失败").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping("/revokepac")
    public String RevokePAC(Integer userid,String paccode)
    {
        if (userService.RevokePAC(userid,paccode))
        {
            return HttpResult.success().setMessage("取消区域授权完成").toString();
        }
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("取消区域授权失败").toString();
    }

    @PostMapping("/grantPosition")
    public String GrantPosition(Integer userid,Integer positonid)
    {
        if (userService.GrantPosition(userid,positonid))
        {
            return HttpResult.success().setMessage("授予职位成功").toString();
        }
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("授予职位失败").toString();
    }

    @PostMapping("/revokePostion")
    public String RevokePosition(Integer userid,Integer positonid)
    {
        if (userService.RevokePosition(userid,positonid))
        {
            return HttpResult.success().setMessage("撤销职位成功").toString();
        }
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("撤销职位失败").toString();
    }

    @GetMapping("/isleader")
    public String IsLeader(String loginname)
    {
        User user = userService.select(loginname);
        if (user.getPositionList().size()>0)
            return HttpResult.success(user.getPositionList()).toString();
        else
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("此人没有职位").toString();
    }

    @PostMapping(value = "/delete")
    public String DeleteUser(@RequestParam(value = "loginname") String loginname)
    {
        try {
            Integer result = userService.delete(loginname);
            if (result>0)
                return HttpResult.success("删除用户成功").toString();
            else
                return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("删除用户失败").toString();
        }
        catch (Exception ex)
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage(ex.getMessage()).toString();
        }
    }

    @PostMapping(value = "/Authorize")
    public String AuthorizeRole(@RequestParam(value = "userid") Integer userid,
                             @RequestParam(value = "roleids") List<Long> RoleIDs)
    {
        if (userService.AuthorizeRole(userid,RoleIDs))
        {
            return HttpResult.success().setMessage("授予角色成功").toString();
        }
        else
        {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR).setMessage("授予角色失败").toString();
        }
    }

}
