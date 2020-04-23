package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.User;

import java.util.List;

public interface IUserService {

    User insert(User user);

    Integer delete(String loginname)throws Exception;

    User select(String loginname);

    List<User> selectAll(Long limit,Long offset);

    User update(User user);

    Boolean GrantPosition(Integer Userid,Integer Positon);

    Boolean AuthorizeRole(String loginname, List<Long> RoleIDs);

    Boolean RevokeRole(String loginname, List<Long> RoleIDs);
}
