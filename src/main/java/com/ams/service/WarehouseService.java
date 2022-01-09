package com.ams.service;

import com.ams.service.warehouse.Good;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Mironov
 */
public interface WarehouseService {

    void reserve(Map<Long, Integer> goods);

    void release(Map<Long, Integer> goods);

    Map<Long, Integer> getGoodsCount(Set<Long> goods);

    void upsert(Good good, int count);

    void addGood(long goodId, int count);

    Map<Good, Integer> getAll();

    Pair<Good, Integer> getGoodPosition(long id);
}
