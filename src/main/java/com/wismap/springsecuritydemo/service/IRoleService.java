package com.wismap.springsecuritydemo.service;

import com.alibaba.fastjson.JSONObject;
import com.wismap.springsecuritydemo.model.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 根据角色名查询
     * @param roleName  角色名
     * @return          操作状态
     */
    JSONObject selectByRoleName(String roleName);

    /**
     * 查询所有角色
     * @return 角色集合
     */
    List<Role> selectALL();

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
     Boolean Authorize(Long roleID,Long[] privilegeIds);

}
