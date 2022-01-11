package com.ams.service.workflow.delegate;

import com.ams.model.OrderGood;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        long id = Long.parseLong(execution.getProcessInstanceId());
        Application application = applicationService.get(id);
        Map<Long, Integer> warehouseGoods = warehouseService.getGoodsCount(application.getGoods().keySet());

        Map<Long, Integer> diff = WarehouseUtils.getDiff(warehouseGoods, application.getGoods());
        boolean needOrder = !diff.isEmpty();
        if (needOrder) {
            execution.setVariable(WorkflowConstants.EXIST_GOODS_KEY, false);
/*            List<OrderGood> orderGoods = diff.entrySet().stream().map(x -> {
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodId(x.getKey());
                orderGood.setCount(x.getValue());
                orderGood.setApplicationId(id);
                return orderGood;
            }).collect(Collectors.toList());
            warehouseService.insertOrderGood(orderGoods);*/
            String user = loadBalancer.getUser(new SimpleGrantedAuthority(AuthorityType.ORDER.getName()));
            execution.setVariable(WorkflowConstants.RESPONSIBLE_ASSISTANT_KEY, user);
        } else {
            execution.setVariable(WorkflowConstants.EXIST_GOODS_KEY, true);
        }
    }
}
