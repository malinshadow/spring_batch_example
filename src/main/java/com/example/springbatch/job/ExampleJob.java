package com.example.springbatch.job;

import com.example.springbatch.domain.AccountDto;
import com.example.springbatch.listener.JobExitCodeListener;
import com.example.springbatch.processor.ExampleProcessor;
import com.example.springbatch.reader.ExampleReader;
import com.example.springbatch.writer.ExampleWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ExampleReader exampleReader;
    @Autowired
    private ExampleWriter exampleWriter;
    @Autowired
    private ExampleProcessor exampleProcessor;
    @Autowired
    private JobExitCodeListener jobExitCodeListener;

    @Bean
    public Job scanRecurringPaymentsBatchJob() {
        return jobBuilderFactory.get("exampleJob")
                .start(scanRecurringPaymentsStep())
                .listener(jobExitCodeListener)
                .build();
    }

    @Bean
    public Step scanRecurringPaymentsStep() {
        return stepBuilderFactory.get("exampleStep")
                .<AccountDto, AccountDto>chunk(50)
                .reader(exampleReader)
                .processor(exampleProcessor)
                .writer(exampleWriter)
                .build();
    }

}
