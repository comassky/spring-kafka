package org.comassky.Kafka.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.comassky.Kafka.dto.Ville;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaVilleonfiguration {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${villes.topic.group.id}")
	private String villeGroupId;

	/***
	 * Consumer
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Ville> villeKafkaListener()
	{
		final Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, this.villeGroupId);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		final ConsumerFactory<String, Ville> consumer = new DefaultKafkaConsumerFactory<>(props,
				new StringDeserializer(),
				new JsonDeserializer<>(Ville.class));

		final ConcurrentKafkaListenerContainerFactory<String, Ville> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumer);
		return factory;
	}

	/***
	 * Producer
	 */
	@Bean
	public KafkaTemplate<String, Ville> villeKafkaProducer() {
		final Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
	}
}
