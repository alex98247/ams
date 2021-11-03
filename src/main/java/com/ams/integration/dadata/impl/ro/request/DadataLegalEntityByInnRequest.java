package com.ams.integration.dadata.impl.ro.request;


import com.ams.integration.dadata.impl.ro.response.DadataBranchType;
import com.ams.integration.dadata.impl.ro.response.DadataLegalEntityType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DadataLegalEntityByInnRequest implements Serializable {

    @JsonProperty("query")
    private String query;

    @JsonProperty("count")
    private int count;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("kpp")
    private String kpp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("branch_type")
    private DadataBranchType branchType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("type")
    private DadataLegalEntityType type;

    public void setQuery(String query) {
        this.query = query;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public DadataBranchType getBranchType() {
        return branchType;
    }

    public void setBranchType(DadataBranchType branchType) {
        this.branchType = branchType;
    }

    public DadataLegalEntityType getType() {
        return type;
    }

    public void setType(DadataLegalEntityType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public String getQuery() {
        return query;
    }
}
