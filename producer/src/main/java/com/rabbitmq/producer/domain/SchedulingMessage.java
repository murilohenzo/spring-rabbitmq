package com.rabbitmq.producer.domain;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SchedulingMessage {
    private String message;
    private StatusMessage status;
}
