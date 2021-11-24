package com.ams.workflow;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateInterestService implements JavaDelegate {

    @Autowired
    TaskService taskService;

    @Autowired
    WorkflowProcessEngine workflowProcessEngine;

    @Autowired
    ProcessEngine processEngine;

    @Override
    public void execute(DelegateExecution delegate) {
        System.out.println("Spring Bean invoked.");

        List<Task> tasks = processEngine.getTaskService().createTaskQuery().processInstanceId(delegate.getProcessInstanceId()).list();

    }
}
