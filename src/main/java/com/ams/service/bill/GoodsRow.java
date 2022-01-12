package com.ams.service.bill;

/**
 * @author northbringer
 */
public class GoodsRow {
    private long goods_id;
    private String goods_name;
    private int goods_count;
    private int goods_cost;
    private int goods_totalcost;

    public GoodsRow(long goods_id, String goods_name, int goods_count, int goods_cost) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_count = goods_count;
        this.goods_cost = goods_cost;
        this.goods_totalcost = this.goods_count * this.goods_cost;
    }

    public long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(long goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public int getGoods_cost() {
        return goods_cost;
    }

    public void setGoods_cost(int goods_cost) {
        this.goods_cost = goods_cost;
    }

    public int getGoods_totalcost() {
        return goods_totalcost;
    }

    public void setGoods_totalcost(int goodsTotalCost) {
        this.goods_totalcost = goodsTotalCost;
    }
}
