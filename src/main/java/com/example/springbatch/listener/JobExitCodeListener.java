package com.example.springbatch.listener;

import com.example.springbatch.domain.BatchCommonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobExitCodeListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(JobExitCodeListener.class);

    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private BatchCommonDto batchCommonDto;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info(jobExecution.getJobInstance().getJobName() + " execution completed. ExitStatus="
                + jobExecution.getExitStatus().getExitCode());

        System.out.println(jobExecution.getExitStatus().getExitCode());

        jobRepository.update(jobExecution);

        if (jobExecution.getStatus() == BatchStatus.FAILED || batchCommonDto.getErrorInfos().size() != 0) {
            System.exit(1);
        } else {
            System.exit(0);
        }
    }
}
