package com.ams.service.workflow.delegate;

import com.ams.security.AuthorityType;
import com.ams.service.ApplicationService;
import com.ams.service.LoadBalancer;
import com.ams.service.WarehouseService;
import com.ams.service.WorkflowService;
import com.ams.service.application.Application;
import com.ams.service.workflow.WorkflowConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Mironov
 */
public class GoodsReservationDelegate implements JavaDelegate {

    private final WorkflowService workflowService;
    private final ApplicationService applicationService;
    private final WarehouseService warehouseService;
    private final LoadBalancer loadBalancer;

    public GoodsReservationDelegate(WorkflowService workflowService,
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
        warehouseService.reserve(application.getGoods());

        Map<String, Object> variables = new HashMap<>();
        if (application.needDelivery()) {
            variables.put(WorkflowConstants.NEED_DELIVERY_KEY, true);
            String user = loadBalancer.getUser(new SimpleGrantedAuthority(AuthorityType.ORDER.getName()));
            variables.put(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, user);
        } else {
            variables.put(WorkflowConstants.NEED_DELIVERY_KEY, false);
            variables.put(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, application.getManagerUsername());
        }

        workflowService.complete(execution.getId(), variables);
    }
}
