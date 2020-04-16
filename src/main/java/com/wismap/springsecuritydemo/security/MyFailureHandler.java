package com.wismap.springsecuritydemo.security;

import com.wismap.springsecuritydemo.utils.HttpResult;
import com.wismap.springsecuritydemo.utils.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        HttpResult httpResult = HttpResult.failure(ResultCodeEnum.Forbidden);
        httpResult.setMessage(exception.getMessage());
        response.getWriter().write(httpResult.toString());
        response.setStatus(403);
    }
}
