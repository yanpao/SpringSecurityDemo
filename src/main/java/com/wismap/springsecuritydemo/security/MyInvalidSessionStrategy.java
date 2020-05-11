package com.wismap.springsecuritydemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyInvalidSessionStrategy implements InvalidSessionStrategy {

    private boolean createNewSession = true;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        Map<String,String> map=new HashMap<>();
        map.put("code", "401");
        map.put("data", "session失效，请重新登录");
        map.put("msg", "session失效！");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }

    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
