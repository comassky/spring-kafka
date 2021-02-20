package org.comassky.Kafka.controller;

import org.comassky.Kafka.services.VilleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VilleController {

	@Autowired
	private VilleServices villeServices;

	@GetMapping(path = "/ville/{nombre}")
	public void generateVille (@PathVariable(required = true) final Integer  nombre) {
		this.villeServices.generateVilles(nombre);
	}

}
