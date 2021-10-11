package com.ams.service.po;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserPO {

    public static final String FIELD_ID = "id";
    public static final String FIELD_EMPLOYEE_ID = "employee_id";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_IS_LOCKED = "is_locked";
    public static final String FIELD_EXPIRATION_DATE = "expiration_date";
    public static final String FIELD_PWD_EXPIRATION_DATE = "pwd_expiration_date";
    public static final String FIELD_IS_SYSTEM = "is_system";

    private long id;
    private EmployeePO employee;
    private String username;
    private String password;
    private boolean isLocked;
    private boolean isSystem;
    private Date expirationDate;
    private Date pwdExpirationDate;
    private List<RolePO> roles = Collections.emptyList();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeePO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeePO employee) {
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Date expirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date isPwdExpirationDate() {
        return pwdExpirationDate;
    }

    public void setPwdExpirationDate(Date pwdExpirationDate) {
        this.pwdExpirationDate = pwdExpirationDate;
    }

    public List<RolePO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePO> roles) {
        this.roles = roles;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }
}
