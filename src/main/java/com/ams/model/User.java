package com.ams.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Employee employee;

    private List<Role> roles;

    private boolean isLocked;

    private boolean isExpired;

    private boolean isPwdExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }

        return roles.stream().flatMap(role -> role.getAuthorities().stream()).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isPwdExpired;
    }

    @Override
    public boolean isEnabled() {
        return !isLocked;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public void setPwdExpired(boolean pwdExpired) {
        isPwdExpired = pwdExpired;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
