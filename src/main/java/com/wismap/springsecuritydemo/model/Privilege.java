package com.wismap.springsecuritydemo.model;

import java.io.Serializable;

public class Privilege implements Serializable {
    private Long id;

    private Long pritype;


    public Privilege() {
        super();
    }

    public Privilege(Long id, Long pritype) {
        this.id = id;
        this.pritype = pritype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPritype() {
        return pritype;
    }

    public void setPritype(Long pritype) {
        this.pritype = pritype;
    }

}