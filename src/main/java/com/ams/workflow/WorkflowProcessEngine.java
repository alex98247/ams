package com.ams.workflow;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.Map;

//TODO: remove
@Service
public class WorkflowProcessEngine {

    private final ProcessEngine processEngine;

    public WorkflowProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public void assign(String taskId, String username) {
        try {
            processEngine.getTaskService().claim(taskId, username);
        } catch (Exception e) {
            final String message = "Assign task with id '{}' to user '{}' failed.";
            /*LOGGER.warn(message, taskId, userName);
            throw new WorkflowRuntimeException(message, WorkflowExceptionIds.EX_WF_ENGINE_ASSIGN_TASK_FAILED,
                    taskId, userName);*/
        }
    }

    public void complete(String taskId, Map<String, Object> variables) {

        try {
            processEngine.getTaskService().complete(taskId, variables);
        } catch (Exception e) {
            final String message = "Complete task with id '{}' failed.";
            /*LOGGER.warn(message, taskId);
            throw new WorkflowRuntimeException(message, WorkflowExceptionIds.EX_WF_ENGINE_COMPLETE_TASK_FAILED, taskId);*/
        }
    }

    public String start(String processDefinitionKey, Map<String, Object> variables) {
        try {
            ProcessInstance instance;
            instance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, variables);
            return instance.getProcessInstanceId();
        } catch (Exception e) {
            final String message = "Start workflow with process definition key '{}' failed.";
            throw new RuntimeException();
/*            LOGGER.warn(message, processDefinitionKey);
            throw new WorkflowRuntimeException(message, WorkflowExceptionIds.EX_WF_ENGINE_PROCESS_START_FAILED,
                    processDefinitionKey);*/
        }
    }
}
