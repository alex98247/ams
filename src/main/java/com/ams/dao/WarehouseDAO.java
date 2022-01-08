package com.ams.dao;

import com.ams.service.warehouse.po.GoodPO;

/**
 * @author Alexey Mironov
 */
public interface WarehouseDAO {

    void insert(GoodPO good);

    GoodPO find(long id);

    void remove(long id);

    void update(GoodPO good);
}
