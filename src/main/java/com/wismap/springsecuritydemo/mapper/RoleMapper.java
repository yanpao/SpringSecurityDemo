package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    /**
     * 根据角色名查询
     * @param roleName  角色名
     * @return          操作状态
     */
    Role selectByRoleName(@Param("roleName") String roleName);

    /**
     * 查询所有角色
     * @return              角色集合
     */
    List<Role> selectALL();

    /**
     * 根据资源路径获取访问该资源需要的全部角色
     * @param url 资源路径的url
     * @return 访问该资源需要的角色
     */
    List<Role> selectByResources(@Param("url") String url);

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
    Long delete(@Param("id") Long id);
}