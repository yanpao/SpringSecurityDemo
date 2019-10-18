package com.wismap.springsecuritydemo.model;

public class Ref_User_Role {
    private Long userid;

    private Long roleid;

    public Ref_User_Role(Long userid, Long roleid) {
        this.userid = userid;
        this.roleid = roleid;
    }

    public Ref_User_Role() {
        super();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}