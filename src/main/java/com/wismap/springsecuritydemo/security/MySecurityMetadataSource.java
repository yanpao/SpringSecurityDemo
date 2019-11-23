package com.wismap.springsecuritydemo.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    //全部使用WebExpressionConfigAttribute
    private Map<String, List<ConfigAttribute>> allConfigAttribute;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();


        return null;
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
