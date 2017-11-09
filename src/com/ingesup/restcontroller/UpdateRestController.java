package com.ingesup.restcontroller;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.hibernate.DisksManager;
import com.ingesup.hibernate.HistoryManager;
import com.ingesup.hibernate.MachineManager;
import com.ingesup.model.Disks;
import com.ingesup.model.History;
import com.ingesup.model.Machine;

@RestController
public class UpdateRestController {

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public ResponseEntity<?> updatePost(HttpServletRequest request, HttpServletResponse response) {

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
	    	e.printStackTrace();
	    }

//		// 1. Mapping all input data
//		HistoryUpdateData dataParams = MapperUtils.historyUpdateDataMapper(this.request.getParameterMap());
//		
		// 2. Getting the attached machine to the IP in param
		Machine currentMachine = MachineManager.getByIp(ip);
		
		// 3. If the machine does'nt exist, ignore
		if(currentMachine == null)
			return new ResponseEntity<>("No machine with the IP : " + ip, HttpStatus.NOT_FOUND);
		
		// 4. Creating a new History line
		History historyItem = new History();
		//historyItem.setMachineId(currentMachine.getId());
		historyItem.setDateEvent(new Date());
		historyItem.setId_machine(currentMachine.getId());
		historyItem.setCpuState(cpu);
		historyItem.setRamState(ram);
		
		
		// 5. Saving the new history line in database
		HistoryManager.create(historyItem);
		
		//------------------------------------------
		List<String> disks=new ArrayList<>();
		List<Disks> listdisks=new ArrayList<>();
		
		int id=HistoryManager.getlastidhistory((historyItem.getId_machine()));
		
		disks=Arrays.asList(disk.split(" "));
		for (String d : disks) {
			listdisks.add(new Disks(id,d.split("_")[0],Double.parseDouble(d.split("_")[1])));
		}
		for (Disks disks2 : listdisks) {
			DisksManager.create(disks2);
		}
		//------------------------------------------
		//historyItem.setStorageState(ComponentState.forValue(dataParams.getStoragePercentage()));
		
		
		return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
}
