package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.Position;

public interface IPositionService {

    Position selectById(Integer id);

    int delete(Integer id);

    int insert(Position record);

    int update(Position record);

    Boolean isLeader(Integer id);

}
