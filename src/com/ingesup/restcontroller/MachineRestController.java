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

import com.ingesup.dto.MachineDto;

@RestController
public class MachineRestController {
	
	/**
	 * GET Method to return the whole list of machine attached to the given park id
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/rest/Machine", method = RequestMethod.GET)
	public ResponseEntity<?> machineGet(@RequestParam("name") String name){
		
		System.out.println("MACHINE GET");
		System.out.println(name);
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	/**
	 * POST Method to switch a machine of park
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/rest/Machine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> machinePost(@RequestParam("name") String name){
		
		//TODO: moove room for machine
		System.out.println("MACHINE POST");
		System.out.println(name);
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	/**
	 * DELETE Method to erase a machine from a room
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/rest/Machine", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> machineDelete(@RequestParam("name") String name){
		
		//TODO: delete machine from room
		System.out.println("MACHINE DELETE");
		System.out.println(name);
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

}
