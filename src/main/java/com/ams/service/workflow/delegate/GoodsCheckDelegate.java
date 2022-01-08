package com.ams.service.workflow.delegate;

import com.ams.security.AuthorityType;
import com.ams.service.ApplicationService;
import com.ams.service.LoadBalancer;
import com.ams.service.WarehouseService;
import com.ams.service.WorkflowService;
import com.ams.service.application.Application;
import com.ams.service.warehouse.WarehouseUtils;
import com.ams.service.workflow.WorkflowConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GoodsCheckDelegate implements JavaDelegate {

    private final WorkflowService workflowService;
    private final ApplicationService applicationService;
    private final WarehouseService warehouseService;
    private final LoadBalancer loadBalancer;

    public GoodsCheckDelegate(WorkflowService workflowService,
                              ApplicationService applicationService,
                              WarehouseService warehouseService,
                              LoadBalancer loadBalancer) {
        this.workflowService = workflowService;
        this.applicationService = applicationService;
        this.warehouseService = warehouseService;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int id = (int) execution.getVariable(WorkflowConstants.APPLICATION_ID_KEY);
        Application application = applicationService.get(id);
        Map<Long, Integer> warehouseGoods = warehouseService.getGoodsCount(application.getGoods().keySet());

        boolean needOrder = WarehouseUtils.needOrder(warehouseGoods, application.getGoods());
        Map<String, Object> variables = new HashMap<>();
        if (needOrder) {
            variables.put(WorkflowConstants.EXIST_GOODS_KEY, false);
            String user = loadBalancer.getUser(new SimpleGrantedAuthority(AuthorityType.ORDER.getName()));
            variables.put(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, user);
        } else {
            variables.put(WorkflowConstants.EXIST_GOODS_KEY, true);
        }

        variables.put(WorkflowConstants.APPLICATION_ID_KEY, id);
        workflowService.complete(execution.getId(), variables);
    }
}
