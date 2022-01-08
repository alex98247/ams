package com.ams.service.warehouse;

import com.ams.dao.ReserveGoodDAO;
import com.ams.dao.WarehouseDAO;
import com.ams.service.WarehouseService;
import com.ams.service.warehouse.po.GoodPO;
import com.ams.service.warehouse.po.ReservePO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final ReserveGoodDAO reserveGoodDAO;
    private final WarehouseDAO warehouseDAO;

    public WarehouseServiceImpl(ReserveGoodDAO reserveGoodDAO, WarehouseDAO warehouseDAO) {
        this.reserveGoodDAO = reserveGoodDAO;
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public void reserve(Map<Long, Integer> goods) {
        Map<Long, Integer> reservedGoods = reserveGoodDAO.get(goods.keySet()).stream()
                .collect(Collectors.toMap(ReservePO::getGoodId, ReservePO::getCount));
        Map<Long, Integer> toReserve = new HashMap<>();
        for (var good : goods.entrySet()) {
            Integer reservedCount = reservedGoods.getOrDefault(good.getKey(), 0);
            toReserve.put(good.getKey(), reservedCount + good.getValue());
        }
        List<ReservePO> reservePOS = toReserve.entrySet().stream()
                .map(x -> new ReservePO(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
        reserveGoodDAO.reserve(reservePOS);
    }

    @Override
    public void release(Map<Long, Integer> goods) {
        Map<Long, Integer> reservedGoods = reserveGoodDAO.get(goods.keySet()).stream()
                .collect(Collectors.toMap(ReservePO::getGoodId, ReservePO::getCount));
        Map<Long, Integer> toRelease = new HashMap<>();
        for (var good : goods.entrySet()) {
            Integer reservedCount = reservedGoods.get(good.getKey());
            if (reservedCount != null) {
                toRelease.put(good.getKey(), reservedCount - good.getValue());
            }
        }
        List<ReservePO> releasePOS = toRelease.entrySet().stream()
                .map(x -> new ReservePO(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
        reserveGoodDAO.reserve(releasePOS);
    }

    @Override
    public Map<Long, Integer> getGoodsCount(Set<Long> goods) {
        return null;
    }

    @Override
    public void addNewGood(Good good, int count) {
        GoodPO goodPO = new GoodPO();
        goodPO.setName(good.getName());
        goodPO.setCount(count);
        warehouseDAO.insert(goodPO);
    }

    @Override
    public void addGood(long goodId, int count) {
        GoodPO goodPO = warehouseDAO.find(goodId);
        if (goodPO == null) {
            return;
        }

        goodPO.setCount(count + goodPO.getCount());
        warehouseDAO.insert(goodPO);
    }
}
