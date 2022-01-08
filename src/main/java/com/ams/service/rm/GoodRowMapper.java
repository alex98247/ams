package com.ams.service.rm;

import com.ams.service.warehouse.po.GoodPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexey Mironov
 */
public class GoodRowMapper implements RowMapper<GoodPO> {
    /**
     * The default singleton.
     */
    public static final GoodRowMapper DEFAULT_ROW_MAPPER = new GoodRowMapper();

    @Override
    public GoodPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        GoodPO po = new GoodPO();
        po.setId(rs.getLong(GoodPO.FIELD_ID));
        po.setName(rs.getString(GoodPO.FIELD_NAME));
        po.setCount(rs.getInt(GoodPO.COUNT));

        return po;

    }
}
