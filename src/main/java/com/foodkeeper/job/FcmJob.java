package com.foodkeeper.job;

import com.foodkeeper.domain.FcmDto;
import com.foodkeeper.domain.NotificationItemDto;
import com.foodkeeper.service.OrderItemBiz;
import com.foodkeeper.service.PcmBiz;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
public class FcmJob {

    public static final Logger logger = LoggerFactory.getLogger(FcmJob.class);

    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private OrderItemBiz orderItemBiz;

    @Autowired
    private PcmBiz pcmBiz;

    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    //푸시 정보 조회
                    List<NotificationItemDto> notificationItemDtoList = orderItemBiz.getNotificationItemList();
                    HashMap<String, FcmDto> fcmDtoMap = Maps.newHashMap();

                    //정보 가공, 푸시, 푸시 중복 기록
                    if(notificationItemDtoList.size() > 0) {
                        FcmDto fcmDto = new FcmDto();
                        fcmDto.setToken(notificationItemDtoList.get(0).getToken());
                        fcmDto.setTitle("구매한 물품 유통기한 임박");
                        fcmDto.setBody("총 " + notificationItemDtoList.size() + "개의 유통기한 임박 상품이 있습니다.");

                        logger.info("=========================");
                        for(NotificationItemDto notificationItemDto : notificationItemDtoList) {
                            logger.info("notificationItemDto = " + notificationItemDto);
                            //푸시
                            if(!fcmDtoMap.containsKey(fcmDto.getToken())) {
                                fcmDtoMap.put(fcmDto.getToken(), fcmDto);
                                pcmBiz.send(fcmDtoMap.get(fcmDto.getToken()));
                            }
                            //푸시 중복 기록
                            if(fcmDto.getToken() == notificationItemDto.getToken() && fcmDto.isSuccess()) {
                                orderItemBiz.disuseNotificationById(notificationItemDto.getOrderItemId());
                            }
                        }
                    }

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Scheduled(fixedDelay = 10000)
    public void launch() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("batch-date",
                System.currentTimeMillis()).toJobParameters();

        try {
            JobExecution exec = jobLauncher.run(job(), jobParameters);
        } catch(Exception e) {
            logger.error("batch err: " + e);
        }
    }
}