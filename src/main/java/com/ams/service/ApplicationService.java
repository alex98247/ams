package com.ams.service;

import com.ams.service.application.Application;

import java.util.List;

/**
 * @author Alexey Mironov
 */
public interface ApplicationService {

    Application get(long id);

    void upsert(Application application);

    void remove(long id);

    List<Application> getAll(String username);
}
