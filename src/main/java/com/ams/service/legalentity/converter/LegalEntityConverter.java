package com.ams.service.legalentity.converter;

import com.ams.service.legalentity.LegalEntity;
import com.ams.service.po.LegalEntityPO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegalEntityConverter {

    public static List<LegalEntity> convertEmployeePoToDto(List<LegalEntityPO> source) {

        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }

        List<LegalEntity> target = new ArrayList<>();
        for (var employeePo : source) {
            LegalEntity legalEntity = new LegalEntity();
            legalEntity.setId(employeePo.getId());
            legalEntity.setName(employeePo.getName());
            legalEntity.setInn(employeePo.getInn());
            legalEntity.setKpp(employeePo.getKpp());
            legalEntity.setOgrn(employeePo.getOgrn());
            target.add(legalEntity);
        }
        return target;
    }

    public static LegalEntityPO convertEmployeeDtoToPo(LegalEntity source) {

        if (source == null) {
            return null;
        }

        LegalEntityPO target = new LegalEntityPO();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setInn(source.getInn());
        target.setKpp(source.getKpp());
        target.setOgrn(source.getOgrn());

        return target;
    }
}
