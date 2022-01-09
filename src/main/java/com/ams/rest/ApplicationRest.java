package com.ams.rest;

import com.ams.rest.request.ApplicationRequest;
import com.ams.service.ApplicationService;
import com.ams.service.application.Application;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationRest {

    private final ApplicationService applicationService;

    public ApplicationRest(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Void> upsert(@RequestBody ApplicationRequest request) {
        applicationService.upsert(Application.of(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Application>> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Application> applications = applicationService.getAll(username);
        return ResponseEntity.ok(applications);
    }

    @GetMapping
    public ResponseEntity<Application> get(@QueryParam("id") Long id) {
        Application application = applicationService.get(id);
        return ResponseEntity.ok(application);
    }
}
