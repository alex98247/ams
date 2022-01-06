package com.ams.service.legalentity;

import com.ams.integration.dto.LegalEntityDetails;

public class LegalEntity {
    private Long id;
    private String name;
    private String inn;
    private String ogrn;
    private String kpp;

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static LegalEntity of(LegalEntityDetails legalEntity) {
        LegalEntity result = new LegalEntity();
        result.setInn(legalEntity.getInn());
        result.setName(legalEntity.getValue());
        result.setKpp(legalEntity.getKpp());
        result.setOgrn(legalEntity.getOgrn());
        return result;
    }
}
