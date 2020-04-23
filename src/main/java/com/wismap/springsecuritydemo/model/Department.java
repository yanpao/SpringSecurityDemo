package com.wismap.springsecuritydemo.model;

import java.util.List;

public class Department {
    private Integer id;

    private String departmentName;

    private String description;

    private Integer pid;

    private List<Department> Children;

    public Department(Integer id, String departmentName, String description, Integer pid) {
        this.id = id;
        this.departmentName = departmentName;
        this.description = description;
        this.pid = pid;
    }

    public Department() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Department> getChildren() {
        return Children;
    }

    public void setChildren(List<Department> children) {
        Children = children;
    }
}