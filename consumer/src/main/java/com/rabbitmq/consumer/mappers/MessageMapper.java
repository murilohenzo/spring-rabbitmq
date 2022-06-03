package com.rabbitmq.consumer.mappers;

import com.rabbitmq.consumer.domain.SchedulingMessage;
import com.rabbitmq.consumer.dtos.SchedulingMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.support.converter.MessageConverter;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageConverter INSTANCE = Mappers.getMapper(MessageConverter.class);
    SchedulingMessage toModel(SchedulingMessageDto authorDTO);

    SchedulingMessageDto toDTO(SchedulingMessage author);
}
