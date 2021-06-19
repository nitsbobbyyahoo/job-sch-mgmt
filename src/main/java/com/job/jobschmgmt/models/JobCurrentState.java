package com.job.jobschmgmt.models;

/*
    Each job should have one of the below states at any time.
 */
public enum JobCurrentState {

  /**
   * Job is queued.
   */
  QUEUED,

  /**
   * Job is already taken from the queue and being executed.
   */
  RUNNING,

  /**
   * Job is executed successfully.
   */
  SUCCESS,

  /**
   * Job is failed.
   */
  FAILED
}
