package org.comassky.Kafka.services;

import kotlin.ranges.IntRange;
import org.apache.commons.lang3.RandomStringUtils;
import org.comassky.Kafka.dto.Personne;
import org.comassky.Kafka.kafka.producer.PersonneProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Service
public class PersonneServices {

	@Autowired
	private PersonneProducerService personneProducerService;

	public void generatePersonnes(final Integer nombre){
		IntStream.range(1,nombre).forEach( index -> {
			final Personne personne = new Personne(
					RandomStringUtils.random(50, true, true),
					RandomStringUtils.random(50, true, true),
					LocalDate.now(),
					index,
					180F
			);
			this.personneProducerService.sendMessage(personne);
		});
	}
}
