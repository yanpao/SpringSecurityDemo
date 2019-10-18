package com.wismap.springsecuritydemo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String roleName;

    private String roleNameLocal;

    private String description;

    private Date genTime;

    private List<Privilege> privileges;

    public Role(Long id, String roleName, String roleNameLocal, String description, Date genTime) {
        this.id = id;
        this.roleName = roleName;
        this.roleNameLocal = roleNameLocal;
        this.description = description;
        this.genTime = genTime;
    }

    public Role() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleNameLocal() {
        return roleNameLocal;
    }

    public void setRoleNameLocal(String roleNameLocal) {
        this.roleNameLocal = roleNameLocal == null ? null : roleNameLocal.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}