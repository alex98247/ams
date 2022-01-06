package com.ams.rest;

import com.ams.integration.LegalEntityContext;
import com.ams.integration.LegalEntityIntegrationService;
import com.ams.integration.dto.LegalEntityBranchType;
import com.ams.integration.dto.LegalEntityDetails;
import com.ams.integration.dto.LegalEntityType;
import com.ams.rest.request.LegalEntitySuggestRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/legalentity/info")
public class LegalEntitySuggestRest {

    private final LegalEntityIntegrationService legalEntitySuggestService;

    public LegalEntitySuggestRest(LegalEntityIntegrationService legalEntitySuggestService) {
        this.legalEntitySuggestService = legalEntitySuggestService;
    }

    @PostMapping
    public ResponseEntity<List<LegalEntityDetails>> getLegalEntity(@RequestBody LegalEntitySuggestRequest request) {
        LegalEntityContext ctx = LegalEntityContext.builder()
                .branchType(LegalEntityBranchType.valueOf(request.getBranchType()))
                .count(request.getCount())
                .entityType(LegalEntityType.valueOf(request.getLegalEntityType()))
                .kpp(request.getKpp())
                .query(request.getQuery())
                .build();
        List<LegalEntityDetails> result = legalEntitySuggestService.suggestLegalEntity(ctx);
        return ResponseEntity.ok().body(result);
    }
}
