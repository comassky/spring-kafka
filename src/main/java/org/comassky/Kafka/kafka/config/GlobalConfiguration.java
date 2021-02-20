package org.comassky.Kafka.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class GlobalConfiguration {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${villes.topic.group.name")
	private String villesTopicName;

	@Value(value = "${personnes.topic.group.name}")
	private String personnesTopicName;

	@Bean
	public NewTopic villesTopic() {
		return TopicBuilder.name(villesTopicName)
				.partitions(2)
				.replicas(2)
				.build();
	}

	@Bean
	public NewTopic personnesTopic() {
		return TopicBuilder.name(personnesTopicName)
				.partitions(2)
				.replicas(3)
				.build();
	}
}
