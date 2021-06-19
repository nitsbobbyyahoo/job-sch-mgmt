package com.job.jobschmgmt.configure;

import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.service.JobManagementExecutor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@EnableScheduling
public class JobConfiguration {

  public int getNumberOfCore() {
    return Runtime.getRuntime().availableProcessors();
  }

  @Bean
  public ScheduledExecutorService getExecutorService() {
    return Executors.newScheduledThreadPool(getNumberOfCore());
  }

  @Bean
  @Scope(BeanDefinition.SCOPE_PROTOTYPE)
  public JobManagementExecutor jobManagementExecutor(JobDetails jobDetails) {
    return new JobManagementExecutor(jobDetails);
  }

}
