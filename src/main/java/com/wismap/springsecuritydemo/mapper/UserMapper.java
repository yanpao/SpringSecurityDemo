package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    int delete(String loginname);

    int insert(User record);

    User select(String loginname);

    int update(User record);
}