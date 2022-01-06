package com.ams.service.warehouse;

import com.ams.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Mironov
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Override
    public void reserve(Map<Good, Integer> goods) {

    }

    @Override
    public void release(Map<Good, Integer> goods) {

    }

    @Override
    public Map<Good, Integer> getGoodsCount(Set<Good> goods) {
        return null;
    }
}
