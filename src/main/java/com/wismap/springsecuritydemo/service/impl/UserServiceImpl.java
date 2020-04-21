package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.Ref_User_RoleMapper;
import com.wismap.springsecuritydemo.mapper.UserMapper;
import com.wismap.springsecuritydemo.model.Ref_User_Role;
import com.wismap.springsecuritydemo.model.User;
import com.wismap.springsecuritydemo.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;
    private Ref_User_RoleMapper ref_user_roleMapper;

    public UserServiceImpl(UserMapper userMapper,Ref_User_RoleMapper ref_user_roleMapper)
    {
        this.userMapper=userMapper;
        this.ref_user_roleMapper=ref_user_roleMapper;
    }

    @CacheEvict(cacheNames ="UserAll",allEntries=true)
    public User insert(User user)
    {
        Integer result = userMapper.insert(user);
        if (result>0)
            return userMapper.select(user.getLoginname());
        else
            return null;
    }

    @CacheEvict("UserInfo")
    public Integer delete(String loginname) throws Exception
    {
        User user = userMapper.select(loginname);
        if (user==null)
            throw new Exception("用户不存在！");
        //delete user
        Integer usercount=userMapper.delete(loginname);
        if (usercount>0) {//delete related roles
            ref_user_roleMapper.deleteByUserId(user.getId().longValue());
        }
        return usercount;
    }

    @Cacheable("UserInfo")
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

    @Cacheable("UserAll")
    public List<User> selectAll(Long limit,Long offset)
    {
        List<User> all = userMapper.selectAll(limit,offset);
        if (all.size()>0)
        {
            for(User user : all)
                user.setPassword(null);
        }

        return all;
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
