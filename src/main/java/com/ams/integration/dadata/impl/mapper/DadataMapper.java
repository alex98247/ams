package com.ams.integration.dadata.impl.mapper;

import com.ams.integration.dadata.impl.ro.response.DadataBranchType;
import com.ams.integration.dadata.impl.ro.response.DadataLegalEntityDetails;
import com.ams.integration.dadata.impl.ro.response.DadataLegalEntityType;
import com.ams.integration.dadata.impl.ro.response.DadataManagement;
import com.ams.integration.dto.LegalEntityBranchType;
import com.ams.integration.dto.LegalEntityDetails;
import com.ams.integration.dto.LegalEntityType;
import com.ams.integration.dto.Management;
import com.google.common.base.Enums;

/**
 * Mapper responsible for map dadata objects to DTOs.
 *
 * @author Alexey Mironov
 */
public class DadataMapper {

    public static DadataBranchType toDadataBranchType(LegalEntityBranchType dadataBranchType) {
        if (dadataBranchType == null) {
            return null;
        }
        return Enums.getIfPresent(DadataBranchType.class, dadataBranchType.name()).orNull();
    }

    public static LegalEntityBranchType toBranchType(DadataBranchType branchType) {
        if (branchType == null) {
            return null;
        }
        return Enums.getIfPresent(LegalEntityBranchType.class, branchType.name()).orNull();
    }

    public static LegalEntityType toLegalEntityType(DadataLegalEntityType dadataLegalEntityType) {
        if (dadataLegalEntityType == null) {
            return null;
        }
        return Enums.getIfPresent(LegalEntityType.class, dadataLegalEntityType.name()).orNull();
    }

    public static DadataLegalEntityType toDadataLegalEntityType(LegalEntityType legalEntityType) {
        if (legalEntityType == null) {
            return null;
        }
        return Enums.getIfPresent(DadataLegalEntityType.class, legalEntityType.name()).orNull();
    }

    public static DadataManagement toDadataManagement(Management management) {
        if (management == null) {
            return null;
        }
        DadataManagement result = new DadataManagement();
        result.setName(management.getName());
        result.setPost(management.getPost());
        return result;
    }

    public static Management toManagement(DadataManagement management) {
        if (management == null) {
            return null;
        }
        Management result = new Management();
        result.setName(management.getName());
        result.setPost(management.getPost());
        return result;
    }

    public static LegalEntityDetails toLegalEntityDetails(DadataLegalEntityDetails dadataLegalEntityDetails) {
        if (dadataLegalEntityDetails == null) {
            return null;
        }
        LegalEntityDetails result = new LegalEntityDetails();
        result.setManagement(toManagement(dadataLegalEntityDetails.getManagement()));
        result.setBranchType(toBranchType(dadataLegalEntityDetails.getBranchType()));
        result.setValue(dadataLegalEntityDetails.getValue());
        result.setLegalEntityType(toLegalEntityType(dadataLegalEntityDetails.getEntityType()));
        result.setUnrestrictedValue(dadataLegalEntityDetails.getUnrestrictedValue());
        result.setInn(dadataLegalEntityDetails.getInn());
        result.setKpp(dadataLegalEntityDetails.getKpp());
        result.setOgrn(dadataLegalEntityDetails.getOgrn());
        return result;
    }

}
