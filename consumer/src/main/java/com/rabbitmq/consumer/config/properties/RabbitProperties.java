package com.rabbitmq.consumer.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbit")
public class RabbitProperties {

    private String queue;
    private Server server = new Server();

    @Getter
    @Setter
    public static class Server {
        private String hostname;
        private Integer port;
        private String username;
        private String password;
    }
}