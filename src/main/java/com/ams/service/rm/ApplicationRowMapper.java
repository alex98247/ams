package com.ams.service.rm;

import com.ams.service.application.po.ApplicationPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationRowMapper implements RowMapper<ApplicationPO> {
    /**
     * The default singleton.
     */
    public static final ApplicationRowMapper DEFAULT_ROW_MAPPER = new ApplicationRowMapper();

    @Override
    public ApplicationPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApplicationPO po = new ApplicationPO();
        po.setId(rs.getLong(ApplicationPO.FIELD_ID));
        po.setFinished(rs.getBoolean(ApplicationPO.FIELD_FINISHED));
        po.setManagerUsername(rs.getString(ApplicationPO.FIELD_MANAGER));
        po.setCustomerId(rs.getLong(ApplicationPO.FIELD_CUSTOMER_ID));
        po.setNeedDelivery(rs.getBoolean(ApplicationPO.FIELD_NEED_DELIVERY));
        po.setDelivery(rs.getString(ApplicationPO.FIELD_DELIVERY));

        return po;

    }
}
