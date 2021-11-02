package com.ams.service.dao;

import com.ams.service.po.UserPO;

import java.util.List;

/**
 * Responsible for db operations with User.
 *
 * @author Alexey Mironov
 */
public interface UserDAO {

    /**
     * Save user.
     *
     * @param user - user for save
     */
    void save(UserPO user);

    /**
     * Update user.
     *
     * @param user - user for update
     */
    void update(UserPO user);

    /**
     * Find user.
     *
     * @param username - user login
     */
    UserPO get(String username);

    /**
     * Find all users.
     */
    List<UserPO> getUsers();

    /**
     * Delete user by id.
     *
     * @param id - user id
     */
    void delete(long id);
}
