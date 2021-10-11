package com.ams.service.dao;

import com.ams.service.po.RolePO;

import java.util.List;

/**
 * Responsible for db operations with Role.
 *
 * @author Alexey Mironov
 */
public interface RoleDAO {

    /**
     * Save role.
     *
     * @param role - role to save
     */
    void save(RolePO role);

    /**
     * Update role.
     *
     * @param role - role to update
     */
    void update(RolePO role);

    /**
     * Get all roles.
     *
     * @return list of roles
     */
    List<RolePO> getRoles();

    /**
     * Get users roles by user id.
     *
     * @param userId - user id
     * @return list of users roles
     */
    List<RolePO> getUserRoles(long userId);

    /**
     * Delete role by id.
     *
     * @param id - role id
     */
    void delete(long id);
}
