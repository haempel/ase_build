package de.hse.swt.microservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hse.swt.microservice.model.Product;

@RestController
public class ProductServiceController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceController.class);
	
	private static Map<String, Product> productRepo = new HashMap<String, Product>();

	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
		
		Product steak = new Product();
		steak.setId("3");
		steak.setName("Steak");
		productRepo.put(steak.getId(), steak);
		
		Product beer = new Product();
		beer.setId("4");
		beer.setName("Beer");
		productRepo.put(beer.getId(), beer);
		
		Product wine = new Product();
		wine.setId("5");
		wine.setName("Wine");
		productRepo.put(wine.getId(), wine);
		
		Product monitor = new Product();
		monitor.setId("6");
		monitor.setName("Monitor");
		productRepo.put(monitor.getId(), monitor);
		
		Product aim-lecture = new Product();
		monitor.setId("7");
		monitor.setName("AIM-Lecture");
		productRepo.put(aim-lecture.getId(), aim-lecture);
		
		Product corona-test = new Product();
		monitor.setId("8");
		monitor.setName("Corona-Test");
		productRepo.put(corona-test.getId(), corona-test);

	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		
		LOGGER.info("Delete called");
		LOGGER.debug("Delete id={}", id);
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct() {	
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

}
