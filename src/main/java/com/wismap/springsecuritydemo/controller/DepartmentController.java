package com.wismap.springsecuritydemo.controller;

import com.alibaba.fastjson.JSON;
import com.wismap.springsecuritydemo.model.Department;
import com.wismap.springsecuritydemo.service.IDepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/department",produces = "application/json")
public class DepartmentController {

    private IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService)
    {
        this.departmentService=departmentService;
    }

    @GetMapping("/select")
    public String select(Integer id)
    {
        Department department = departmentService.selectById(id);
        return JSON.toJSONString(department);
    }

}
