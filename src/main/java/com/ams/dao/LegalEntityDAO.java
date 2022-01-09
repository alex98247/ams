package com.ams.dao;

import com.ams.service.po.LegalEntityPO;

import java.util.List;

public interface LegalEntityDAO {
    /**
     * Create application.
     *
     * @param legalEntity - employee to save
     */
    void create(LegalEntityPO legalEntity);

    LegalEntityPO get(long id);

    List<LegalEntityPO> getByName(String name);

    void delete(long id);

    LegalEntityPO getByInn(String inn);
}
