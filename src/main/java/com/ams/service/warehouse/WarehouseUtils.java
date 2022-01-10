package com.ams.service.warehouse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Mironov
 */
public class WarehouseUtils {

    private WarehouseUtils() {
    }

    public static Map<Long, Integer> getDiff(Map<Long, Integer> warehouseGoods, Map<Long, Integer> applicationGoods) {
        Map<Long, Integer> result = new HashMap<>();
        for (var good : applicationGoods.entrySet()) {
            Integer count = warehouseGoods.get(good.getKey());
            if (count == null) {
                result.put(good.getKey(), good.getValue());
            } else if (count < good.getValue()) {
                result.put(good.getKey(), good.getValue() - count);
            }
        }
        return result;
    }

    public static boolean needOrder(Map<Long, Integer> warehouseGoods, Map<Long, Integer> applicationGoods) {
        return !getDiff(warehouseGoods, applicationGoods).isEmpty();
    }
}