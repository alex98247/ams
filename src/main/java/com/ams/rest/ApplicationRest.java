package com.ams.rest;

import com.ams.rest.request.ApplicationRequest;
import com.ams.service.ApplicationService;
import com.ams.service.WorkflowService;
import com.ams.service.application.Application;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/application")
public class ApplicationRest {

    private final ApplicationService applicationService;
    private final WorkflowService workflowService;

    public ApplicationRest(ApplicationService applicationService,
                           WorkflowService workflowService) {
        this.applicationService = applicationService;
        this.workflowService = workflowService;
    }

    @PostMapping
    public ResponseEntity<Void> upsert(@RequestBody ApplicationRequest request) {
        applicationService.upsert(Application.of(request));
        return ResponseEntity.ok().build();
    }
/*
    @GetMapping
    public ResponseEntity<Application> get(@QueryParam("taskId") String taskId) {
        Task task = workflowService.getTask(taskId);
        Application application = applicationService.get();
        return ResponseEntity.ok().build();
    }*/
}
