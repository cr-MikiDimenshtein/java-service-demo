package com.cybereason.servicedemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "job_status")
public class JobStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "job_runtime_id")
    private UUID jobRuntimeId;
    private String status;
    @Column(name = "job_name")
    private String jobName;

    @Column(name = "last_updated")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date lastUpdated;

}
