package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_Privilege_Page;
import org.apache.ibatis.annotations.Param;

public interface Ref_Privilege_PageMapper {

    Ref_Privilege_Page selectByPriid(@Param("priId") Long priId);

    Ref_Privilege_Page selectByPageid(@Param("pageId") Long pageId);

    int insert(Ref_Privilege_Page record);

    Long delete(@Param("priId") Long priId, @Param("pageId") Long pageId);
}