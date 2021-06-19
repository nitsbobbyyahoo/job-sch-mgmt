package com.job.jobschmgmt.service;

import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.queue.JobSchMgmtQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JobManagmentProccesor {

  @Autowired
  private ScheduledExecutorService executorService;

  @Autowired
  private JobSchMgmtQueue jobQueue;

  @Autowired
  private ApplicationContext applicationContext;

  @Scheduled(fixedDelayString = "${job.queue.execution.time}")
  public void startProssingJob() {
    try {
      while (jobQueue.size() > 0 && !Thread.currentThread().isInterrupted()) {
        JobDetails takenJobInfo = jobQueue.take();
        runJobExecutor(takenJobInfo);
      }
    } catch (InterruptedException e) {
      log.error(" JobManagmentProccesor exception: " + e.getMessage());
      Thread.currentThread().interrupt();
    } catch (Exception e) {
      log.error("JobManagmentProccesor exception is occured, " + e.getMessage());
    }

  }

  private void runJobExecutor(final JobDetails takenJobInfo) {
    JobManagementExecutor jobExecutor =
        (JobManagementExecutor) applicationContext.getBean("jobManagementExecutor", takenJobInfo);
    executorService.schedule(jobExecutor, takenJobInfo.getDelay(), TimeUnit.MILLISECONDS);
  }
}
