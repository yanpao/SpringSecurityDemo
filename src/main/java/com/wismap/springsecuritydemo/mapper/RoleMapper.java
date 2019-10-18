package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色
     * @param limit         每页条数
     * @param offset        页码
     * @param roleName      角色名
     * @param roleNameLocal 角色中文名
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
     * 根据id查询
     * @param id    角色id
     * @return      角色对象
     */
    Role selectById(@Param("id") Long id);

    /**
     * 插入角色
     * @param record    角色对象
     * @return          操作状态
     */
    Long insert(Role record);

    /**
     * 修改角色
     * @param record  角色对象
     * @return          操作状态
     */
    Long update(Role record);


    /**
     * 删除角色
     * @param id    角色id
     * @return      操作状态
     */
    Long delete(@Param("id") Long id);


    /**
     * 查询角色数量
     * @param roleName      角色名
     * @param roleNameLocal 角色中文名
     * @return              操作状态
     */
    Long selectCount(@Param("roleName") String roleName, @Param("roleNameLocal") String roleNameLocal);

    /**
     * 根据角色名查询
     * @param roleName  角色名
     * @return          操作状态
     */
    Role selectByRoleName(@Param("roleName") String roleName);

    /**
     * 根据角色id 获得权限
     * @param pritype  权限类型
     * @param roleid    角色id
     * @param symbol    运维行政类型
     * @return          权限集合
     */
    List<Long> getPrivilege(@Param("pritype") Long pritype, @Param("roleid") Long roleid, @Param("symbol") Long symbol);

    /**
     * 根据职位id 获取权限
     * @param id
     * @return
     */
    List<Long> selectPosition(@Param("id") Long id);



}