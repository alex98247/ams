package com.ams.service.impl;

import com.ams.dao.RightDAO;
import com.ams.service.po.RightPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Responsible for db operations with Right.
 *
 * @author Alexey Mironov
 */
public class RightDAOImpl implements RightDAO {

    private final String getRightsByRoleIds;
    private final JdbcTemplate jdbcTemplate;

    public RightDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("security-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        getRightsByRoleIds = sql.getProperty("getRightsByRoleIds");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Long, List<RightPO>> getRights(Collection<Long> roleIds) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(getRightsByRoleIds, StringUtils.join(roleIds, ","));
        Map<Long, List<RightPO>> result = new HashMap<>();

        while (rs.next()) {
            RightPO po = new RightPO();
            po.setId(rs.getLong(RightPO.FIELD_ID));
            po.setName(rs.getString(RightPO.FIELD_NAME));
            long roleId = rs.getLong(RightPO.FIELD_ROLE_ID);
            List<RightPO> rights = result.getOrDefault(roleId, new ArrayList<>());
            rights.add(po);
            result.put(roleId, rights);
        }

        return result;
    }
}
