package com.wismap.springsecuritydemo.model;

import java.io.Serializable;

public class Ref_User_Pac implements Serializable {

    private Integer userId;
    private String pac;

    public Ref_User_Pac(Integer userId, String pac) {
        this.userId = userId;
        this.pac = pac;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }
}