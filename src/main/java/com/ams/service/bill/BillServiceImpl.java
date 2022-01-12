package com.ams.service.bill;

import com.ams.service.ApplicationService;
import com.ams.service.BillService;
import com.ams.service.LegalEntityService;
import com.ams.service.WarehouseService;
import com.ams.service.application.Application;
import com.ams.service.legalentity.LegalEntity;
import com.ams.service.warehouse.Good;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author northbringer
 */
@Service
public class BillServiceImpl implements BillService {

    private static final String BILL_TEMPLATE_PATH = "src\\main\\resources\\bill_blank.jrxml";
    private static final String BILLS_PATH = "src\\main\\resources\\bills\\";

    private final ApplicationService applicationService;
    private final LegalEntityService legalEntityService;
    private final WarehouseService warehouseService;

    public BillServiceImpl(ApplicationService applicationService,
                           LegalEntityService legalEntityService,
                           WarehouseService warehouseService) {
        this.applicationService = applicationService;
        this.legalEntityService = legalEntityService;
        this.warehouseService = warehouseService;
    }

    @Override
    public void saveBillAsPdf(long applicationId) {
        Bill billToSave = createBill(applicationId);

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(BILL_TEMPLATE_PATH);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(billToSave.getTotalOrderedGoods());

            Map<String, Object> parameters = new HashMap();
            parameters.put("BILL_ID", billToSave.getId());
            parameters.put("CONSUMER_NAME", billToSave.getConsumerName());
            parameters.put("CONSUMER_INN", billToSave.getConsumerINN());

            JasperPrint printableBill = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(printableBill, BILLS_PATH + applicationId + ".pdf");

        } catch (JRException e) {
            Log.error("Error while creating report");
        }
    }

    @Override
    public byte[] getBill(long applicationId) throws IOException {
        File bill = new File(BILLS_PATH + applicationId + ".pdf");
        return IOUtils.toByteArray(new FileInputStream(bill));
    }

    private Bill createBill(long applicationId) {
        Application application = applicationService.get(applicationId);
        long customerId = application.getCustomerId();
        LegalEntity consumer = legalEntityService.getLegalEntity(customerId);
        Map<Good, Integer> currentGoods;

        String consumerName;
        String consumerINN;
        List<GoodsRow> totalOrderedGoods;

        consumerName = consumer.getName();
        consumerINN = consumer.getInn();

        currentGoods = warehouseService.getOrderGoods(applicationId);
        totalOrderedGoods = currentGoods
                .entrySet()
                .stream()
                .map(x ->
                        new GoodsRow(x.getKey().getId(),
                                x.getKey().getName(),
                                x.getValue(),
                                x.getKey().getCost()))
                .collect(Collectors.toList());

        return new Bill(applicationId, consumerName, Long.parseLong(consumerINN), totalOrderedGoods);
    }
}
