package com.ams.service.application;

import com.ams.dao.ApplicationDAO;
import com.ams.service.ApplicationService;
import com.ams.service.application.po.ApplicationPO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        if(applicationPO == null){
            return null;
        }

        Application application = new Application();
        application.setId(applicationPO.getId());
        application.setManagerUsername(applicationPO.getManagerUsername());
        application.setFinished(applicationPO.getFinished());
        application.setCustomerId(applicationPO.getCustomerId());
        application.setGoods(applicationPO.getGoods());

        return application;
    }

    @Override
    public void upsert(Application application) {
        ApplicationPO applicationPO = new ApplicationPO();
        applicationPO.setId(application.getId());
        applicationPO.setManagerUsername(application.getManagerUsername());
        applicationPO.setFinished(application.getFinished());
        applicationPO.setCustomerId(application.getCustomerId());
        applicationPO.setGoods(application.getGoods());

        ApplicationPO po = applicationDAO.find(applicationPO.getId());
        if (po == null) {
            applicationDAO.create(applicationPO);
        } else {
            applicationDAO.update(applicationPO);
        }
    }

    @Override
    public void remove(long id) {
        applicationDAO.delete(id);
    }

    @Override
    public List<Application> getAll(String username) {
        return applicationDAO.getAll(username).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Application convert(ApplicationPO po) {
        Application application = new Application();
        application.setManagerUsername(po.getManagerUsername());
        application.setFinished(po.getFinished());
        application.setCustomerId(po.getId());
        application.setGoods(po.getGoods());

        return application;
    }
}
