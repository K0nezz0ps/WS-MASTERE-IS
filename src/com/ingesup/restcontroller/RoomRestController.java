package com.ingesup.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.hibernate.HibernateUtilMastere;
import com.ingesup.hibernate.RoomManager;
import com.ingesup.model.Room;

@RestController
public class RoomRestController {
	
	@RequestMapping(value="/rest/RoomDelete", method=RequestMethod.POST)
	public ResponseEntity<?> deleteRoom(@RequestParam Integer roomId, HttpServletRequest request){
		
		// 1. Validating current user
		if(!ControllerUtils.isValidUser(request))
			return new ResponseEntity<>("Not permitted.", HttpStatus.UNAUTHORIZED);
		
		// 2. Get the requested room
		Room currentRoom = RoomManager.get(roomId);
		if(currentRoom == null)
			return new ResponseEntity<>("Room does'nt exist.", HttpStatus.NOT_FOUND);
		
		// 3. Delete the room then return 200 status
		RoomManager.delete(roomId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/RoomCreate", method = RequestMethod.POST)
	public ResponseEntity<?> createRoom(@RequestParam String roomName, @RequestParam Integer idPark, HttpServletRequest request){
		
		// 1. Validating user
		if(!ControllerUtils.isValidUser(request))
			return new ResponseEntity<>("Not permitted.", HttpStatus.UNAUTHORIZED);
		
		// 2. Create the room
		Room newRoom = new Room();
		newRoom.setId_park(idPark);
		newRoom.setName(roomName);
		
		// 3. Hibernate create + clean
		HibernateUtilMastere.getSession().save(newRoom);
		HibernateUtilMastere.cleanHibernateExchange();
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
