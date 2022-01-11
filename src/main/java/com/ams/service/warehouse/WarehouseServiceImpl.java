package com.ams.service.warehouse;

import com.ams.dao.ReserveGoodDAO;
import com.ams.dao.WarehouseDAO;
import com.ams.model.OrderGood;
import com.ams.service.ApplicationService;
import com.ams.service.WarehouseService;
import com.ams.service.application.Application;
import com.ams.service.warehouse.po.GoodPO;
import com.ams.service.warehouse.po.ReservePO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final ReserveGoodDAO reserveGoodDAO;
    private final WarehouseDAO warehouseDAO;
    private final ApplicationService applicationService;

    public WarehouseServiceImpl(ReserveGoodDAO reserveGoodDAO,
                                WarehouseDAO warehouseDAO,
                                ApplicationService applicationService) {
        this.reserveGoodDAO = reserveGoodDAO;
        this.warehouseDAO = warehouseDAO;
        this.applicationService = applicationService;
    }

    @Override
    public void reserve(Map<Long, Integer> goods) {
        Map<Long, Integer> reservedGoods = reserveGoodDAO.get(goods.keySet()).stream()
                .collect(Collectors.toMap(ReservePO::getGoodId, ReservePO::getCount));
        Map<Long, Integer> toReserve = new HashMap<>();
        for (var good : goods.entrySet()) {
            Integer reservedCount = reservedGoods.getOrDefault(good.getKey(), 0);
            if (reservedCount != 0) {
                reserveGoodDAO.update(new ReservePO(good.getKey(), reservedCount + good.getValue()));
            } else {
                toReserve.put(good.getKey(), reservedCount + good.getValue());
            }
        }
        List<ReservePO> reservePOS = toReserve.entrySet().stream()
                .map(x -> new ReservePO(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
        reserveGoodDAO.insert(reservePOS);
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
        reserveGoodDAO.insert(releasePOS);
    }

    @Override
    public Map<Long, Integer> getGoodsCount(Set<Long> goods) {
        Map<Long, Integer> result = new HashMap<>();
        Map<Long, Integer> existGoods = getAll().entrySet().stream()
                .filter(x -> goods.contains(x.getKey().getId()))
                .collect(Collectors.toMap(x -> x.getKey().getId(), Map.Entry::getValue));
        goods.forEach(id -> result.put(id, existGoods.getOrDefault(id, 0)));
        return result;
    }

    @Override
    public void upsert(Good good, int count) {
        GoodPO goodPO = new GoodPO();
        goodPO.setName(good.getName());
        goodPO.setCost(good.getCost());
        if (good.getId() == null) {
            warehouseDAO.insert(goodPO);
            goodPO.setCount(count);
        } else {
            goodPO.setId(good.getId());
            List<ReservePO> reservePOS = reserveGoodDAO.get(Set.of(good.getId()));
            int reservedCount = 0;
            if (!reservePOS.isEmpty()) {
                reservedCount = reservePOS.get(0).getCount();
            }
            goodPO.setCount(count + reservedCount);
            warehouseDAO.update(goodPO);
        }
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

    @Override
    public Map<Good, Integer> getAll() {
        Map<Good, Integer> result = new HashMap<>();
        List<GoodPO> pos = warehouseDAO.getAll();
        for (var po : pos) {
            Good good = new Good();
            good.setId(po.getId());
            good.setName(po.getName());
            good.setCost(po.getCost());
            result.put(good, po.getCount());
        }

        return calculateGoodCount(result);
    }

    @Override
    public Pair<Good, Integer> getGoodPosition(long id) {
        GoodPO po = warehouseDAO.find(id);
        Good good = new Good();
        good.setId(po.getId());
        good.setName(po.getName());
        good.setCost(po.getCost());

        Map<Good, Integer> goodCount = calculateGoodCount(Map.of(good, po.getCount()));
        Integer count = goodCount.get(good);
        return Pair.of(good, count);
    }

    @Override
    public Map<Good, Integer> getOrderGoods(long applicationId) {
        Application application = applicationService.get(applicationId);
        Map<Long, Integer> warehouseGoods = getGoodsCount(application.getGoods().keySet());
        Map<Long, Integer> diff = WarehouseUtils.getDiff(warehouseGoods, application.getGoods());
        Map<Long, Good> allGoods = getAll().keySet().stream().collect(Collectors.toMap(Good::getId, Function.identity()));

        Map<Good, Integer> result = new HashMap<>();
        diff.forEach((k, v) -> result.put(allGoods.get(k), v));

        return result;
    }

    @Override
    public void insertOrderGood(List<OrderGood> orderGoods) {
        /*List<OrderGoodPO> pos = orderGoods.stream().map(x -> {
            OrderGoodPO po = new OrderGoodPO();
            po.setCount(x.getCount());
            po.setApplicationId(x.getApplicationId());
            po.setGoodId(x.getGoodId());

            return po;
        }).collect(Collectors.toList());

        orderGoodDAO.insert(pos);*/
    }

    @Override
    public void removeOrderGoods(long applicationId) {
        /*orderGoodDAO.remove(applicationId);*/
    }

    private Map<Good, Integer> calculateGoodCount(Map<Good, Integer> goods) {
        Set<Long> goodIds = goods.keySet().stream().map(Good::getId).collect(Collectors.toSet());
        Map<Long, Integer> reservedGoods = reserveGoodDAO.get(goodIds).stream()
                .collect(Collectors.toMap(ReservePO::getGoodId, ReservePO::getCount));
        Map<Good, Integer> result = new HashMap<>();
        goods.forEach((k, v) -> {
            if (reservedGoods.containsKey(k.getId())) {
                result.put(k, v - reservedGoods.get(k.getId()));
            } else {
                result.put(k, v);
            }
        });

        return result;
    }

}
