package com.ingesup.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.filter.HibernateFilter;

public class MachineController extends HttpServletUtils {
	
	public void machine() {
		
		// 1. Validating the user
		if(!HibernateFilter.isActiveSession(this.request))
			this.sendError(401);
		
		// 2. GET function to access Machine elements in db
		if(this.request.getMethod().toUpperCase().equals("GET")){
			this.machineGet();
			return;
		}
		
		// 3. Throw a 404 in case of no implemented getMethod();
		this.sendError(404, "Not implemented yet.");
		
	}
	
	public void machineGet() {
		
		// 1. Getting both parameters for this request
		String selectMode = null;
		Integer parkId    = null;
		
		for(Entry<String, String[]> currentAttribute : this.request.getParameterMap().entrySet()){
			if(currentAttribute.getKey().equals("parkId"))
				parkId = Integer.valueOf(currentAttribute.getValue()[0]);
			else if(currentAttribute.getKey().equals("selectMode"))
				selectMode = currentAttribute.getValue()[0];
		}
		
		// 2. If valid input
		if(parkId != null && selectMode != null){
			
			// 2.1 
			if(selectMode.equals("all")){
				
				
			}
			
		}
		
	}

}
