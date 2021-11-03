package com.ams.integration.dadata.impl.ro.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class DadataLegalEntitySuggestResponse implements Serializable {

    @JsonProperty("suggestions")
    private List<DadataLegalEntityDetails> legalEntityInfo;

    public List<DadataLegalEntityDetails> getLegalEntityInfo() {
        return legalEntityInfo;
    }

    public void setLegalEntityInfo(List<DadataLegalEntityDetails> legalEntityInfo) {
        this.legalEntityInfo = legalEntityInfo;
    }
}
