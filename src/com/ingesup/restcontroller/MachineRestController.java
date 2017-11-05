package com.ingesup.restcontroller;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.dto.MachineSwitchDto;
import com.ingesup.hibernate.EntityManager;
import com.ingesup.hibernate.MachineManager;
import com.ingesup.model.Machine;

@RestController
public class MachineRestController {

	/**
	 * POST Method to switch a machine of park
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/rest/Machine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> machinePost(@RequestParam("targetRoomId") Integer targetRoomId,@RequestParam("machineId") String machineId){

		// 1. Getting the requested machine
		Machine m = MachineManager.getById(Integer.parseInt(machineId));
		
		// 2. Updating roomId
		m.setId_room(targetRoomId);
		EntityManager.update(m);
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	/**
	 * DELETE Method to erase a machine from a room
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/rest/Machine", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> machineDelete(@RequestParam("machineId") String machineId){
		
		// 1. Getting the requested machine
		Machine m = MachineManager.getById(Integer.parseInt(machineId));
		
		// 2. Deleting it
		EntityManager.delete(m);
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	/**
	 * Switch from room the given list of machine & room ids
	 * @param input
	 * @return
	 */
	@RequestMapping(value="/rest/Machine/switch", method = RequestMethod.POST)
	public ResponseEntity<?> switchMachine(@RequestBody MachineSwitchDto.PostInput input){
		
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
