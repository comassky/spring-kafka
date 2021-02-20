package org.comassky.Kafka.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.comassky.Kafka.dto.Ville;
import org.comassky.Kafka.kafka.producer.VilleProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class VilleServices {

	@Autowired
	private VilleProducerService villeProducerService;

	public void generateVilles(final Integer nombre){
		IntStream.range(1,nombre).forEach(index -> {
			final Ville personne = new Ville(
					RandomStringUtils.random(50, true, true),
					index,
					RandomStringUtils.random(6, true, true),
					4546546L
			);
			this.villeProducerService.sendMessage(personne);
		});
	}
}
