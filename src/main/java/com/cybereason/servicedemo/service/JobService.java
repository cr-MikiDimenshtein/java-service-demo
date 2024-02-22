package com.cybereason.servicedemo.service;


import com.cybereason.servicedemo.model.JobStatus;
import com.cybereason.servicedemo.repository.JobStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobStatusRepository jobStatusRepository;

    @Autowired
    public JobService(JobStatusRepository jobStatusRepository) {
        this.jobStatusRepository = jobStatusRepository;
    }

    public void addJobStatus(JobStatus jobStatus) {
        jobStatus.setLastUpdated(new java.sql.Date(System.currentTimeMillis()));
        jobStatusRepository.save(jobStatus);
    }

    public List<JobStatus> getAll(String status) {
        if (status != null) {
            return jobStatusRepository.findByStatus(status);
        }
        return jobStatusRepository.findAll();
    }
}
