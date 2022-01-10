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

        return convertToDTO(applicationPO);
    }

    @Override
    public void upsert(Application application) {
        ApplicationPO applicationPO = convertToPO(application);
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
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ApplicationPO convertToPO(Application source) {
        if (source == null) {
            return null;
        }

        ApplicationPO target = new ApplicationPO();
        target.setId(source.getId());
        target.setManagerUsername(source.getManagerUsername());
        target.setFinished(source.getFinished());
        target.setCustomerId(source.getCustomerId());
        target.setGoods(source.getGoods());
        target.setDelivery(source.getDelivery());
        return target;
    }

    private Application convertToDTO(ApplicationPO source) {
        if (source == null) {
            return null;
        }
        Application target = new Application();
        target.setId(source.getId());
        target.setManagerUsername(source.getManagerUsername());
        target.setFinished(source.getFinished());
        target.setCustomerId(source.getCustomerId());
        target.setGoods(source.getGoods());
        target.setDelivery(source.getDelivery());
        return target;
    }
}
