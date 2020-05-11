package com.wismap.springsecuritydemo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class User implements UserDetails {
    private Integer id;

    private String loginname;

    private String password;

    private String localusername;

    private String mobile;

    private String email;

    private Date genTime;

    private Date lastLoginTime;

    private Integer loginCount;

    private Boolean status;

    private String img;

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    private List<Position> positionList;

    private List<Role> rolesList;

    private PAC pac;

    public User(Integer id, String loginname, String password, String localusername, String mobile, String email, Date genTime, Date lastLoginTime, Integer loginCount, Boolean status, String img) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.localusername = localusername;
        this.mobile = mobile;
        this.email = email;
        this.genTime = genTime;
        this.lastLoginTime = lastLoginTime;
        this.loginCount = loginCount;
        this.status = status;
        this.img = img;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalusername() {
        return localusername;
    }

    public void setLocalusername(String localusername) {
        this.localusername = localusername;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public PAC getPac() {
        return pac;
    }

    public void setPac(PAC pac) {
        this.pac = pac;
    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> Authorities = new ArrayList<GrantedAuthority>();
        for (Role role:this.rolesList) {
            SimpleGrantedAuthority simpleGrantedAuthorityAdmin = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            Authorities.add(simpleGrantedAuthorityAdmin);
        }
        return Authorities;
    }

    @Override
    public String getUsername()
    {
        return loginname;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return status;
    }
}