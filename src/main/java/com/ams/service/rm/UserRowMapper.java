package com.ams.service.rm;

import com.ams.service.po.UserPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserPO> {
    /**
     * The default singleton.
     */
    public static final UserRowMapper DEFAULT_ROW_MAPPER = new UserRowMapper();

    @Override
    public UserPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPO po = new UserPO();
        po.setId(rs.getInt(UserPO.FIELD_ID));

        return po;

    }
}
