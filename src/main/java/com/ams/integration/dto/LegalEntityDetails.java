package com.ams.integration.dto;

/**
 * Contains information about legal entity.
 *
 * @author Alexey Mironov
 */
public class LegalEntityDetails {
    /**
     * Name of legal entity.
     */
    private String value;
    /**
     * Unrestricted Name of legal entity.
     */
    private String unrestrictedValue;
    /**
     * The branch type.
     */
    private LegalEntityBranchType branchType;
    /**
     * The legal entity type.
     */
    private LegalEntityType legalEntityType;
    /**
     * The management of legal entity.
     */
    private Management management;
    /**
     * The inn.
     */
    private String inn;
    /**
     * The ogrn.
     */
    private String ogrn;
    /**
     * The kpp.
     */
    private String kpp;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnrestrictedValue() {
        return unrestrictedValue;
    }

    public void setUnrestrictedValue(String unrestrictedValue) {
        this.unrestrictedValue = unrestrictedValue;
    }

    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }

    public LegalEntityBranchType getBranchType() {
        return branchType;
    }

    public void setBranchType(LegalEntityBranchType branchType) {
        this.branchType = branchType;
    }

    public LegalEntityType getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(LegalEntityType legalEntityType) {
        this.legalEntityType = legalEntityType;
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
