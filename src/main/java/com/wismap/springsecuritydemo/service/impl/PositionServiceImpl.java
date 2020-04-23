package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.PositionMapper;
import com.wismap.springsecuritydemo.model.Position;
import com.wismap.springsecuritydemo.service.IPositionService;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements IPositionService {

    private PositionMapper positionMapper;

    public PositionServiceImpl(PositionMapper positionMapper)
    {
        this.positionMapper=positionMapper;
    }

    public Position selectById(Integer id){
        return positionMapper.selectById(id);
    }

    public int delete(Integer id){
        return positionMapper.delete(id);
    }

    public int insert(Position record){
        return positionMapper.insert(record);
    }

    public int update(Position record){
        return positionMapper.update(record);
    }
}
