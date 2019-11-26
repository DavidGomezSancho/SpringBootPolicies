package com.home.policies.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.home.policies.dto.PolicyDto;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@KafkaListener(topics = "policies", groupId = "group_id")
	public void consume(PolicyDto message) {
		logger.info(String.format("Received update with updateDto: %s", message.toString()));
	}
}
