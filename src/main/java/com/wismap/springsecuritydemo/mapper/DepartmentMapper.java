package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper {

    Department selectById(Integer id);

    int delete(Integer id);

    int insert(Department record);

    int update(Department record);
}