package com.ams.service.application;

import com.ams.service.ApplicationService;
import org.springframework.stereotype.Service;

/**
 * @author Alexey Mironov
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public Application get(long id) {
        return null;
    }

    @Override
    public void upsert(Application application) {

    }

    @Override
    public void remove(long id) {

    }
}
