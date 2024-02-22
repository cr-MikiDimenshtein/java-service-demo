package com.cybereason.servicedemo.repository;

import com.cybereason.servicedemo.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
    List<JobStatus> findByStatus(String status);
}
