package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_User_Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Ref_User_PositionMapper {

    Ref_User_Position select(@Param("userid") Long userid, @Param("positionid") Long positionid);

    int delete(@Param("userid") Long userid, @Param("positionid") Long positionid);

    int insert(Ref_User_Position record);

    Boolean deleteByPositionId(@Param("positionid") Long positionid);

    Boolean deleteByUserId(@Param("userid") Long userid);

}
