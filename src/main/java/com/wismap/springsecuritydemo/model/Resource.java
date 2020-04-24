package com.wismap.springsecuritydemo.model;

import java.io.Serializable;

public class Resource implements Serializable {
    private Long id;

    private String url;

    private String name;

    private String tag;

    public Resource(Long id, String url, String name,String tag) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.tag = tag;
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

}