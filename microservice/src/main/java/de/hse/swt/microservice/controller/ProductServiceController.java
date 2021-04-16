package de.hse.swt.microservice.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
//		// Load the JDBC driver
//	    try {
//		    Connection connection = DriverManager.getConnection
//		      ("jdbc:mysql://localhost:3306/timemanagement" , "timeuser", "timeuser");
//		    LOGGER.info("Database connected");
//
//		     //Create a statement
//		    Statement statement = connection.createStatement();
//
//		    // Execute a statement
//		    ResultSet resultSet = statement.executeQuery
//		      ("select firstName, mi, lastName from Student where lastName "
//		        + " = 'Smith'");
//
//		    // Iterate through the result and print the student names
//		    while (resultSet.next())
//		      System.out.println(resultSet.getString(1) + "\t" +
//		        resultSet.getString(2) + "\t" + resultSet.getString(3));
//		    // Close the connection
//			   connection.close();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


	    

	
		
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

}
