package com.wismap.springsecuritydemo.model;

import java.io.Serializable;
import java.util.List;

public class PAC implements Serializable {
    private String pac;

    private String name;

    private String parent;

    private List<PAC> Children;

    public PAC(String pac, String name, String parent) {
        this.pac = pac;
        this.name = name;
        this.parent = parent;
    }

    public PAC() {
        super();
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<PAC> getChildren() {
        return Children;
    }

    public void setChildren(List<PAC> children) {
        Children = children;
    }
}