package com.job.jobschmgmt.dao;

import com.job.jobschmgmt.models.JobCurrentState;
import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.models.JobSchedulerPriorityLevel;
import com.job.jobschmgmt.models.JobSchedulerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class JobDaoTest {

  @InjectMocks
  private JobDao jobDao;

  @Test
  public void save_job_success() {
    JobDetails jobDetails = new JobDetails("low", JobSchedulerType.SEND_EMAIL, JobSchedulerPriorityLevel.LOW);
    JobDetails jobDetails1 = new JobDetails("HIGH", JobSchedulerType.SEND_EMAIL, JobSchedulerPriorityLevel.HIGH);
    jobDao.saveJob(jobDetails);
    jobDao.saveJob(jobDetails1);
    Assertions.assertEquals(2, jobDao.numberOfJobInQueue());
  }

  @Test
  public void getALLJob_QUEUE() {
    JobDetails jobDetails = new JobDetails("low", JobSchedulerType.SEND_EMAIL, JobSchedulerPriorityLevel.LOW);
    JobDetails jobDetails1 = new JobDetails("HIGH", JobSchedulerType.SEND_EMAIL, JobSchedulerPriorityLevel.HIGH);
    jobDao.saveJob(jobDetails);
    jobDao.saveJob(jobDetails1);
    Assertions.assertEquals(1, jobDao.getAllJob().get(jobDetails.getJobId()).getJobCurrentStateList().size());
    Assertions.assertEquals(JobCurrentState.QUEUED,
        jobDao.getAllJob().get(jobDetails.getJobId()).getJobCurrentStateList().get(0));
  }
}
