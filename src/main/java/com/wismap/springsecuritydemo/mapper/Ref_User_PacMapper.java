package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_User_Pac;
import org.springframework.stereotype.Repository;

@Repository
public interface Ref_User_PacMapper {
    Ref_User_Pac select(Integer userid, String pac);

    Ref_User_Pac selectbyuser(Integer userid);

    Ref_User_Pac selectbypac(String pac);

    int delete(Integer userid, String pac);

    int deleteByUserId(Integer userid);

    int deleteByPAC(String pac);

    int insert(Ref_User_Pac record);
}