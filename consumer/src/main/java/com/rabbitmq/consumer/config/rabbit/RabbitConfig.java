package com.rabbitmq.consumer.config.rabbit;

import com.rabbitmq.consumer.config.properties.RabbitProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableRabbit
@AllArgsConstructor
public class RabbitConfig {

    private final RabbitProperties rabbitProperties;

    @Bean
    public ConnectionNameStrategy cns() {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.name");
    }

    @Bean
    public ConnectionFactory connectionFactory(ConnectionNameStrategy cns) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitProperties.getServer().getHostname());
        connectionFactory.setPort(rabbitProperties.getServer().getPort());
        connectionFactory.setUsername(rabbitProperties.getServer().getUsername());
        connectionFactory.setPassword(rabbitProperties.getServer().getPassword());
        connectionFactory.setConnectionNameStrategy(cns);
        return connectionFactory;
    }
}