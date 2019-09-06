package com.foodkeeper.controller;

import com.foodkeeper.domain.CommonResponse;
import com.foodkeeper.job.PcmJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@RestController
public class BatchController {

    public static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private PcmJob pcmJob;

    @GetMapping("/batch")
    public ResponseEntity batch() {
        try {
            pcmJob.launch();
        } catch (Exception e) {
            logger.info("Launcher has failed", e);
            return new ResponseEntity(new CommonResponse("ERROR", "batch ERROR!"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("배치 수행 성공", HttpStatus.OK);
    }


}
