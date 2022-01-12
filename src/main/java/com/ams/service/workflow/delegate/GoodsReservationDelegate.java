package com.ams.service.workflow.delegate;

import com.ams.security.AuthorityType;
import com.ams.service.ApplicationService;
import com.ams.service.BillService;
import com.ams.service.LoadBalancer;
import com.ams.service.WarehouseService;
import com.ams.service.application.Application;
import com.ams.service.workflow.WorkflowConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @author Alexey Mironov
 */
@Component
public class GoodsReservationDelegate implements JavaDelegate {

    private final ApplicationService applicationService;
    private final WarehouseService warehouseService;
    private final LoadBalancer loadBalancer;
    private final BillService billService;

    public GoodsReservationDelegate(ApplicationService applicationService,
                                    WarehouseService warehouseService,
                                    LoadBalancer loadBalancer,
                                    BillService billService) {
        this.applicationService = applicationService;
        this.warehouseService = warehouseService;
        this.loadBalancer = loadBalancer;
        this.billService = billService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long id = Long.parseLong(execution.getProcessInstanceId());
        Application application = applicationService.get(id);
        warehouseService.reserve(application.getGoods());

        if (application.getNeedDelivery()) {
            execution.setVariable(WorkflowConstants.NEED_DELIVERY_KEY, true);
            String user = loadBalancer.getUser(new SimpleGrantedAuthority(AuthorityType.ORDER.getName()));
            execution.setVariable(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, user);
        } else {
            execution.setVariable(WorkflowConstants.NEED_DELIVERY_KEY, false);
            execution.setVariable(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, application.getManagerUsername());
        }

        billService.saveBillAsPdf(id);
    }
}
