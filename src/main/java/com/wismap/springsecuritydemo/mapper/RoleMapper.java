package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    /**
     * 根据角色名查询
     * @param roleName  角色名
     * @return          操作状态
     */
    Role selectByRoleName(@Param("roleName") String roleName);

    Integer selectAllCount(@Param("roleName") String roleName, @Param("roleNameLocal") String roleNameLocal);

    /**
     * 查询所有角色
     * @return              角色集合
     */
    List<Role> selectALL(@Param("limit") Long limit, @Param("offset") Long offset, @Param("roleName") String roleName, @Param("roleNameLocal") String roleNameLocal);

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

    Long update(Role role);
}