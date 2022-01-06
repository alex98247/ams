package com.ams.service.rm;

import com.ams.service.po.LegalEntityPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalEntityRowMapper implements RowMapper<LegalEntityPO> {
    /**
     * The default singleton.
     */
    public static final LegalEntityRowMapper DEFAULT_ROW_MAPPER = new LegalEntityRowMapper();

    @Override
    public LegalEntityPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        LegalEntityPO po = new LegalEntityPO();
        po.setId(rs.getLong(LegalEntityPO.FIELD_ID));
        po.setName(rs.getString(LegalEntityPO.FIELD_NAME));
        po.setInn(rs.getString(LegalEntityPO.FIELD_INN));
        po.setKpp(rs.getString(LegalEntityPO.FIELD_KPP));
        po.setOgrn(rs.getString(LegalEntityPO.FIELD_OGRN));

        return po;

    }
}
