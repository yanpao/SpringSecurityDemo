package com.wismap.springsecuritydemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        Map<String,String> map=new HashMap<>();
        map.put("code", "401");
        map.put("data", "您的账号在其他设备上已登录，如果不是您本人操作，请立即更改密码");
        map.put("msg", "session失效！");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }

}
