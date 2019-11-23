package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_Privilege_Resource;
import org.apache.ibatis.annotations.Param;

public interface Ref_Privilege_ResourceMapper {

    /**
     * 根据资源id查询关联关系
     * @param resid 资源id
     * @return 关联关系
     */
    Ref_Privilege_Resource selectByResid(@Param("resid") Long resid);

    /**
     * 根据权限id查询关联关系
     * @param priId 权限id
     * @return 关联关系
     */
    Ref_Privilege_Resource selectByPriid(@Param("priId") Long priId);

    /**
     * 新增权限和api对应关系
     * @param record 对应关系
     * @return 新增
     */
    Long insert(Ref_Privilege_Resource record);

    /**
     * 删除指定的对应关系
     * @param priId 权限id
     * @param resId api资源id
     * @return 删除条数
     */
    Long delete(@Param("priId") Long priId, @Param("resId") Long resId);

}