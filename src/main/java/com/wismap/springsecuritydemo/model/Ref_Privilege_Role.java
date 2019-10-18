package com.wismap.springsecuritydemo.model;

import java.io.Serializable;

public class Ref_Privilege_Role implements Serializable {
    private Long priId;

    private Long roleId;

    public Ref_Privilege_Role(Long priId, Long roleId) {
        this.priId = priId;
        this.roleId = roleId;
    }

    public Ref_Privilege_Role() {
        super();
    }

    public Long getPriId() {
        return priId;
    }

    public void setPriId(Long priId) {
        this.priId = priId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}