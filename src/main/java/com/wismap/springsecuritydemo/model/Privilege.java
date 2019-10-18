package com.wismap.springsecuritydemo.model;

import java.io.Serializable;

public class Privilege implements Serializable {
    private Long id;

    private Long pritype;

    private Long prisymbol; //0运维，1行政



    public Privilege() {
        super();
    }

    public Privilege(Long id, Long pritype,Long prisymbol) {
        this.id = id;
        this.pritype = pritype;
        this.prisymbol =  prisymbol;
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

    public Long getPrisymbol() {
        return prisymbol;
    }

    public void setPrisymbol(Long prisymbol) {
        this.prisymbol = prisymbol;
    }
}