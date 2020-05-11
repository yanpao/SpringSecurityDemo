package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.Role;

import java.util.List;
import java.util.Map;

public interface IRoleService {

    /**
     * 查询所有角色
     * @return 角色集合
     */
    Map<String,Object> selectALL(Long limit, Long offset, String roleName, String roleNameLocal);

    List<Long> getRolePri(Long roleid, Long pritype);

    /**
     * 插入角色
     * @param record    角色对象
     * @return          操作状态
     */
    Long insert(Role record);

    /**
     * 删除角色
     * @param id    角色id
     * @return      操作状态
     */
    Long delete(Long id);

    /**
     * 角色授权
     */
     Boolean Authorize(Long roleID, Long[] privilegeIds);

     Boolean Revoke(Long roleID, Long[] privilegeIds);

    Boolean Update(Role role);

}
