package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.Ref_User_RoleMapper;
import com.wismap.springsecuritydemo.mapper.UserMapper;
import com.wismap.springsecuritydemo.model.Ref_User_Role;
import com.wismap.springsecuritydemo.model.User;
import com.wismap.springsecuritydemo.service.IUserService;
import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Ref_User_RoleMapper ref_user_roleMapper;

    public Integer insert(User user)
    {
        return userMapper.insert(user);
    }

    @Cacheable("UserInfo")
    public Integer delete(String loginname)
    {
        //delete user
        Integer usercount=userMapper.delete(loginname);
        if (usercount>0) {//delete related roles
            Integer userid = userMapper.select(loginname).getId();
            ref_user_roleMapper.deleteByUserId(userid.longValue());
        }
        return usercount;
    }

    @Cacheable("UserInfo")
    public User select(String loginname)
    {
        return userMapper.select(loginname);
    }

    @CachePut(cacheNames = "UserInfo",key = "#user.loginname")
    public User update(User user)
    {
        if (userMapper.update(user)>0) {
            return userMapper.select(user.getLoginname());
        }
        else
        {
            return null;
        }
    }

    public Boolean AuthorizeRole(String loginname, List<Long> RoleIDs)
    {
        try {
            Integer userid = userMapper.select(loginname).getId();
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

    public Boolean RevokeRole(String loginname, List<Long> RoleIDs)
    {
        try {
            Integer userid = userMapper.select(loginname).getId();
            for (Long roleid : RoleIDs) {
                ref_user_roleMapper.deleteByPrimaryKey(userid.longValue(),roleid);
            }
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
