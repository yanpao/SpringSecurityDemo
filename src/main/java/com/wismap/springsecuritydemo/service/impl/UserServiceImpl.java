package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.*;
import com.wismap.springsecuritydemo.model.*;
import com.wismap.springsecuritydemo.service.IUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;
    private Ref_User_RoleMapper ref_user_roleMapper;
    private Ref_User_PositionMapper ref_user_positionMapper;
    private Ref_User_PacMapper ref_user_pacMapper;
    private PasswordEncoder passwordEncoder;
    private SessionRegistry sessionRegistry;
    private Ref_Privilege_RoleMapper ref_privilege_roleMapper;
    private ResourceMapper resourceMapper;
    private PageMapper pageMapper;

    public UserServiceImpl(UserMapper userMapper,
                           Ref_User_RoleMapper ref_user_roleMapper,
                           Ref_User_PositionMapper ref_user_positionMapper,
                           Ref_User_PacMapper ref_user_pacMapper,
                           PasswordEncoder passwordEncoder,
                           SessionRegistry sessionRegistry,
                           Ref_Privilege_RoleMapper ref_privilege_roleMapper,
                           ResourceMapper resourceMapper,
                           PageMapper pageMapper)
    {
        this.userMapper=userMapper;
        this.ref_user_roleMapper=ref_user_roleMapper;
        this.ref_user_positionMapper=ref_user_positionMapper;
        this.ref_user_pacMapper=ref_user_pacMapper;
        this.passwordEncoder=passwordEncoder;
        this.sessionRegistry=sessionRegistry;
        this.ref_privilege_roleMapper=ref_privilege_roleMapper;
        this.resourceMapper=resourceMapper;
        this.pageMapper=pageMapper;
    }

    public User insert(User user)throws Exception
    {
        User origin = userMapper.select(user.getLoginname());
        if (origin!=null)
            throw new Exception("用户已经存在");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setGenTime(new Date());
        user.setStatus(true);
        try {
            Integer result = userMapper.insert(user);
            if (result>0)
                return userMapper.select(user.getLoginname());
        }
        catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    public Integer delete(String loginname) throws Exception
    {
        User user = userMapper.select(loginname);
        if (user==null)
            throw new Exception("用户不存在！");
        //delete user
        Integer usercount=userMapper.delete(loginname);
        if (usercount>0) {//delete related roles
            ref_user_roleMapper.deleteByUserId(user.getId().longValue());
            ref_user_positionMapper.deleteByUserId(user.getId());
            ref_user_pacMapper.deleteByUserId(user.getId());
        }
        return usercount;
    }

    public User select(String loginname)
    {
        User user = userMapper.select(loginname);
        if (user!=null)
        {
            user.setPassword(null);
            return user;
        }
        else
            return null;
    }

    public Map<String,Object> selectAll(String name,Long limit,Long offset)
    {
        List<User> all = userMapper.selectAll(name,limit,offset);

        if (all.size()>0)
        {
            for(User user : all)
                user.setPassword(null);
        }
        Integer count = userMapper.selectAllCount(name);
        Map<String,Object> result = new HashMap<>();
        result.put("count",count);
        result.put("users",all);

        return result;
    }

    public User update(User user)
    {
        User userupdate=new User();
        userupdate.setLoginname(user.getLoginname());
        userupdate.setLocalusername(user.getLocalusername());
        userupdate.setMobile(user.getMobile());
        userupdate.setEmail(user.getEmail());
        userupdate.setImg(user.getImg());
        if (userMapper.update(userupdate)>0) {
            return userMapper.select(userupdate.getLoginname());
        }
        else
        {
            return null;
        }
    }

    public Map<String,Object> getAllPrivileges(String loginname)
    {
        //获取用户
        User user=userMapper.select(loginname);
        //获取角色
        List<Long> Privelege_resourceid = new ArrayList<>();
        List<Long> Privelege_pageid = new ArrayList<>();
        for(Role role:user.getRolesList())
        {
            Privelege_resourceid.addAll(ref_privilege_roleMapper.selectPriByRoleid(role.getId(),1L));
            Privelege_pageid.addAll(ref_privilege_roleMapper.selectPriByRoleid(role.getId(),2L));
        }
        //获取页面和资源
        List<Resource> resources=new ArrayList<>();
        if (Privelege_resourceid.size()>0)
            resources=resourceMapper.selectByPrivilegeIds(Privelege_resourceid.toArray(new Long[((ArrayList) Privelege_resourceid).size()]));
        List<Page> pages=new ArrayList<>();
        if (Privelege_pageid.size()>0)
            pages=pageMapper.selectPageByPrivilegeIds(Privelege_pageid.toArray(new Long[((ArrayList) Privelege_pageid).size()]));

        Map<String,Object> result = new HashMap<>();
        result.put("resources",resources);
        result.put("page",pages);
        return result;
    }

    public Boolean ResetPassword(String oldpassword,String newpassword)throws Exception{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userMapper.select(username);

        if (!passwordEncoder.matches(oldpassword, user.getPassword()))
            throw new Exception("旧密码错误！");

        User userupdate=new User();
        userupdate.setLoginname(user.getLoginname());
        userupdate.setPassword(passwordEncoder.encode(newpassword));
        userMapper.update(userupdate);
        return true;
    }

    public Boolean ManagePassword(String loginname,String password)throws Exception{
        User user = userMapper.select(loginname);
        if (user==null)
            throw new Exception("未查询到用户，操作无法完成！");

        User userupdate=new User();
        userupdate.setLoginname(user.getLoginname());
        userupdate.setPassword(passwordEncoder.encode(password));
        userMapper.update(userupdate);
        return true;
    }

    public Boolean UpdateStatus(String loginname,Boolean status)throws Exception{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        if (username.equals(loginname))
            throw new Exception("不可以冻结本人账户！");

        User userupdate=new User();
        userupdate.setLoginname(loginname);
        userupdate.setStatus(status);
        userMapper.update(userupdate);

        //强制用户下线
        List<Object> users =sessionRegistry.getAllPrincipals();

        for (Object otherprincipal : users)
        {
            String otherUserName = ((UserDetails)otherprincipal).getUsername();
            if (otherUserName.equals(loginname)) {
                List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(otherprincipal, false);
                if (null != sessionsInfo && sessionsInfo.size() > 0) {
                    for (SessionInformation sessionInformation : sessionsInfo) {
                        sessionInformation.expireNow();
                    }
                }
            }
        }

        return true;
    }

    public Boolean GrantPAC(Integer userid,String paccode)throws Exception
    {
        if (ref_user_pacMapper.select(userid,paccode)!=null)//防止重复授权
        {
            throw new Exception("区域已经授权该用户！");
        }
        else if (ref_user_pacMapper.selectbyuser(userid)!=null)
        {
            throw new Exception("用户已经授权其他区域，请先取消其他区域授权！");
        }
        else if (ref_user_pacMapper.selectbypac(paccode)!=null)
        {
            throw new Exception("区域已经授权其他用户用户！");
        }
        else {
            Ref_User_Pac ref_user_pac = new Ref_User_Pac(userid, paccode);
            if (ref_user_pacMapper.insert(ref_user_pac) > 0)
                return true;
            else
                return false;
        }
    }

    public Boolean RevokePAC(Integer userid,String paccode)
    {
        if(ref_user_pacMapper.delete(userid,paccode)>0)
            return true;
        else
            return false;
    }

    public Boolean GrantPosition(Integer Userid,Integer Positon)
    {
        Ref_User_Position ref_user_position = ref_user_positionMapper.select(Userid,Positon);
        if (ref_user_position!=null)//防止重复授权
        {
            return false;
        }
        else {
            ref_user_position = new Ref_User_Position(Userid,Positon);
            if (ref_user_positionMapper.insert(ref_user_position) > 0)
                return true;
            else
                return false;
        }
    }

    public Boolean RevokePosition(Integer Userid,Integer Positon)
    {
        if(ref_user_positionMapper.delete(Userid,Positon)>0)
            return true;
        else
            return false;
    }

    public Boolean AuthorizeRole(Integer userid, List<Long> RoleIDs)
    {
        try {
            ref_user_roleMapper.deleteByUserId(userid.longValue());
            for (Long roleid : RoleIDs) {
                if (ref_user_roleMapper.select(userid.longValue(),roleid)!=null)
                    continue;
                Ref_User_Role ref_user_role = new Ref_User_Role(userid.longValue(), roleid);
                ref_user_roleMapper.insert(ref_user_role);
            }
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
