package com.ams.dao;

import com.ams.service.application.po.ApplicationPO;

import java.util.List;

public interface ApplicationDAO {

    ApplicationPO find(long id);

    void create(ApplicationPO application);

    void update(ApplicationPO application);

    void delete(long id);

    List<ApplicationPO> getAll(String username);
}
