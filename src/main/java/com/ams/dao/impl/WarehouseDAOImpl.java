package com.ams.dao.impl;

import com.ams.dao.WarehouseDAO;
import com.ams.service.rm.GoodRowMapper;
import com.ams.service.warehouse.po.GoodPO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Properties;

/**
 * @author Alexey Mironov
 */
@Repository
public class WarehouseDAOImpl implements WarehouseDAO {

    private final String updateGood;
    private final String findGood;
    private final String insertGood;
    private final String removeGood;
    private final String getAllGood;
    private final JdbcTemplate jdbcTemplate;

    public WarehouseDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("application-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        updateGood = sql.getProperty("updateGood");
        findGood = sql.getProperty("findGood");
        insertGood = sql.getProperty("insertGood");
        removeGood = sql.getProperty("removeGood");
        getAllGood = sql.getProperty("getAllGood");
    }

    @Override
    public void insert(GoodPO good) {
        jdbcTemplate.update(insertGood, good.getName(), good.getCount(), good.getCost());
    }

    @Override
    public GoodPO find(long id) {
        return jdbcTemplate.queryForObject(findGood, GoodRowMapper.DEFAULT_ROW_MAPPER, id);
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.update(removeGood, id);
    }

    @Override
    public void update(GoodPO good) {
        jdbcTemplate.update(updateGood, good.getName(), good.getCount(), good.getCost(), good.getId());
    }

    @Override
    public List<GoodPO> getAll() {
        return jdbcTemplate.query(getAllGood, GoodRowMapper.DEFAULT_ROW_MAPPER);
    }
}
