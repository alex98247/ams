package com.ams.workflow;

import com.ams.configuration.CoreConfigurationConstants;
import com.ams.service.workflow.WorkflowConstants;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.util.EngineUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Starter implements InitializingBean {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private WorkflowProcessEngine workflowProcessEngine;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> variables = new HashMap<>();
        variables.put("responsible_assistant", "admin");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(WorkflowConstants.PROCESS_DEFINITION_KEY, variables);
        List<Task> list = EngineUtil.lookupProcessEngine(WorkflowConstants.WORKFLOW_ENGINE_NAME)
                .getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).list();
        if (!list.isEmpty()) {
            workflowProcessEngine.complete(list.get(0).getId(), Map.of("new", true, "caseDefinitionId", "sdsfkgl;fkg"));
            /*EngineUtil.lookupProcessEngine(CoreConfigurationConstants.WORKFLOW_ENGINE_NAME)
                    .getTaskService().complete(list.get(0).getId());*/
        }
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
}
