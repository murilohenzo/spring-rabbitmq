package com.rabbitmq.producer.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(value = "rabbit")
public class RabbitProperties {
    private String hostname;
    private Integer port;
    private String username;
    private String password;
    private String routing_key;
    private String message_exchange;
    private String queue;
}