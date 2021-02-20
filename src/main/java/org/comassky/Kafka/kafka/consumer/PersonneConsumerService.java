package org.comassky.Kafka.kafka.consumer;

import org.comassky.Kafka.dto.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PersonneConsumerService {

	private final Logger logger = LoggerFactory.getLogger(PersonneConsumerService.class);

	@KafkaListener(topics = "${personnes.topic.group.name}",
			groupId = "${personnes.topic.group.id}",
			containerFactory = "personneKafkaListener")
	public void consume(Personne personne) throws InterruptedException {
		Thread.sleep(100);
		logger.info(String.format("Personne created -> %s", personne.toString()));
	}
}
