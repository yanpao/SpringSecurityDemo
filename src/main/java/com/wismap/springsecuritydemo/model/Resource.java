package com.wismap.springsecuritydemo.model;

import java.io.Serializable;
import java.util.List;

public class Resource implements Serializable {
    private Long id;

    private String url;

    private String name;

    private String tag;

    private Long pid;

    private Long priid;

    private List<Resource> Children;

    public Resource(Long id, String url, String name,String tag,Long pid) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.tag = tag;
        this.pid = pid;
    }

    public Resource() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPriid() {
        return priid;
    }

    public void setPriid(Long priid) {
        this.priid = priid;
    }

    public List<Resource> getChildren() {
        return Children;
    }

    public void setChildren(List<Resource> children) {
        Children = children;
    }

}