package com.ams.service;

import com.ams.service.application.Application;

/**
 * @author Alexey Mironov
 */
public interface ApplicationService {

    Application get(long id);

    void upsert(Application application);

    void remove(long id);
}
