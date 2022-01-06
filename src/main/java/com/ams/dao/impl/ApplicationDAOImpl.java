package com.ams.dao.impl;

import com.ams.dao.ApplicationDAO;
import com.ams.service.application.po.ApplicationPO;
import com.ams.service.rm.ApplicationRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Properties;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String getApplicationById;
    private final String deleteApplicationById;
    private final String createApplication;
    private final String updateApplication;

    public ApplicationDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("application-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        getApplicationById = sql.getProperty("getApplicationById");
        deleteApplicationById = sql.getProperty("deleteApplicationById");
        createApplication = sql.getProperty("createApplication");
        updateApplication = sql.getProperty("updateApplication");
    }

    @Override
    public ApplicationPO get(long id) {
        return jdbcTemplate.queryForObject(getApplicationById, ApplicationRowMapper.DEFAULT_ROW_MAPPER, id);
    }

    @Override
    public void create(ApplicationPO application) {
        jdbcTemplate.update(createApplication, application.getCustomerId(), application.getFinished(), application.getManagerUsername());
    }

    @Override
    public void update(ApplicationPO application) {
        jdbcTemplate.update(updateApplication, application.getCustomerId(), application.getFinished(), application.getManagerUsername(), application.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(deleteApplicationById, id);
    }
}
