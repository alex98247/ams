package com.ams.dao.impl;

import com.ams.dao.ReserveGoodDAO;
import com.ams.service.rm.ReserveGoodRowMapper;
import com.ams.service.warehouse.po.ReservePO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
@Repository
public class ReserveGoodDAOImpl implements ReserveGoodDAO {

    private final String insertReserve;
    private final String updateReserve;
    private final String releaseGood;
    private final String getReservedGood;
    private final JdbcTemplate jdbcTemplate;

    public ReserveGoodDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("application-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        insertReserve = sql.getProperty("insertReserve");
        updateReserve = sql.getProperty("updateReserve");
        releaseGood = sql.getProperty("releaseGood");
        getReservedGood = sql.getProperty("getReservedGood");
    }

    @Override
    public void insert(List<ReservePO> reservePOs) {
        jdbcTemplate.batchUpdate(insertReserve, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ReservePO good = reservePOs.get(i);
                ps.setLong(1, good.getGoodId());
                ps.setInt(2, good.getCount());
            }

            @Override
            public int getBatchSize() {
                return reservePOs.size();
            }
        });
    }

    @Override
    public void update(ReservePO reservePO) {
        jdbcTemplate.update(updateReserve, reservePO.getCount(), reservePO.getGoodId());
    }

    @Override
    public void release(List<ReservePO> reservePOs) {
        String ids = reservePOs.stream().map(x -> x.getGoodId().toString()).collect(Collectors.joining(", "));
        String statement = String.format(releaseGood, ids);
        jdbcTemplate.update(statement);
        insert(reservePOs);
    }

    @Override
    public List<ReservePO> get(Set<Long> ids) {
        try {
            String idStr = ids.stream().map(Object::toString).collect(Collectors.joining(", "));
            String statement = String.format(getReservedGood, idStr);
            return jdbcTemplate.query(statement, ReserveGoodRowMapper.DEFAULT_ROW_MAPPER);
        } catch (Exception ex) {
            return Collections.emptyList();
        }

    }
}
