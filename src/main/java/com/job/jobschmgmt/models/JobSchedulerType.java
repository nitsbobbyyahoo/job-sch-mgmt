package com.job.jobschmgmt.models;

public enum JobSchedulerType {
  /**
   * Performing a data-load into a DWH
   */
  LOAD_DATA,

  /**
   * Performing indexing of some file-based content
   */
  INDEX_FILES,

  /**
   *
   */
  SEND_EMAIL,

  /**
   * Sending emails
   */
  EXIT
}
