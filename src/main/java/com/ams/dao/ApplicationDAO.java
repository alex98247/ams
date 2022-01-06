package com.ams.dao;

import com.ams.service.application.po.ApplicationPO;

public interface ApplicationDAO {

    ApplicationPO get(long id);

    void create(ApplicationPO application);

    void update(ApplicationPO application);

    void delete(long id);
}
