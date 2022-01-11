package com.ams.dao.impl;

import com.ams.dao.OrderGoodDAO;
import com.ams.service.rm.OrderGoodRowMapper;
import com.ams.service.warehouse.po.OrderGoodPO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexey Mironov
 */
@Repository
public class OrderGoodDAOImpl implements OrderGoodDAO {

    private final String insertOrderGood;
    private final String getOrderGood;
    private final String removeOrderGood;
    private final JdbcTemplate jdbcTemplate;

    public OrderGoodDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("application-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        insertOrderGood = sql.getProperty("insertOrderGood");
        getOrderGood = sql.getProperty("getOrderGood");
        removeOrderGood = sql.getProperty("removeOrderGood");
    }

    @Override
    public List<OrderGoodPO> getByApplicationId(long applicationId) {
        return jdbcTemplate.query(getOrderGood, OrderGoodRowMapper.DEFAULT_ROW_MAPPER, applicationId);
    }

    @Override
    public void insert(List<OrderGoodPO> pos) {
        jdbcTemplate.batchUpdate(insertOrderGood, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                OrderGoodPO po = pos.get(i);
                ps.setLong(1, po.getApplicationId());
                ps.setLong(2, po.getGoodId());
                ps.setInt(3, po.getCount());
            }

            @Override
            public int getBatchSize() {
                return pos.size();
            }
        });
    }

    @Override
    public void remove(long applicationId) {
        jdbcTemplate.update(removeOrderGood, applicationId);
    }
}
