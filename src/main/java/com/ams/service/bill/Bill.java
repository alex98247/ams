package com.ams.service.bill;

import java.util.List;

/**
 * @author northbringer
 */
public class Bill {
    private long Id;
    private String consumerName;
    private long consumerINN;
    private List totalOrderedGoods;

    public Bill(long id, String consumerName, long consumerINN, List<GoodsRow> totalOrderedGoods) {
        this.Id = id;
        this.consumerName = consumerName;
        this.consumerINN = consumerINN;
        this.totalOrderedGoods = totalOrderedGoods;
    }

    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public long getConsumerINN() {
        return consumerINN;
    }

    public void setConsumerINN(long consumerINN) {
        this.consumerINN = consumerINN;
    }

    public List getTotalOrderedGoods() {
        return totalOrderedGoods;
    }

    public void setTotalOrderedGoods(List totalOrderedGoods) {
        this.totalOrderedGoods = totalOrderedGoods;
    }
}
