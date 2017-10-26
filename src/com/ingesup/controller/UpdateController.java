package com.ingesup.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;

import com.ingesup.controller.utils.MapperUtils;
import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.data.HistoryUpdateData;
import com.ingesup.manager.HistoryManager;
import com.ingesup.manager.MachineManager;
import com.ingesup.model.History;
import com.ingesup.model.Machine;
import com.ingesup.state.ComponentState;

public class UpdateController extends HttpServletUtils {
	
	/**
	 * Update method dispatcher
	 */
	public void update() {
		
		// 1. POST: in case of registering a new history
		if(this.request.getMethod().toUpperCase().equals("POST")) {
			System.out.println("data");
			this.updatePost();
		}
		// 2. Else, throw a 404 Error
		else
			this.sendError(404, "Not implemented yet.");

	}

	/**
	 * Update method that will register in database the computer history
	 */
	public void updatePost() {
		
	    double cpu=0;
        double ram = 0;
        String disk = "";
		String ip=request.getRemoteAddr();

		//System.out.println(request.getRemoteHost());
		try{
	        StringBuilder sb = new StringBuilder();
	        BufferedReader br = request.getReader();
	        String str = null;
	        while ((str = br.readLine()) != null) {
	            sb.append(str);
	            System.out.println(str);
	        }
	        JSONObject jObj = new JSONObject(sb.toString());
	         cpu = jObj.getDouble("Cpu");
	         ram = jObj.getDouble("Ram");
	         disk = jObj.getString("Disk");

	    } catch (Exception e) {
	    	
	    }
		
		
		
		//-------------------------------------
		
		
//		// 1. Mapping all input data
//		HistoryUpdateData dataParams = MapperUtils.historyUpdateDataMapper(this.request.getParameterMap());
//		
		// 2. Getting the attached machine to the IP in param
		Machine currentMachine = MachineManager.getByIp(ip);
		
		// 3. If the machine does'nt exist, ignore
		if(currentMachine == null){
			System.out.println("No machine with the IP : " +ip);
			return;
		}
		
		// 4. Creating a new History line
		History historyItem = new History();
		//historyItem.setMachineId(currentMachine.getId());
		
		historyItem.setDateEvent(new Date());
		historyItem.setId_machine(currentMachine.getId());
		historyItem.setCpuState(ComponentState.forValue(cpu));
		historyItem.setRamState(ComponentState.forValue(ram));
		historyItem.setStorageState(disk);
		
		System.out.println(disk);
		//historyItem.setStorageState(ComponentState.forValue(dataParams.getStoragePercentage()));
		
		// 5. Saving the new history line in database
		HistoryManager.create(historyItem);
		
		// 6. Return a 201 (Created) status
		try {
			this.response.sendError(201);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
