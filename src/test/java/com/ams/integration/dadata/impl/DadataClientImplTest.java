package com.ams.integration.dadata.impl;

import com.ams.integration.LegalEntityContext;
import com.ams.integration.LegalEntityIntegrationService;
import com.ams.integration.dto.LegalEntityDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.List;

class DadataClientImplTest {

    @Test
    void getLegalEntity() {
        LegalEntityIntegrationService dadataClient = new DadataLegalEntityIntegrationService(new RestTemplateBuilder());
        List<LegalEntityDetails> details = dadataClient.suggestLegalEntity(LegalEntityContext.builder()
                .query("Эксперт")
                .build());
        System.out.println("");
    }
}