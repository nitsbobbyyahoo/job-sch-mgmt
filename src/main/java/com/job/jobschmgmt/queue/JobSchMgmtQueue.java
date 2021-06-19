package com.job.jobschmgmt.queue;

import com.job.jobschmgmt.dao.JobDao;
import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.models.JobSchedulerPriorityLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;

@Component
@Slf4j
public class JobSchMgmtQueue extends LinkedBlockingDeque<JobDetails> {

  /**
   * Serial Id.
   */
  private static final long serialVersionUID = 1L;

  @Autowired
  private JobDao jobDao;

  @Override
  public JobDetails take() throws InterruptedException {
    JobDetails jobDetails = super.takeFirst();
    log.info("Job " + jobDetails.getData() + " is taken from head of the queue");
    return jobDetails;
  }

  @Override
  public boolean offerFirst(JobDetails jobDetails) {
    if (jobDetails == null) {
      log.warn("jobDetails cannot be null to added to the queue");
      return false;
    }

    if (super.offerFirst(jobDetails)) {
      log.info("job {} is added to the queue", jobDetails.getData());
      return true;
    } else {
      log.info("job {} is not added to the queue", jobDetails.getData());
      return false;
    }
  }

  @Override
  public boolean offerLast(JobDetails jobDetails) {
    if (jobDetails == null) {
      log.warn("jobDetails cannot be null to added to the queue");
      return false;
    }

    if (super.offerLast(jobDetails)) {
      log.info("job {} is added to the queue", jobDetails.getData());
      return true;
    } else {
      log.info("job {} is not added to the queue", jobDetails.getData());
      return false;
    }
  }

  public boolean addJobToQueue(JobDetails jobDetails) {
    jobDao.saveJob(jobDetails);
    if (JobSchedulerPriorityLevel.HIGH == jobDetails.getJobSchedulerPriorityLevel()) {
      return offerFirst(jobDetails);
    }

    return offerLast(jobDetails);
  }
}
