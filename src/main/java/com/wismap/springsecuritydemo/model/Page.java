package com.wismap.springsecuritydemo.model;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
    private Long id;

    private Long menutype;

    private String url;

    private String pagename;

    private String component;

    private Long sort;

    private String description;

    private Long pid;

    private String icon;

    private List<Page> children;
    private Long priid;
    private Long prisymbol;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Page(Long id, String url, String pagename, String component, Long sort, String description, Long pid, Long prisymbol) {
        this.id = id;
        this.url = url;
        this.pagename = pagename;
        this.component=component;
        this.sort=sort;
        this.description = description;
        this.pid = pid;
        this.prisymbol = prisymbol;
    }

    public Page(Long id, String url, String pagename, String component, Long sort, String description, Long pid, Long prisymbol,String icon) {
        this.id = id;
        this.url = url;
        this.pagename = pagename;
        this.component=component;
        this.sort=sort;
        this.description = description;
        this.pid = pid;
        this.prisymbol = prisymbol;
        this.icon = icon;
    }

    public Page() {
        super();
    }

    public Long getMenutype() {
        return menutype;
    }

    public void setMenutype(Long menutype) {
        this.menutype = menutype;
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename == null ? null : pagename.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<Page> getChildren() {
        return children;
    }

    public void setChildren(List<Page> children) {
        this.children = children;
    }

    public Long getPriid() {
        return priid;
    }

    public void setPriid(Long priid) {
        this.priid = priid;
    }

    public Long getPrisymbol() {
        return prisymbol;
    }

    public void setPrisymbol(Long prisymbol) {
        this.prisymbol = prisymbol;
    }
}