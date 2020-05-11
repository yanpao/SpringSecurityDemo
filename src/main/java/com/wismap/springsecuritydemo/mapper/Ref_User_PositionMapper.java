package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Ref_User_Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Ref_User_PositionMapper {

    Ref_User_Position select(@Param("userid") Integer userid, @Param("positionid") Integer positionid);

    int delete(@Param("userid") Integer userid, @Param("positionid") Integer positionid);

    int insert(Ref_User_Position record);

    Boolean deleteByPositionId(@Param("positionid") Integer positionid);

    Boolean deleteByUserId(@Param("userid") Integer userid);

}
