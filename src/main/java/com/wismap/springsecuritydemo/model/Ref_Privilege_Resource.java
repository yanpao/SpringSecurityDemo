package com.wismap.springsecuritydemo.model;

public class Ref_Privilege_Resource {
    private Long priId;

    private Long resId;

    public Ref_Privilege_Resource(Long priId, Long resId) {
        this.priId = priId;
        this.resId = resId;
    }

    public Ref_Privilege_Resource() {
        super();
    }

    public Long getPriId() {
        return priId;
    }

    public void setPriId(Long priId) {
        this.priId = priId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }
}