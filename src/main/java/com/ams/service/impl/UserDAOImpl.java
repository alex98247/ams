package com.ams.service.impl;

import com.ams.dao.RoleDAO;
import com.ams.dao.UserDAO;
import com.ams.service.po.UserPO;
import com.ams.service.rm.UserRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class UserDAOImpl implements UserDAO {

    private final RoleDAO roleDAO;
    private final String getUserByUsername;
    private final String getUsers;
    private final JdbcTemplate jdbcTemplate;
    private final String deleteUserById;
    private final String deleteUserRoleByUserId;
    private final String saveUser;
    private final String updateUser;

    public UserDAOImpl(JdbcTemplate jdbcTemplate,
                       @Qualifier("security-sql") final Properties sql,
                       RoleDAO roleDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.saveUser = sql.getProperty("saveUser");
        this.getUserByUsername = sql.getProperty("getUserByUsername");
        this.getUsers = sql.getProperty("getUsers");
        this.deleteUserById = sql.getProperty("deleteUserById");
        this.deleteUserRoleByUserId = sql.getProperty("deleteUserRoleByUserId");
        this.updateUser = sql.getProperty("updateUser");
        this.roleDAO = roleDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserPO user) {
        Map<String, Object> params = createParametersMap(user);
        roleDAO.save(user.getRoles());
        jdbcTemplate.update(saveUser, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserPO user) {
        Map<String, Object> params = createParametersMap(user);
        params.put(UserPO.FIELD_ID, user.getId());
        roleDAO.update(user.getRoles());
        jdbcTemplate.update(updateUser, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPO get(String username) {
        return jdbcTemplate.queryForObject(getUserByUsername, UserRowMapper.DEFAULT_ROW_MAPPER, username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserPO> getUsers() {
        return jdbcTemplate.query(getUsers, UserRowMapper.DEFAULT_ROW_MAPPER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long id) {
        jdbcTemplate.update(deleteUserById, id);
        jdbcTemplate.update(deleteUserRoleByUserId, id);
    }

    private Map<String, Object> createParametersMap(UserPO user) {
        Map<String, Object> params = new HashMap<>();
        params.put(UserPO.FIELD_USERNAME, user.getUsername());
        params.put(UserPO.FIELD_EMPLOYEE_ID, user.getEmployee().getId());
        params.put(UserPO.FIELD_EXPIRATION_DATE, user.expirationDate());
        params.put(UserPO.FIELD_IS_LOCKED, user.isLocked());
        params.put(UserPO.FIELD_IS_SYSTEM, user.isSystem());
        params.put(UserPO.FIELD_PASSWORD, user.getPassword());
        params.put(UserPO.FIELD_PWD_EXPIRATION_DATE, user.isPwdExpirationDate());
        return params;
    }
}
