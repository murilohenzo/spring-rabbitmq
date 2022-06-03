package com.rabbitmq.consumer.service;

import com.rabbitmq.consumer.domain.StatusMessage;
import com.rabbitmq.consumer.repository.SchedulingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
public class SchedulingService {
    private final SchedulingRepository schedulingRepository;

    @Scheduled(cron = "${scheduled.cron.tab}", zone = "${scheduled.time.zone}")
    public void execute() {
        schedulingRepository.findAll().forEach(message -> {
            if (Objects.equals(StatusMessage.PENDING, message.getStatus())) {
                log.info("Dispatch message for email");
                message.setStatus(StatusMessage.FINISHED);
            }
        });
    }
}
