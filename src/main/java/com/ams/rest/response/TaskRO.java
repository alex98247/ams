package com.ams.rest.response;

import org.camunda.bpm.engine.task.Task;

/**
 * @author Alexey Mironov
 */
public class TaskRO {

    private String id;
    private String name;
    private String definitionKey;
    private String applicationId;

    public static TaskRO of(Task task) {
        TaskRO result = new TaskRO();
        result.setId(task.getId());
        result.setName(task.getName());
        result.setDefinitionKey(task.getTaskDefinitionKey());
        result.setApplicationId(task.getProcessInstanceId());
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinitionKey() {
        return definitionKey;
    }

    public void setDefinitionKey(String definitionKey) {
        this.definitionKey = definitionKey;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}