package com.wismap.springsecuritydemo.security;

import com.wismap.springsecuritydemo.mapper.UserMapper;
import com.wismap.springsecuritydemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String loginname) throws UsernameNotFoundException
    {
        User user=userMapper.select(loginname);
        if (user==null)
            //在这里不要抛出UsernameNotFoundException异常，前端获取不到UsernameNotFoundException的异常信息
            throw new BadCredentialsException("用户不存在");
        return user;
    }

}
