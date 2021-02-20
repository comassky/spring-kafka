package org.comassky.Kafka.controller;

import org.comassky.Kafka.services.PersonneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonneController {

	@Autowired
	private PersonneServices personneServices;

	@GetMapping(path = "/personne/{nombre}")
	public void generatePersonne(@PathVariable(required = true) final Integer  nombre) {
		this.personneServices.generatePersonnes(nombre);
	}
}
