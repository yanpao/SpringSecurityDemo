package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.Department;

public interface IDepartmentService {

    Department selectById(Integer id);

    int delete(Integer id);

    int insert(Department record);

    int update(Department record);

}
