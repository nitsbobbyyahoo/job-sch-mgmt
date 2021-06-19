package com.job.jobschmgmt.web.rest;

import com.job.jobschmgmt.dao.JobDao;
import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.queue.JobSchMgmtQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping("/managejobschedule")
@Slf4j
public class JobScheduleMgmtController {

  @Autowired
  JobSchMgmtQueue jobSchMgmtQueue;

  @Autowired
  private JobDao jobDao;

  @PostMapping("/addjobtoschedulerqueue")
  public ResponseEntity<String> addJobToQueue(@Valid @RequestBody JobDetails jobDetails)
      throws InterruptedException {
    jobSchMgmtQueue.addJobToQueue(jobDetails);
    return new ResponseEntity("Job added to queue successfully.", HttpStatus.OK);
  }

  @GetMapping("/getAllJobs")
  public ResponseEntity<Map<UUID, JobDetails>> getAllJobs() {
    return new ResponseEntity<>(jobDao.getAllJob(), HttpStatus.OK);
  }
}
