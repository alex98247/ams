package com.ams.dao;

import com.ams.service.warehouse.po.ReservePO;

import java.util.List;
import java.util.Set;

/**
 * @author Alexey Mironov
 */
public interface ReserveGoodDAO {

    void reserve(List<ReservePO> reservePOs);

    void release(List<ReservePO> reservePOs);

    List<ReservePO> get(Set<Long> ids);
}
