package com.wismap.springsecuritydemo.service;


import com.wismap.springsecuritydemo.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    User insert(User user)throws Exception;

    Integer delete(String loginname)throws Exception;

    User select(String loginname);

    Map<String,Object> selectAll(String name, Long limit, Long offset);

    User update(User user);

    Map<String,Object> getAllPrivileges(String loginname);

    Boolean ResetPassword(String oldpassword, String newpassword)throws Exception;

    Boolean ManagePassword(String loginname, String password)throws Exception;

    Boolean UpdateStatus(String loginname, Boolean status)throws Exception;

    Boolean GrantPAC(Integer userid, String paccode) throws Exception;

    Boolean RevokePAC(Integer userid, String paccode);

    Boolean GrantPosition(Integer Userid, Integer Positon);

    Boolean RevokePosition(Integer Userid, Integer Positon);

    Boolean AuthorizeRole(Integer userid, List<Long> RoleIDs);
}
