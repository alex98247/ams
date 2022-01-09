package com.ams.dao.impl;

import com.ams.dao.LegalEntityDAO;
import com.ams.service.po.LegalEntityPO;
import com.ams.service.rm.LegalEntityRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Properties;

@Repository
public class LegalEntityDAOImpl implements LegalEntityDAO {

    private final String getLegalEntityById;
    private final String deleteLegalEntityById;
    private final String createLegalEntity;
    private final String getLegalEntityByName;
    private final String getLegalEntityByInn;
    private final JdbcTemplate jdbcTemplate;

    public LegalEntityDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("application-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        getLegalEntityById = sql.getProperty("getLegalEntityById");
        deleteLegalEntityById = sql.getProperty("deleteLegalEntityById");
        createLegalEntity = sql.getProperty("createLegalEntity");
        getLegalEntityByName = sql.getProperty("getLegalEntityByName");
        getLegalEntityByInn = sql.getProperty("getLegalEntityByInn");
    }

    @Override
    public void create(LegalEntityPO legalEntity) {
        jdbcTemplate.update(createLegalEntity, legalEntity.getName(), legalEntity.getInn(), legalEntity.getOgrn(), legalEntity.getKpp());
    }

    @Override
    public LegalEntityPO get(long id) {
        return jdbcTemplate.queryForObject(getLegalEntityById, LegalEntityRowMapper.DEFAULT_ROW_MAPPER, id);
    }

    @Override
    public List<LegalEntityPO> getByName(String name) {
        String replacedName = name.replace("%", "\\%");
        return jdbcTemplate.query(getLegalEntityByName, LegalEntityRowMapper.DEFAULT_ROW_MAPPER, replacedName + "%");
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(deleteLegalEntityById, id);
    }

    @Override
    public LegalEntityPO getByInn(String inn) {
        return jdbcTemplate.queryForObject(getLegalEntityByInn, LegalEntityRowMapper.DEFAULT_ROW_MAPPER, inn);
    }
}
