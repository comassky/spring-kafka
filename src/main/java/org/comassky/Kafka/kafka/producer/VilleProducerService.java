package org.comassky.Kafka.kafka.producer;

import org.comassky.Kafka.dto.Personne;
import org.comassky.Kafka.dto.Ville;
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
public class VilleProducerService {

	private static final Logger logger =  LoggerFactory.getLogger(VilleProducerService.class);

	@Value(value = "${villes.topic.group.name}")
	private String villesTopicName;

	@Autowired
	private KafkaTemplate<String, Ville> villeKafkaTemplate;

	public void sendMessage(final Ville ville){
		ListenableFuture<SendResult<String, Ville>> future
				= this.villeKafkaTemplate.send(villesTopicName, ville);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Ville>>() {
			@Override
			public void onSuccess(SendResult<String, Ville> result) {
				logger.info("Ville created: "
						+ ville + " with offset: " + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Ville created : " + ville, ex);
			}
		});
	}
}
