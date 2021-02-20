package org.comassky.Kafka.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.comassky.Kafka.dto.Personne;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaPersonneConfiguration {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${personnes.topic.group.id}")
	private String personneGroupId;

	/***
	 * Consumer
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Personne> personneKafkaListener()
	{
		final Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, this.personneGroupId);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		final ConsumerFactory<String, Personne> consumer = new DefaultKafkaConsumerFactory<>(props,
				new StringDeserializer(),
				new JsonDeserializer<>(Personne.class));

		ConcurrentKafkaListenerContainerFactory<String, Personne> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumer);
		return factory;
	}

	/***
	 * Producer
	 */
	@Bean
	public KafkaTemplate<String, Personne> personneKafkaProducer() {
		final Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
	}
}
