package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Position;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionMapper {

    Position selectById(Integer id);

    int delete(Integer id);

    int insert(Position record);

    int update(Position record);
}