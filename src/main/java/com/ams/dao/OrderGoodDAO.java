package com.ams.dao;

import com.ams.service.warehouse.po.OrderGoodPO;

import java.util.List;

/**
 * @author Alexey Mironov
 */
public interface OrderGoodDAO {

    List<OrderGoodPO> getByApplicationId(long applicationId);

    void insert(List<OrderGoodPO> pos);

    void remove(long applicationId);
}
