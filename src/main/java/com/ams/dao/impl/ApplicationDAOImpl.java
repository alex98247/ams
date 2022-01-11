package com.ams.dao.impl;

import com.ams.dao.ApplicationDAO;
import com.ams.service.application.po.ApplicationPO;
import com.ams.service.rm.ApplicationRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String getApplicationById;
    private final String deleteApplicationById;
    private final String createApplication;
    private final String updateApplication;
    private final String createGoodApplication;
    private final String removeGoodApplication;
    private final String findGoodApplication;
    private final String findAllApplication;

    public ApplicationDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("application-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        getApplicationById = sql.getProperty("getApplicationById");
        deleteApplicationById = sql.getProperty("deleteApplicationById");
        createApplication = sql.getProperty("createApplication");
        updateApplication = sql.getProperty("updateApplication");
        createGoodApplication = sql.getProperty("createGoodApplication");
        removeGoodApplication = sql.getProperty("removeGoodApplication");
        findGoodApplication = sql.getProperty("findGoodApplication");
        findAllApplication = sql.getProperty("findAllApplication");
    }

    @Override
    public ApplicationPO find(long id) {
        try {
            ApplicationPO applicationPO = jdbcTemplate.queryForObject(getApplicationById, ApplicationRowMapper.DEFAULT_ROW_MAPPER, id);
            Map<Long, Integer> goods = new HashMap<>();
            jdbcTemplate.query(findGoodApplication, rs -> {
                if (rs.next()) {
                    goods.put(rs.getLong(ApplicationPO.FIELD_GOOD_ID), rs.getInt(ApplicationPO.FIELD_GOOD_COUNT));
                }
                return null;
            }, id);

            if (applicationPO != null) {
                applicationPO.setGoods(goods);
            }

            return applicationPO;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void create(ApplicationPO application) {
        jdbcTemplate.update(createApplication, application.getId(), application.getCustomerId(), application.getFinished(), application.getManagerUsername(), application.getDelivery(), application.isNeedDelivery());
        for (var good : application.getGoods().entrySet()) {
            jdbcTemplate.update(createGoodApplication, good.getKey(), application.getId(), good.getValue());
        }
    }

    @Override
    public void update(ApplicationPO application) {
        jdbcTemplate.update(updateApplication, application.getCustomerId(), application.getFinished(), application.getDelivery(), application.isNeedDelivery(), application.getManagerUsername(), application.getId());
        jdbcTemplate.update(removeGoodApplication, application.getId());
        for (var good : application.getGoods().entrySet()) {
            jdbcTemplate.update(createGoodApplication, good.getKey(), application.getId(), good.getValue());
        }
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(deleteApplicationById, id);
        jdbcTemplate.update(removeGoodApplication, id);
    }

    @Override
    public List<ApplicationPO> getAll(String username) {
        return jdbcTemplate.query(findAllApplication, ApplicationRowMapper.DEFAULT_ROW_MAPPER);
    }
}
