package com.job.jobschmgmt.dao;

import com.job.jobschmgmt.models.JobDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JobDao {

  private final Map<UUID, JobDetails> jobsDetailMap = new ConcurrentHashMap<>();

  public void saveJob(JobDetails jobDetails) {
    if (jobDetails.getJobId() == null) {
      jobDetails.setJobId(UUID.randomUUID());
    }

    jobsDetailMap.put(jobDetails.getJobId(), jobDetails);
  }

  public Map<UUID, JobDetails> getAllJob() {
    return jobsDetailMap;
  }

  public int numberOfJobInQueue() {
    return jobsDetailMap.size();
  }

}
