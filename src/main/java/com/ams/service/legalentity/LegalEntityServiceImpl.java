package com.ams.service.legalentity;

import com.ams.dao.LegalEntityDAO;
import com.ams.service.LegalEntityService;
import com.ams.service.legalentity.converter.LegalEntityConverter;
import com.ams.service.po.LegalEntityPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LegalEntityServiceImpl implements LegalEntityService {

    private final LegalEntityDAO legalEntityDAO;

    public LegalEntityServiceImpl(LegalEntityDAO legalEntityDAO) {
        this.legalEntityDAO = legalEntityDAO;
    }

    @Override
    public List<LegalEntity> getLegalEntities(String name) {
        if (StringUtils.isEmpty(name)) {
            return Collections.emptyList();
        }

        List<LegalEntityPO> legalEntities = legalEntityDAO.getByName(name);
        return LegalEntityConverter.convertEmployeePoToDto(legalEntities);
    }

    @Override
    public void upsert(LegalEntity legalEntity) {
        if (legalEntity.getId() == null) {
            legalEntityDAO.create(LegalEntityConverter.convertEmployeeDtoToPo(legalEntity));
        }
    }

    @Override
    public LegalEntity getLegalEntity(long id) {
        LegalEntityPO po = legalEntityDAO.get(id);
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setOgrn(po.getOgrn());
        legalEntity.setOgrn(po.getOgrn());
        legalEntity.setKpp(po.getKpp());
        legalEntity.setId(po.getId());
        legalEntity.setName(po.getName());

        return legalEntity;
    }

    @Override
    public LegalEntity getLegalEntityByInn(String inn) {
        LegalEntityPO po = legalEntityDAO.getByInn(inn);
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setOgrn(po.getOgrn());
        legalEntity.setOgrn(po.getOgrn());
        legalEntity.setKpp(po.getKpp());
        legalEntity.setId(po.getId());
        legalEntity.setName(po.getName());

        return legalEntity;
    }
}
