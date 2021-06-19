package com.job.jobschmgmt.web.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.jobschmgmt.models.JobDetails;
import com.job.jobschmgmt.models.JobSchedulerPriorityLevel;
import com.job.jobschmgmt.models.JobSchedulerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JobScheduleMgmtControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  MockMvc mvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void postJob_withBody() throws Exception {

    JobDetails loadData = new JobDetails("load data", JobSchedulerType.LOAD_DATA, JobSchedulerPriorityLevel.LOW);

    String jobStr = objectMapper.writeValueAsString(loadData);
    mvc.perform(MockMvcRequestBuilders.post("/managejobschedule/addjobtoschedulerqueue")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jobStr)).andExpect(status().isOk());
  }

  @Test
  void postJob_withoutBody() throws Exception {

    JobDetails loadData = new JobDetails(null, JobSchedulerType.LOAD_DATA, JobSchedulerPriorityLevel.LOW);
    String exceptedError = "Input Field: data - Data is empty/null.";
    String jobStr = objectMapper.writeValueAsString(loadData);
    mvc.perform(MockMvcRequestBuilders.post("/managejobschedule/addjobtoschedulerqueue")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jobStr)).andExpect(status().isBadRequest())
        .andExpect(content().string(exceptedError));
  }

  @Test
  void getAllJob() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/managejobschedule/getAllJobs")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
