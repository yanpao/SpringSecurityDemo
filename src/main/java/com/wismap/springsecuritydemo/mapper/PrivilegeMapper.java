package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Privilege;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeMapper {

    /**
     * 查询所有权限
     * @param pritype 权限类型
     * @return 权限信息
     */
    List<Privilege> selectAll(@Param("pritype") Long pritype);

    /**
     * 根据id查找权限
     * @param id 权限id
     * @return 权限信息
     */
    Privilege selectById(@Param("id") Long id);

    /**
     *根据id删除权限
     * @param id 权限id
     * @return 删除条数
     */
    Long delete(@Param("id") Long id);

    /**
     *新增权限
     * @param privilege 全新信息
     * @return 插入条数
     */
    Long insert(Privilege privilege);

    Long update(Privilege privilege);


}