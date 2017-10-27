package com.ingesup.controller;

import java.util.List;
import java.util.Map.Entry;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import com.google.gson.Gson;
import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.filter.HibernateFilter;
import com.ingesup.manager.MachineManager;
import com.ingesup.model.Machine;

@WebServlet(urlPatterns = "/machine")
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
		
		// 3. DELETE function to erase a Machine
		if(this.request.getMethod().toUpperCase().equals("DELETE")){
			this.machineDelete();
			return;
		}
		
		// 3. Throw a 404 in case of no implemented getMethod();
		this.sendError(404, "Not implemented yet.");
		
	}
	
	/**
	 * Return the whole list of machine for the given park id
	 */
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
			
			// 2.1 If the selected mode is "all"
			if(selectMode.equals("all")){
				
				// 2.1.1 Load all the Machine for the requested parkId
				List<Machine> machineList = MachineManager.getAll(parkId);
		
				if(machineList == null)
					// 2.1.2 Throw error in case of null List
					this.sendJsonResponse("Error while loading the machine list.");
				else
					// 2.1.2 Send response as JSON
					this.sendJsonResponse(new Gson().toJson(machineList));
			}
			
		}
		
	}

	/**
	 * Delete a Machine from the given park id
	 */
	public void machineDelete() {
		
		Integer parkId = null;
		
		for(Entry<String, String[]> currentAttribute : this.request.getParameterMap().entrySet()){
			if(currentAttribute.getKey().equals("parkId"))
				parkId = Integer.valueOf(currentAttribute.getValue()[0]);
		}
		
	}
}
