package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.DepartmentMapper;
import com.wismap.springsecuritydemo.mapper.PositionMapper;
import com.wismap.springsecuritydemo.model.Position;
import com.wismap.springsecuritydemo.service.IPositionService;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements IPositionService {

    private PositionMapper positionMapper;
    private DepartmentMapper departmentMapper;

    public PositionServiceImpl(PositionMapper positionMapper,
                               DepartmentMapper departmentMapper)
    {
        this.positionMapper=positionMapper;
        this.departmentMapper=departmentMapper;

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

    public Boolean isLeader(Integer id)
    {
        Position position = selectById(id);
        if (position.getPid()==-1)
            return true;
        else {
            Position positionUp = selectById(position.getPid());
            //职位和上一级比较，如果部门一样，就证明不是该部门最大的，如果不一样，就是最大的
            if (position.getDepartmentId()==positionUp.getDepartmentId())
                return false;
            else
                return true;
        }
    }
}
