package com.ams.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "t_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany
    @JoinColumn(name = "role_ids")
    private List<Role> roles;

    @Column(name = "is_locked")
    private boolean isLocked;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_pwd_expired")
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
}
