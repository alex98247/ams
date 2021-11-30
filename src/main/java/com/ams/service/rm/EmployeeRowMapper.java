package com.ams.service.rm;

import com.ams.service.po.EmployeePO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<EmployeePO> {
    /**
     * The default singleton.
     */
    public static final EmployeeRowMapper DEFAULT_ROW_MAPPER = new EmployeeRowMapper();

    @Override
    public EmployeePO mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeePO po = new EmployeePO();
        po.setId(rs.getLong(EmployeePO.FIELD_ID));
        po.setSurname(rs.getString(EmployeePO.FIELD_SURNAME));
        po.setPatronymic(rs.getString(EmployeePO.FIELD_PATRONYMIC));
        po.setPhone(rs.getString(EmployeePO.FIELD_PHONE));
        po.setPosition(rs.getString(EmployeePO.FIELD_POSITION));
        po.setName(rs.getString(EmployeePO.FIELD_NAME));

        return po;
    }
}
