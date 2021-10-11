package com.ams.service.dao;

import com.ams.service.po.UserPO;

import java.util.List;

public interface UserDAO {

    void save(UserPO user);

    void update(UserPO user);

    UserPO get(String username);

    List<UserPO> getUsers();

    void delete(long id);
}
