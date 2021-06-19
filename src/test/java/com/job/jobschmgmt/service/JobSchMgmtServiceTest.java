package com.job.jobschmgmt.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JobSchMgmtServiceTest {

  @InjectMocks
  JobSchMgmtServiceImpl jobSchMgmtServiceImpl;

  @Test
  public void testSendEmail_Success() throws InterruptedException {
    boolean status = jobSchMgmtServiceImpl.sendEmail("send Email");
    assertTrue(status);
  }

  @Test
  public void testSendEmail_Failure() throws InterruptedException {
    boolean status = jobSchMgmtServiceImpl.sendEmail(null);
    assertFalse(status);
  }

  @Test
  public void testLoadData_Success() throws InterruptedException {
    boolean status = jobSchMgmtServiceImpl.loadData("Load Data");
    assertTrue(status);
  }

  @Test
  public void testLoadData_Failure() throws InterruptedException {
    boolean status = jobSchMgmtServiceImpl.loadData(null);
    assertFalse(status);
  }

  @Test
  public void testIndexFiles_Success() throws InterruptedException {
    boolean status = jobSchMgmtServiceImpl.indexFiles("Indexes Files");
    assertTrue(status);
  }

  @Test
  public void testIndexFiles_Failure() throws InterruptedException {
    boolean status = jobSchMgmtServiceImpl.indexFiles(null);
    assertFalse(status);
  }
}