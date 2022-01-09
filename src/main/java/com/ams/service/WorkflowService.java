package com.ams.service;

import org.camunda.bpm.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @author Alexey Mironov
 */
public interface WorkflowService {

    List<Task> getUserTasks(String username);

    void complete(String taskId, Map<String, Object> variables);

    List<Task> getTasks(String processInstanceId);

    Task getTask(String id);

    void assign(String taskId, String username);

    Map<String, Object> getVariables(String taskId);

    String start(String username);
}
