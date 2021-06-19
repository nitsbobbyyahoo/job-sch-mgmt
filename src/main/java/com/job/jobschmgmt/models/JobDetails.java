package com.job.jobschmgmt.models;

import com.job.jobschmgmt.service.JobSchMgmtService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class JobDetails implements Serializable {

  private static final long serialVersionUID = 8256726251492277771L;

  @Autowired
  private JobSchMgmtService jobSchMgmtService;

  private UUID jobId;

  @NotNull(message = "Data is empty/null.")
  private String data;

  @NotNull(message = "Please provide jobType.")
  private JobSchedulerType jobSchedulerType;

  @NotNull(message = "Please provide job priority level.")
  private JobSchedulerPriorityLevel jobSchedulerPriorityLevel;

  private List<JobCurrentState> jobCurrentStateList;
  private long delay;

  {
    this.jobCurrentStateList = new ArrayList<>();
    this.jobCurrentStateList.add(JobCurrentState.QUEUED);
    this.delay = 0;
  }

  public JobDetails(String data, JobSchedulerType jobSchedulerType,
      JobSchedulerPriorityLevel jobSchedulerPriorityLevel) {
    this.data = data;
    this.jobSchedulerType = jobSchedulerType;
    this.jobSchedulerPriorityLevel = jobSchedulerPriorityLevel;
  }

  public JobDetails(String data, JobSchedulerType jobSchedulerType, JobSchedulerPriorityLevel jobSchedulerPriorityLevel,
      long delay) {
    this.data = data;
    this.jobSchedulerType = jobSchedulerType;
    this.jobSchedulerPriorityLevel = jobSchedulerPriorityLevel;
    this.jobCurrentStateList = new ArrayList<>();
    this.jobCurrentStateList.add(JobCurrentState.QUEUED);
    this.delay = delay;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    JobDetails jobDetails = (JobDetails) object;

    if (delay != jobDetails.delay) {
      return false;
    }
    if (!data.equals(jobDetails.data)) {
      return false;
    }
    if (jobSchedulerType != jobDetails.jobSchedulerType) {
      return false;
    }
    if (jobSchedulerPriorityLevel != jobDetails.jobSchedulerPriorityLevel) {
      return false;
    }
    return jobCurrentStateList == jobDetails.jobCurrentStateList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, jobSchedulerType, jobSchedulerPriorityLevel, jobCurrentStateList, delay);
  }
}
