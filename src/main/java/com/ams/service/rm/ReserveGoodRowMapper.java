package com.ams.service.rm;

import com.ams.service.warehouse.po.ReservePO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexey Mironov
 */
public class ReserveGoodRowMapper implements RowMapper<ReservePO> {
    /**
     * The default singleton.
     */
    public static final ReserveGoodRowMapper DEFAULT_ROW_MAPPER = new ReserveGoodRowMapper();

    @Override
    public ReservePO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReservePO po = new ReservePO();
        po.setGoodId(rs.getLong(ReservePO.FIELD_GOOD_ID));
        po.setCount(rs.getInt(ReservePO.COUNT));

        return po;
    }
}
