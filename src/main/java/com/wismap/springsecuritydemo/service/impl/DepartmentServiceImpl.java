package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.DepartmentMapper;
import com.wismap.springsecuritydemo.model.Department;
import com.wismap.springsecuritydemo.service.IDepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper)
    {
        this.departmentMapper=departmentMapper;
    }

    public Department selectById(Integer id)
    {
        return departmentMapper.selectById(id);
    }

    public int delete(Integer id)
    {
        return departmentMapper.delete(id);
    }

    public int insert(Department record)
    {
        return departmentMapper.insert(record);
    }

    public int update(Department record)
    {
        return departmentMapper.update(record);
    }

}
