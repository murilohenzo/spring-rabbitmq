package com.rabbitmq.consumer.dtos;

import com.rabbitmq.consumer.domain.StatusMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class SchedulingMessageDto implements Serializable {
    private final UUID id;
    private final String message;
    private final StatusMessage status;
    private final Date createdAt;
}
