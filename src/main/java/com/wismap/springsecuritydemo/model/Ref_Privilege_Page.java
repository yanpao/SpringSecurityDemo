package com.wismap.springsecuritydemo.model;

import java.io.Serializable;

public class Ref_Privilege_Page implements Serializable {
    private Long priId;

    private Long pageId;

    public Ref_Privilege_Page(Long priId, Long pageId) {
        this.priId = priId;
        this.pageId = pageId;
    }

    public Ref_Privilege_Page() {
        super();
    }

    public Long getPriId() {
        return priId;
    }

    public void setPriId(Long priId) {
        this.priId = priId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }
}