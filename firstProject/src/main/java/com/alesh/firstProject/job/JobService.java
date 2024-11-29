package com.alesh.firstProject.job;

import java.util.List;

public interface JobService {
   List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean delereJob(Long id);

    boolean updateJob(Long id, Job updateJob);
}
