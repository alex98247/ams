package com.ams.service.rm;

import com.ams.service.po.RolePO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<RolePO> {
    /**
     * The default singleton.
     */
    public static final RoleRowMapper DEFAULT_ROW_MAPPER = new RoleRowMapper();

    @Override
    public RolePO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RolePO po = new RolePO();
        po.setId(rs.getInt(RolePO.FIELD_ID));
        po.setName(rs.getString(RolePO.FIELD_NAME));

        return po;

    }
}