# Job Scheduler Management Service

## This Microservices exposed two api's to manage jobs scheduler:
```sh
 1. The first api caters to adding job to the service queue - It's a POST service to put the job into the queue:
 - http://localhost:8060/managejobschedule/addjobtoschedulerqueue
```
Header
  - contentType: application/json

Input Request 
```sh
  body: {
    "data": "Job 1",
    "jobType": "LOAD_DATA",
    "priorityLevel": "LOW",
    "delay": 2000
}
```
JobType: As per requirement 3 different type of job are available: 
  - LOAD_DATA
  - INDEX_FILES
  - SEND_EMAIL

Priority Level: There are 2 priority levels:
   - LOW
   - HIGH

And ofcourse by default it will be LOW.

Based on priority task will get executed with HIGH precedence over LOW priority task.

There is a Delay time in request, to tell after how many millisecond task should execute.

Response
```sh
     Job added to queue successfully.
```
```sh
2. Second API is a developd for getting all jobs from the queue. - It's a GET CALL
 - http://localhost:8060/managejobschedule/getAllJobs
```

This API will return complete information of all job available in application.
The state of the job is QUEUE or RUNNING or SUCCESSES or FAILED
```sh
{
    "07630bf3-f506-47a9-9290-375d2ecc2b3f": {
        "jobSchMgmtService": null,
        "jobId": "07630bf3-f506-47a9-9290-375d2ecc2b3f",
        "data": "Job 4",
        "jobSchedulerType": "INDEX_FILES",
        "jobSchedulerPriorityLevel": "HIGH",
        "jobCurrentStateList": [
            "QUEUED",
            "RUNNING",
            "SUCCESS"
        ],
        "delay": 0
    },
    "5046e429-5fcc-4487-8618-971ae154e000": {
        "jobSchMgmtService": null,
        "jobId": "5046e429-5fcc-4487-8618-971ae154e000",
        "data": "Job 1",
        "jobSchedulerType": "SEND_EMAIL",
        "jobSchedulerPriorityLevel": "HIGH",
        "jobCurrentStateList": [
            "QUEUED",
            "RUNNING",
            "SUCCESS"
        ],
        "delay": 0
    },
    "20a3164a-7ce6-471e-89f1-40c096d096b8": {
        "jobSchMgmtService": null,
        "jobId": "20a3164a-7ce6-471e-89f1-40c096d096b8",
        "data": "Job 4",
        "jobSchedulerType": "INDEX_FILES",
        "jobSchedulerPriorityLevel": "HIGH",
        "jobCurrentStateList": [
            "QUEUED",
            "RUNNING",
            "SUCCESS"
        ],
        "delay": 0
    },
    "c7572ace-f2a7-404c-b231-8c0ece412156": {
        "jobSchMgmtService": null,
        "jobId": "c7572ace-f2a7-404c-b231-8c0ece412156",
        "data": "Job 3",
        "jobSchedulerType": "INDEX_FILES",
        "jobSchedulerPriorityLevel": "HIGH",
        "jobCurrentStateList": [
            "QUEUED",
            "RUNNING",
            "SUCCESS"
        ],
        "delay": 0
    },
    "373ddafb-3d16-4678-b9de-9706ef25b329": {
        "jobSchMgmtService": null,
        "jobId": "373ddafb-3d16-4678-b9de-9706ef25b329",
        "data": "Job 2",
        "jobSchedulerType": "LOAD_DATA",
        "jobSchedulerPriorityLevel": "LOW",
        "jobCurrentStateList": [
            "QUEUED",
            "RUNNING",
            "SUCCESS"
        ],
        "delay": 0
    }
}
```

