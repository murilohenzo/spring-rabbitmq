package com.rabbitmq.consumer.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.consumer.domain.SchedulingMessage;
import com.rabbitmq.consumer.domain.StatusMessage;
import com.rabbitmq.consumer.dtos.SchedulingMessageDto;
import com.rabbitmq.consumer.mappers.MessageMapper;
import com.rabbitmq.consumer.repository.SchedulingRepository;
import com.rabbitmq.consumer.service.SchedulingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class ListenerMessage {
    private final SchedulingService service;

    @RabbitListener(queues = "${rabbit.queue}")
    public void listener(String message) {
        service.create(stringToObject(message));
    }

    private SchedulingMessage stringToObject(String message) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        log.info("Action: Successful Action, Message: Transformation string to object structure");
        return gson.fromJson(message,SchedulingMessage.class);
    }
}
