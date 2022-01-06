package com.ams.service.workflow;

import com.ams.service.WorkflowService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Alexey Mironov
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final ProcessEngine processEngine;

    public WorkflowServiceImpl(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public List<Task> getUserTasks(String username) {
        return processEngine.getTaskService()
                .createTaskQuery()
                .active()
                .taskAssignee(username)
                .list();
    }

    @Override
    public void complete(String taskId, Map<String, Object> variables) {
        try {
            processEngine.getTaskService().complete(taskId, variables);
        } catch (Exception e) {
            final String message = "Complete task with id '{}' failed.";
            /*LOGGER.warn(message, taskId);
            throw new WorkflowRuntimeException(message, WorkflowExceptionIds.EX_WF_ENGINE_COMPLETE_TASK_FAILED, taskId);*/
        }
    }

    @Override
    public List<Task> getTasks(String processInstanceId) {
        return processEngine.getTaskService()
                .createTaskQuery()
                .active()
                .processInstanceId(processInstanceId)
                .list();
    }

    @Override
    public Task getTask(String id) {
        return processEngine.getTaskService()
                .createTaskQuery()
                .taskId(id)
                .singleResult();
    }

    @Override
    public String start(String username) {
        try {
            ProcessInstance instance;
            Map<String, Object> params = Map.of(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, username);
            instance = processEngine.getRuntimeService().startProcessInstanceByKey(WorkflowConstants.PROCESS_DEFINITION_KEY, params);
            return instance.getProcessInstanceId();
        } catch (Exception e) {
            final String message = "Start workflow with process definition key '{}' failed.";
            throw new RuntimeException();
/*            LOGGER.warn(message, processDefinitionKey);
            throw new WorkflowRuntimeException(message, WorkflowExceptionIds.EX_WF_ENGINE_PROCESS_START_FAILED,
                    processDefinitionKey);*/
        }
    }

    @Override
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
}
