package com.rabbitmq.producer.publisher;

import com.rabbitmq.producer.domain.SchedulingMessage;
import com.rabbitmq.producer.domain.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessagePublisher {

    public static final String ROUTING_KEY = "routing_key";
    public static final String EXCHANGE = "message_exchange";

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public SchedulingMessage publishMessage(@RequestBody SchedulingMessage message) {
        message.setStatus(StatusMessage.PENDING);
        template.convertAndSend(EXCHANGE,
                ROUTING_KEY, message);

        log.info("Action: Published message in Queue, Message: " + message.toString());

        return message;
    }
}
