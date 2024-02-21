package com.cybereason.servicedemo.controller;

import com.cybereason.servicedemo.model.JobStatus;
import com.cybereason.servicedemo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("JobsController")
@RequestMapping(JobsController.PATH)
public class JobsController {

    public static final String PATH = "/job";

    private final JobService jobService;

    @Autowired
    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<JobStatus> getInfo() {
        return jobService.getAll();
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public String addJobStatus(@RequestBody JobStatus jobStatus) {
        jobService.addJobStatus(jobStatus);
        return "Job status added successfully";
    }
}
