package com.ams.service.impl;

import com.ams.service.dao.RightDAO;
import com.ams.service.dao.RoleDAO;
import com.ams.service.po.RightPO;
import com.ams.service.po.RolePO;
import com.ams.service.rm.RoleRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Responsible for db operations with Role.
 *
 * @author Alexey Mironov
 */
public class RoleDAOImpl implements RoleDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String saveRole;
    private final String getRolesByUserId;
    private final String getRoles;
    private final String deleteRoleById;
    private final String saveRoleRight;
    private final String deleteRoleRightByRoleId;
    private final RightDAO rightDAO;
    private final String updateRole;

    public RoleDAOImpl(JdbcTemplate jdbcTemplate,
                       @Qualifier("security-sql") final Properties sql,
                       RightDAO rightDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.saveRole = sql.getProperty("saveRole");
        this.getRolesByUserId = sql.getProperty("getRolesByUserId");
        this.getRoles = sql.getProperty("getRoles");
        this.deleteRoleById = sql.getProperty("deleteRoleById");
        this.saveRoleRight = sql.getProperty("saveRoleRight");
        this.deleteRoleRightByRoleId = sql.getProperty("deleteRoleRightByRoleId");
        this.updateRole = sql.getProperty("updateRole");
        this.rightDAO = rightDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(RolePO role) {
        jdbcTemplate.update(saveRole, role.getName());
        jdbcTemplate.update(saveRoleRight, createSaveRoleRightRequest(role.getId(), role.getRights()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(RolePO role) {
        jdbcTemplate.update(updateRole, role.getName(), role.getId());
        jdbcTemplate.update(deleteRoleRightByRoleId, role.getId());
        jdbcTemplate.update(saveRoleRight, createSaveRoleRightRequest(role.getId(), role.getRights()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RolePO> getRoles() {
        List<RolePO> rolePOs = jdbcTemplate.query(getRoles, RoleRowMapper.DEFAULT_ROW_MAPPER);
        return fillRights(rolePOs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RolePO> getUserRoles(long userId) {
        List<RolePO> rolePOs = jdbcTemplate.query(getRolesByUserId, RoleRowMapper.DEFAULT_ROW_MAPPER, userId);
        return fillRights(rolePOs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long id) {
        jdbcTemplate.update(deleteRoleById, id);
        jdbcTemplate.update(deleteRoleRightByRoleId, id);
    }

    private List<RolePO> fillRights(List<RolePO> roles) {
        Map<Long, RolePO> rolePOs = roles.stream().collect(Collectors.toMap(RolePO::getId, Function.identity()));
        Map<Long, List<RightPO>> rights = rightDAO.getRights(rolePOs.keySet());
        rolePOs.forEach((k, v) -> v.setRights(rights.getOrDefault(k, new ArrayList<>())));

        return new ArrayList<>(rolePOs.values());
    }

    private String createSaveRoleRightRequest(long roleId, Collection<RightPO> rights) {
        return saveRoleRight + rights.stream().map(x -> String.format("(%d, %s)", roleId, x.getId())).collect(Collectors.joining(","));
    }
}
