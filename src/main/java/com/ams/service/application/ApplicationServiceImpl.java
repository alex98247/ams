package com.ams.service.application;

import com.ams.dao.ApplicationDAO;
import com.ams.service.ApplicationService;
import com.ams.service.application.po.ApplicationPO;
import org.springframework.stereotype.Service;

/**
 * @author Alexey Mironov
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDAO applicationDAO;

    public ApplicationServiceImpl(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public Application get(long id) {
        ApplicationPO applicationPO = applicationDAO.find(id);

        Application application = new Application();
        application.setManagerUsername(applicationPO.getManagerUsername());
        application.setFinished(applicationPO.getFinished());
        application.setCustomerId(applicationPO.getId());
        application.setGoods(applicationPO.getGoods());

        return application;
    }

    @Override
    public void upsert(Application application) {
        ApplicationPO applicationPO = new ApplicationPO();
        applicationPO.setManagerUsername(application.getManagerUsername());
        applicationPO.setFinished(application.getFinished());
        applicationPO.setCustomerId(application.getId());
        applicationPO.setGoods(application.getGoods());

        if (application.getId() == null) {
            applicationDAO.create(applicationPO);
        } else {
            applicationDAO.update(applicationPO);
        }
    }

    @Override
    public void remove(long id) {
        applicationDAO.delete(id);
    }
}
