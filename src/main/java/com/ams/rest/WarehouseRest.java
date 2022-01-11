package com.ams.rest;

import com.ams.rest.request.GoodRequest;
import com.ams.rest.response.GoodPositionRO;
import com.ams.rest.response.OrderGoodResponse;
import com.ams.service.WarehouseService;
import com.ams.service.warehouse.Good;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseRest {

    private final WarehouseService warehouseService;

    public WarehouseRest(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<GoodPositionRO>> getGoodsPositions() {
        List<GoodPositionRO> result = warehouseService.getAll().entrySet().stream()
                .map(x -> GoodPositionRO.of(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/id")
    public ResponseEntity<GoodPositionRO> getGoodPositions(@QueryParam("id") Long id) {
        Pair<Good, Integer> goodPosition = warehouseService.getGoodPosition(id);
        GoodPositionRO result = GoodPositionRO.of(goodPosition.getKey(), goodPosition.getValue());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> upsert(@RequestBody GoodRequest request) {
        Good good = new Good();
        good.setId(request.getId());
        good.setName(request.getName());
        warehouseService.upsert(good, request.getCount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrderGoodResponse>> get(@QueryParam("applicationId") Long applicationId) {
        List<OrderGoodResponse> result = warehouseService.getOrderGoods(applicationId).entrySet().stream().map(x -> {
            OrderGoodResponse response = new OrderGoodResponse();
            response.setName(x.getKey().getName());
            response.setCount(x.getValue());
            return response;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
