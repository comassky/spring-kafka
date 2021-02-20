package org.comassky.Kafka.kafka.producer;

import org.comassky.Kafka.dto.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class PersonneProducerService {

	private static final Logger logger =  LoggerFactory.getLogger(PersonneProducerService.class);

	@Value(value = "${personnes.topic.group.name}")
	private String personneTopicName;

	@Autowired
	private KafkaTemplate<String, Personne> personneKafkaTemplate;

	public void sendMessage(final Personne personne){
		ListenableFuture<SendResult<String, Personne>> future
				= this.personneKafkaTemplate.send(personneTopicName, personne);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Personne>>() {
			@Override
			public void onSuccess(SendResult<String, Personne> result) {
				logger.info("Personne created: "
						+ personne + " with offset: " + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Personne created : " + personne, ex);
			}
		});
	}
}
