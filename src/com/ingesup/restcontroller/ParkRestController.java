package com.ingesup.restcontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.dto.ParkListDto;
import com.ingesup.hibernate.EntityManager;
import com.ingesup.manager.ParkManager;
import com.ingesup.model.Park;
import com.ingesup.model.Room;

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
		
		ParkListDto.GetOutput output = new ParkListDto.GetOutput(ParkManager.get(parkName), new ArrayList<Room>());
		
		return new ResponseEntity<>(output ,HttpStatus.CREATED);
		
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

	/**
	 * DELETE Function to delete a Park
	 * @param parkId
	 * @return
	 */
	@RequestMapping(value="/rest/ParkDelete", method=RequestMethod.POST)
	public ResponseEntity<?> deletePark(@RequestParam("parkId") Integer parkId, HttpServletRequest request){
		
		// 1. Validating user
		if(!ControllerUtils.validateUser(request))
			return new ResponseEntity<>("You does'nt have access to this action.",HttpStatus.UNAUTHORIZED);
		
		// 2. Getting request park by Id
		Park currentPark = ParkManager.getPark(parkId);
		if(currentPark == null)
			return new ResponseEntity<>("This park does'nt exist",HttpStatus.NOT_FOUND);
		
		// 3. Deleting the park
		EntityManager.delete(currentPark);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
