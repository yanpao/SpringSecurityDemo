package com.wismap.springsecuritydemo.model;

public class Ref_User_Position {
    private Integer userId;

    private Integer positionId;

    public Ref_User_Position(Integer userId, Integer positionId) {
        this.userId = userId;
        this.positionId = positionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}