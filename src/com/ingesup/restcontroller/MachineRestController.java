package com.ingesup.restcontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.mastere.dto.MachineDto;

@RestController
public class MachineRestController {
	
	@RequestMapping(value="/rest/Machine", method = RequestMethod.GET)
	public ResponseEntity<?> machineGet(HttpServletRequest request){
		
		System.out.println("MACHINE GET");
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/Machine", method = RequestMethod.DELETE)
	public ResponseEntity<?> machineDelete(MachineDto.DeleteInput input, HttpServletRequest request){
		
		System.out.println("MACHINE DELETE");
		
		
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	

}
