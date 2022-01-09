package com.ams.rest;

import com.ams.rest.request.TaskCompleteRequest;
import com.ams.rest.response.ProcessRO;
import com.ams.rest.response.TaskRO;
import com.ams.service.WorkflowService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
@RestController
@RequestMapping("/workflow")
public class WorkflowRest {

    private final WorkflowService workflowService;

    public WorkflowRest(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping
    public ResponseEntity<List<TaskRO>> getUserTasks(@QueryParam("username") String username) {
        List<TaskRO> result = workflowService.getUserTasks(username).stream()
                .map(TaskRO::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/start")
    public ResponseEntity<ProcessRO> startProcess(@QueryParam("username") String username) {
        String id = workflowService.start(username);
        ProcessRO result = new ProcessRO();
        result.setId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRO> getTask(@PathVariable("id") String id) {
        TaskRO result = TaskRO.of(workflowService.getTask(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/complete")
    public ResponseEntity<TaskRO> completeTask(@RequestBody TaskCompleteRequest request) {
        Task task = workflowService.getTask(request.getTaskId());
        workflowService.complete(request.getTaskId(), request.getVariables());
        List<Task> tasks = workflowService.getTasks(task.getProcessInstanceId());
        TaskRO result = new TaskRO();
        if (!tasks.isEmpty()) {
            result = TaskRO.of(tasks.get(0));
        }

        return ResponseEntity.ok(result);
    }
}
