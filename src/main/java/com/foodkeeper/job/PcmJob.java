package com.foodkeeper.job;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;


@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
public class PcmJob {

    public static final Logger logger = LoggerFactory.getLogger(PcmJob.class);

    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

    @Autowired
    private JobLauncher jobLauncher;

    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    logger.info("유통기한 배치 수행");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Scheduled(fixedDelay = 1000)
    public void launch() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("batch-date",
                System.currentTimeMillis()).toJobParameters();

        JobExecution exec = jobLauncher.run(job(), jobParameters);

        logger.info("Exit Status: " + exec.getStatus());
    }
}