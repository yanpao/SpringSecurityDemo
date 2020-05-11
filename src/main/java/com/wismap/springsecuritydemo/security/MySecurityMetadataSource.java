package com.wismap.springsecuritydemo.security;

import com.wismap.springsecuritydemo.mapper.RoleMapper;
import com.wismap.springsecuritydemo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleMapper roleMapper;

    @Value("#{'${security.needlogindontneedauth}'.split(',')}")
    private List<String> urllist;

    private Map<String, List<ConfigAttribute>> allConfigAttribute;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        if (url.contains("?"))
            url = url.substring(0,url.indexOf("?"));
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
        if (urllist.contains(url))
            return attributes;

        List<Role> allRoles = roleMapper.selectByResources(url);
        for(Role role : allRoles)
        {
            SecurityConfig securityConfig=new SecurityConfig("ROLE_"+role.getRoleName());
            attributes.add(securityConfig);
        }

        if (attributes.size()==0)
        {
            throw new IllegalArgumentException("该接口没有进行授权，请联系管理员为该接口进行授权");
        }
        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
