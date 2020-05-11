package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_Privilege_Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ref_Privilege_RoleMapper {

    /**
     * 新增角色和权限的关系
     * @param record 角色权限关系
     * @return 插入的条数
     */
    Long insert(Ref_Privilege_Role record);

    /**
     * 根据角色删除
     * @param roleId 角色id
     * @return 删除的条数
     */
    Long deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据权限删除
     * @param priId 权限id
     * @return 删除的条数
     */
    Long deleteByPriId(@Param("priId") Long priId);

    /**
     * 解除权限和角色关联
     * @param roleId 角色id
     * @param priId 权限id
     * @return 删除的条数
     */
    Long delete(@Param("roleId") Long roleId, @Param("priId") Long priId);

    /**
     * 查询角色有什么权限
     * @param roleId 角色id
     * @return 权限的id
     */
    List<Long> selectPriByRoleid(@Param("roleId") Long roleId, @Param("pritype") Long pritype);

    /**
     * 查询权限被分配给了那些角色
     * @param priId 权限id
     * @return 角色的id
     */
    List<Long> selectRoleidBypri(@Param("priId") Long priId);

    Ref_Privilege_Role select(@Param("priId") Long priId, @Param("roleId") Long roleId);

}