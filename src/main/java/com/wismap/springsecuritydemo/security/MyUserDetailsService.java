package com.wismap.springsecuritydemo.security;

import com.wismap.springsecuritydemo.mapper.UserMapper;
import com.wismap.springsecuritydemo.model.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyUserDetailsService implements UserDetailsService {

    UserMapper userMapper;

    public MyUserDetailsService(UserMapper userMapper)
    {
       this.userMapper=userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String loginname) throws UsernameNotFoundException
    {
        User user=userMapper.select(loginname);
        if (user==null)
            //在这里不要抛出UsernameNotFoundException异常，前端获取不到UsernameNotFoundException的异常信息
            throw new BadCredentialsException("用户不存在");
        if (!user.getStatus())
            throw new DisabledException("用户已禁用");
        user.setLoginCount(user.getLoginCount()==null ? 1 : user.getLoginCount()+1);
        user.setLastLoginTime(new Date());
        userMapper.update(user);
        return user;
    }
}
