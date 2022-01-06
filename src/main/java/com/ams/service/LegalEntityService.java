package com.ams.service;

import com.ams.service.legalentity.LegalEntity;

import java.util.List;

public interface LegalEntityService {

    List<LegalEntity> getLegalEntities(String name);

    void upsert(LegalEntity legalEntity);
}
