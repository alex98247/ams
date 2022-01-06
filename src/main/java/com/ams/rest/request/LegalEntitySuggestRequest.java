package com.ams.rest.request;

/**
 * @author Alexey Mironov
 */
public class LegalEntitySuggestRequest {
    /**
     * Inn, kpp or query for search.
     */
    private String query;
    /**
     * The legal entity type.
     */
    private String legalEntityType = "LEGAL";
    /**
     * The branch type (main or branch).
     */
    private String branchType = "MAIN";
    /**
     * The kpp.
     */
    private String kpp;
    /**
     * Max count to return.
     */
    private int count = 10;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(String legalEntityType) {
        this.legalEntityType = legalEntityType;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }
}
