package com.ams.service.rm;

import com.ams.service.warehouse.po.OrderGoodPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexey Mironov
 */
public class OrderGoodRowMapper implements RowMapper<OrderGoodPO> {
    /**
     * The default singleton.
     */
    public static final OrderGoodRowMapper DEFAULT_ROW_MAPPER = new OrderGoodRowMapper();

    @Override
    public OrderGoodPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderGoodPO po = new OrderGoodPO();
        po.setId(rs.getLong(OrderGoodPO.FIELD_ID));
        po.setApplicationId(rs.getLong(OrderGoodPO.FIELD_APPLICATION_ID));
        po.setGoodId(rs.getLong(OrderGoodPO.FIELD_GOOD_ID));
        po.setCount(rs.getInt(OrderGoodPO.COUNT));

        return po;

    }
}
