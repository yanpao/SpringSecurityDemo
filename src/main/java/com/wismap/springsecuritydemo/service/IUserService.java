package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.User;

import java.util.List;

public interface IUserService {

    Integer insert(User user);

    Integer delete(String loginname);

    User select(String loginname);

    Integer update(User user);

    Boolean AssignRole(String loginname, List<Long> RoleIDs);
}
