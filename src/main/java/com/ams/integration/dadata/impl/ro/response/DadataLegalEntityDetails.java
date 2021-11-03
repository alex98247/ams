package com.ams.integration.dadata.impl.ro.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadataLegalEntityDetails implements Serializable {

    @JsonProperty("value")
    private String value;

    @JsonProperty("unrestricted_value")
    private String unrestrictedValue;

    @JsonIgnore
    private DadataManagement management;

    @JsonIgnore
    private DadataLegalEntityType entityType;

    @JsonIgnore
    private DadataBranchType branchType;

    @JsonIgnore
    private String inn;

    @JsonIgnore
    private String ogrn;

    @JsonIgnore
    private String kpp;

    @SuppressWarnings("unchecked")
    @JsonProperty("data")
    private void unpackNameFromNestedObject(Map<String, Object> data) {
        this.entityType = DadataLegalEntityType.valueOf((String) data.get("type"));
        this.branchType = DadataBranchType.valueOf((String) data.get("branch_type"));
        this.inn = (String) data.get("inn");
        this.kpp = (String) data.get("kpp");
        this.ogrn = (String) data.get("ogrn");
        Map<String, String> managementMap = (Map<String, String>) data.get("management");
        if (managementMap == null) {
            return;
        }
        DadataManagement management = new DadataManagement();
        management.setName(managementMap.get("name"));
        management.setPost(managementMap.get("post"));
        this.management = management;
    }

    public String getUnrestrictedValue() {
        return unrestrictedValue;
    }

    public void setUnrestrictedValue(String unrestrictedValue) {
        this.unrestrictedValue = unrestrictedValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DadataManagement getManagement() {
        return management;
    }

    public void setManagement(DadataManagement management) {
        this.management = management;
    }

    public DadataLegalEntityType getEntityType() {
        return entityType;
    }

    public DadataBranchType getBranchType() {
        return branchType;
    }

    public void setBranchType(DadataBranchType branchType) {
        this.branchType = branchType;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }
}
