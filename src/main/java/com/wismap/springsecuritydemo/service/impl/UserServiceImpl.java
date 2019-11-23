package com.wismap.springsecuritydemo.service.impl;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import com.wismap.springsecuritydemo.mapper.Ref_User_RoleMapper;
import com.wismap.springsecuritydemo.mapper.UserMapper;
import com.wismap.springsecuritydemo.model.Ref_User_Role;
import com.wismap.springsecuritydemo.model.User;
import com.wismap.springsecuritydemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer delete(String loginname)
    {
        return userMapper.delete(loginname);
    }

    public User select(String loginname)
    {
        return userMapper.select(loginname);
    }

    public Integer update(User user)
    {
        return userMapper.update(user);
    }

    public Boolean AssignRole(String loginname, List<Long> RoleIDs)
    {
        try {
            Integer userid = userMapper.select(loginname).getId();
            for (Long roleid : RoleIDs) {
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
