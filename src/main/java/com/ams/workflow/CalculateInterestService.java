package com.ams.workflow;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateInterestService implements JavaDelegate {

    @Autowired
    TaskService taskService;

    @Override
    public void execute(DelegateExecution delegate) {
        System.out.println("Spring Bean invoked.");

    }
}
