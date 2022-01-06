package com.ams.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LegalEntitySaveUpdateRequest {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("value")
    private String name;
    @JsonProperty("inn")
    private String inn;
    @JsonProperty("ogrn")
    private String ogrn;
    @JsonProperty("kpp")
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
}
