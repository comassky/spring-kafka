package org.comassky.Kafka.kafka.consumer;

import org.comassky.Kafka.dto.Personne;
import org.comassky.Kafka.dto.Ville;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VilleConsumerService {

	private final Logger logger = LoggerFactory.getLogger(PersonneConsumerService.class);

	@KafkaListener(topics = "${villes.topic.group.name}",
			groupId = "${villes.topic.group.id}",
			containerFactory = "villeKafkaListener")
	public void consume(Ville ville) throws InterruptedException {
		Thread.sleep(50);
		logger.info(String.format("Ville created -> %s", ville.toString()));
	}
}
