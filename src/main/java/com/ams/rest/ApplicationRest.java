package com.ams.rest;

import com.ams.rest.request.ApplicationRequest;
import com.ams.rest.response.ApplicationResponse;
import com.ams.service.ApplicationService;
import com.ams.service.LegalEntityService;
import com.ams.service.application.Application;
import com.ams.service.legalentity.LegalEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/application")
public class ApplicationRest {

    private final ApplicationService applicationService;
    private final LegalEntityService legalEntityService;

    public ApplicationRest(ApplicationService applicationService,
                           LegalEntityService legalEntityService) {
        this.applicationService = applicationService;
        this.legalEntityService = legalEntityService;
    }

    @PostMapping
    public ResponseEntity<Void> upsert(@RequestBody ApplicationRequest request) {
        applicationService.upsert(Application.of(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationResponse>> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ApplicationResponse> applications = applicationService.getAll(username).stream()
                .filter(x->!x.getFinished())
                .map(this::fill)
                .collect(Collectors.toList());
        return ResponseEntity.ok(applications);
    }

    @GetMapping
    public ResponseEntity<Application> get(@QueryParam("id") Long id) {
        Application application = applicationService.get(id);
        return ResponseEntity.ok(application);
    }

    private ApplicationResponse fill(Application application) {
        ApplicationResponse result = new ApplicationResponse();
        result.setCustomerId(application.getCustomerId());
        result.setFinished(application.getFinished());
        result.setId(application.getId());
        result.setNeedDelivery(application.getNeedDelivery());
        result.setGoods(application.getGoods());
        result.setManagerUsername(application.getManagerUsername());
        result.setDelivery(application.getDelivery());

        LegalEntity legalEntity = legalEntityService.getLegalEntity(application.getCustomerId());
        result.setCustomerName(legalEntity.getName());

        return result;
    }
}
