package com.ams.rest;

import com.ams.rest.request.LegalEntitySaveUpdateRequest;
import com.ams.service.LegalEntityService;
import com.ams.service.legalentity.LegalEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/legalentity")
public class LegalEntityRest {

    private final LegalEntityService legalEntityService;

    public LegalEntityRest(LegalEntityService legalEntityService) {
        this.legalEntityService = legalEntityService;
    }

    @GetMapping
    public ResponseEntity<List<LegalEntity>> getLegalEntity(@QueryParam("name") String name) {
        List<LegalEntity> result = legalEntityService.getLegalEntities(name);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Void> saveLegalEntity(@RequestBody LegalEntitySaveUpdateRequest request) {
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setOgrn(request.getOgrn());
        legalEntity.setKpp(request.getKpp());
        legalEntity.setId(request.getId());
        legalEntity.setName(request.getName());
        legalEntity.setInn(request.getInn());
        legalEntityService.upsert(legalEntity);
        return ResponseEntity.ok().build();
    }
}
