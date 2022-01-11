package com.ams.rest;

import com.ams.rest.request.OrderGoodsRequest;
import com.ams.rest.response.OrderGoodResponse;
import com.ams.service.WarehouseService;
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
@RequestMapping("/order")
public class OrderGoodRest {

    private final WarehouseService warehouseService;

    public OrderGoodRest(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<OrderGoodResponse>> getOrderGoods(@QueryParam("applicationId") Long applicationId) {
        List<OrderGoodResponse> result = warehouseService.getOrderGoods(applicationId).entrySet().stream().map(x -> {
            OrderGoodResponse response = new OrderGoodResponse();
            response.setName(x.getKey().getName());
            response.setCount(x.getValue());
            return response;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/remove")
    public ResponseEntity<Void> removeOrderGoods(@QueryParam("applicationId") Long applicationId) {
        warehouseService.removeOrderGoods(applicationId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> insertOrderGoods(@RequestBody OrderGoodsRequest request) {
        warehouseService.insertOrderGood(request.getOrderGoods());
        return ResponseEntity.ok().build();
    }
}
