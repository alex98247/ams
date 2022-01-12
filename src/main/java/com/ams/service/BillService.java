package com.ams.service;


import java.io.IOException;

/**
 * @author northbringer
 */
public interface BillService {
    void saveBillAsPdf(long applicationId);

    byte[] getBill(long applicationId) throws IOException;
}
