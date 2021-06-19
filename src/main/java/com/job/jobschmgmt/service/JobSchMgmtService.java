package com.job.jobschmgmt.service;

public interface JobSchMgmtService {
    boolean sendEmail(String data);

    boolean loadData(String data);

    boolean indexFiles(String data);
    
}
