package com.rabbitmq.producer.config.rabbit;

import com.rabbitmq.producer.config.properties.RabbitProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableRabbit
@AllArgsConstructor
public class RabbitConfig {

    private final RabbitProperties properties;


    @Bean
    public ConnectionNameStrategy cns() {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.name");
    }

    @Bean
    public ConnectionFactory connectionFactory(ConnectionNameStrategy cns) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(properties.getHostname());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        connectionFactory.setConnectionNameStrategy(cns);
        return connectionFactory;
    }


    // método cria uma fila AMQP
    @Bean
    public Queue queue() {
        return new Queue(properties.getQueue());
    }

    // método cria uma troca de tópicos
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(properties.getMessage_exchange());
    }

    // método une esses dois, definindo o comportamento que ocorre quando RabbitTemplate publica em uma troca
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(properties.getRouting_key());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory(cns()));
        template.setMessageConverter(messageConverter());
        return template;
    }
}