package com.wismap.springsecuritydemo.security;

import com.wismap.springsecuritydemo.mapper.RoleMapper;
import com.wismap.springsecuritydemo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Map<String, List<ConfigAttribute>> allConfigAttribute;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        List<Role> allRoles = roleMapper.selectByResources(url);
        for(Role role : allRoles)
        {
            SecurityConfig securityConfig=new SecurityConfig("ROLE_"+role.getRoleName());
            attributes.add(securityConfig);
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
