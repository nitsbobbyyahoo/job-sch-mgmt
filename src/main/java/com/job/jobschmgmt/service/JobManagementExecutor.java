package com.job.jobschmgmt.service;

import com.job.jobschmgmt.dao.JobDao;
import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.models.JobCurrentState;
import com.job.jobschmgmt.models.JobSchedulerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobManagementExecutor implements Runnable {

  private JobDetails jobInfo;
  @Autowired
  private JobSchMgmtService jobSchMgmtService;
  @Autowired
  private JobDao jobDAO;

  public JobManagementExecutor() {
  }

  public JobManagementExecutor(JobDetails jobInfo) {
    this.jobInfo = jobInfo;
  }


  public JobManagementExecutor(JobDetails jobInfo, com.job.jobschmgmt.service.JobSchMgmtService jobSchMgmtService,
      JobDao jobDAO) {
    this.jobInfo = jobInfo;
    this.jobSchMgmtService = jobSchMgmtService;
    this.jobDAO = jobDAO;
  }

  /**
   * Performing action of job based on job type
   */
  @Override
  public void run() {
    boolean jobActionStatus = false;
    jobInfo.getJobCurrentStateList().add(JobCurrentState.RUNNING);
    jobDAO.saveJob(jobInfo);
    try {
      log.info("Job {} execution started", jobInfo.getData().toUpperCase());

      if (JobSchedulerType.SEND_EMAIL == jobInfo.getJobSchedulerType()) {
        jobActionStatus = jobSchMgmtService.sendEmail(jobInfo.getData());
      } else if (JobSchedulerType.LOAD_DATA == jobInfo.getJobSchedulerType()) {
        jobActionStatus = jobSchMgmtService.loadData(jobInfo.getData());
      } else if (JobSchedulerType.INDEX_FILES == jobInfo.getJobSchedulerType()) {
        jobActionStatus = jobSchMgmtService.indexFiles(jobInfo.getData());
      }
      log.info("Job {} execution completed", jobInfo.getData().toUpperCase());
    } catch (Exception e) {
      log.error("Job execution exception is occured, " + e.getMessage());
    } finally {
      setJobState(jobActionStatus);
    }
  }

  private void setJobState(boolean jobActionSuccessful) {
    if (jobActionSuccessful) {
      jobInfo.getJobCurrentStateList().add(JobCurrentState.SUCCESS);
    } else {
      jobInfo.getJobCurrentStateList().add(JobCurrentState.FAILED);
    }
    jobDAO.saveJob(jobInfo);
  }
}
