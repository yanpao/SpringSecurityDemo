package com.wismap.springsecuritydemo.model;

import java.util.List;

public class Position {
    private Integer id;

    private String positionName;

    private String description;

    private Integer departmentId;

    private Integer pid;

    private List<Position> Children;

    public Position(Integer id, String positionName, String description, Integer departmentId, Integer pid) {
        this.id = id;
        this.positionName = positionName;
        this.description = description;
        this.departmentId = departmentId;
        this.pid = pid;
    }

    public Position() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Position> getChildren() {
        return Children;
    }

    public void setChildren(List<Position> children) {
        Children = children;
    }
}