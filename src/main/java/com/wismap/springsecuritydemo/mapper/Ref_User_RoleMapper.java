package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_User_Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Ref_User_RoleMapper {

    Ref_User_Role select(@Param("userid") Long userid, @Param("roleid") Long roleid);

    int deleteByPrimaryKey(@Param("userid") Long userid, @Param("roleid") Long roleid);

    int insert(Ref_User_Role record);

    int deleteByRoleId(@Param("roleid") Long roleid);

    int deleteByUserId(@Param("userid") Long userid);

}