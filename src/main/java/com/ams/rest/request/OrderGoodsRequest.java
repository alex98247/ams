package com.ams.rest.request;

import com.ams.model.OrderGood;

import java.util.List;

/**
 * @author Alexey Mironov
 */
public class OrderGoodsRequest {

    private List<OrderGood> orderGoods;

    public List<OrderGood> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGood> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
