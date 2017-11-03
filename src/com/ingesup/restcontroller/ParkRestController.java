package com.ingesup.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.hibernate.EntityManager;
import com.ingesup.manager.ParkManager;
import com.ingesup.model.Park;

@RestController
public class ParkRestController {
	
	/**
	 * POST Function to create a Park
	 * @param parkName
	 * @return
	 */
	@RequestMapping(value="/rest/createPark", method= RequestMethod.POST)
	public ResponseEntity<?> createPark(@RequestParam("parkName") String parkName){
		
		// 1. Trying to get a potential park with the same given parkName
		Park testPark = ParkManager.get(parkName);
		
		// 2. If this name is already used, return error
		if(testPark != null)
			return new ResponseEntity<>("This name is already used.", HttpStatus.INTERNAL_SERVER_ERROR);
		
		// 3. Else, create the park
		Park newPark = new Park();
		newPark.setName(parkName);
		ParkManager.create(newPark);
		
		newPark = ParkManager.get(parkName);
		
		return new ResponseEntity<>(newPark ,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/rest/editPark", method= RequestMethod.POST)
	public void editPark(@RequestBody  String values){
		System.out.println(values);
//		// 1. Trying to get a potential park with the same given parkName
//		Park testPark = ParkManager.get(parkName);
//		
//		// 2. If this name is already used, return error
//		if(testPark != null)
//			return new ResponseEntity<>("This name is already used.", HttpStatus.INTERNAL_SERVER_ERROR);
//		
//		// 3. Else, create the park
//		Park newPark = new Park();
//		newPark.setName(parkName);
//		ParkManager.create(newPark);
//		
//		newPark = ParkManager.get(parkName);

		
	}

}
