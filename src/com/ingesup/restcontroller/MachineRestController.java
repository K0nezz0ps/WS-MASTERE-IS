package com.ingesup.restcontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.dto.MachineCreate;
import com.ingesup.hibernate.EntityManager;
import com.ingesup.hibernate.MachineManager;
import com.ingesup.model.Machine;
import com.ingesup.model.Room;

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
	@RequestMapping(value="/rest/MachineCreate", method = RequestMethod.POST)
	public ResponseEntity<?> switchMachine(@RequestBody MachineCreate.PostInput input, HttpServletRequest request){
		
		// 1. Validating current user
		if(!ControllerUtils.isValidUser(request))
			return new ResponseEntity<>("Not permitted.", HttpStatus.UNAUTHORIZED);
		
		Integer lastMachine = MachineManager.getLast();
		
		if(lastMachine == null)
			return new ResponseEntity<>("Can't reach the last machine.", HttpStatus.NOT_FOUND);
		
		Machine newMachine = new Machine();
		newMachine.setId(lastMachine + 1);
		newMachine.setMachineIp(input.getMachineIp());
		newMachine.setCpu(Float.valueOf(input.getMachineCpu()));
		newMachine.setRam(Float.valueOf(input.getMachineRam()));
		newMachine.setId_room(input.getRoomId());
		
		MachineManager.create(newMachine);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
 