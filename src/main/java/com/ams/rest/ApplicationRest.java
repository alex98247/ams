package com.ams.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationRest {

/*    private final ApplicationService applicationService;

    public ApplicationRest(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getApplications() {
        List<Application> employees = applicationService.getUserApplications();
        return ResponseEntity.ok().body(employees);
    }*/

}
