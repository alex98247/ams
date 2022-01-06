package com.ams.service;

import com.ams.service.warehouse.Good;

import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Mironov
 */
public interface WarehouseService {

    void reserve(Map<Good, Integer> goods);

    void release(Map<Good, Integer> goods);

    Map<Good, Integer> getGoodsCount(Set<Good> goods);
}
