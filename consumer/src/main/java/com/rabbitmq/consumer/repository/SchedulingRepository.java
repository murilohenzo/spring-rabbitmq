package com.rabbitmq.consumer.repository;

import com.rabbitmq.consumer.domain.SchedulingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository <SchedulingMessage, Long> {
}
