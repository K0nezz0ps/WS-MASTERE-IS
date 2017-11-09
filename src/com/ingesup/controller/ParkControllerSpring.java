package com.ingesup.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;
import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.dto.HistoryUpdateDto;
import com.ingesup.dto.ParkListDto;
import com.ingesup.dto.ParkListDto.GetOutput.Alert;
import com.ingesup.hibernate.HistoryManager;
import com.ingesup.hibernate.MachineManager;
import com.ingesup.hibernate.ParkManager;
import com.ingesup.hibernate.RoomManager;
import com.ingesup.hibernate.UserManager;
import com.ingesup.dto.RoomDto;
import com.ingesup.model.History;
import com.ingesup.model.Machine;
import com.ingesup.model.Park;
import com.ingesup.model.Room;
import com.ingesup.state.ComponentState;

@Controller
public class ParkControllerSpring {
	
	/**
	 * Display the general view with all park attached to the connected user
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/park", method = RequestMethod.GET)
	public String park(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		// 1. Verifying that the current user is connected
		if(!ControllerUtils.isValidUser(request)){
			ControllerUtils.redirect("/WS-CNS-AUTH/login", response);
			return null;
		}
		
		// 2. Getting all park that the current user can see
		List<Park> parkList = ParkManager.getParkListWithUserId(UserManager.getUser(ControllerUtils.getCookieEmail(request)).getId());
		
		List<ParkListDto.GetOutput> outputParkList = new ArrayList<>();
		
		// 3. Import the most recent event for each machine
		List<History> recentList = new ArrayList<>();
		recentList = HistoryManager.getRecentList();
		
		// 4. ForEach park, getting the rooms objects attached
		for(Park currentPark : parkList){

			List<Room> currentRoomList = RoomManager.getAllRoom(currentPark.getId());
			List<Alert> currentParkRecentAlert = new ArrayList<>();
		
			// 4.1 Loading all the machine attached to the current park
			List<Machine> currentMachineList = MachineManager.getAll(currentPark.getId());
			
			// 4.2 Creating an Alert object for each machine History where any ComponentState > Average
			recentList.stream().forEach(x -> { 
				
				for(Machine currentMachine : currentMachineList){
					if(x.getId_machine() == currentMachine.getId()){
						
						ComponentState cpuState = ComponentState.forValue(x.getCpuState());
						ComponentState ramState = ComponentState.forValue(x.getRamState());
						
						// 4.2.1 In case of higher ComponentState than RAISED, create an alert
						if(cpuState.equals(ComponentState.ALERT) || ramState.equals(ComponentState.ALERT))
							currentParkRecentAlert.add(new Alert(currentMachine.getMachineIp(), currentRoomList.stream().filter(y -> y.getId() == currentMachine.getId_room()).findFirst().orElse(null) , currentPark));
						
					}
				}
				
			});
			
			outputParkList.add(new ParkListDto.GetOutput(currentPark, currentRoomList, currentParkRecentAlert));
		}
			

		// 5. Adding the park List to the attribute
		model.addAttribute("parkList", new Gson().toJson(outputParkList));
		
		// 6. Null values here (to match with the JSP), because they are not required but potentially used by the JS
        model.addAttribute("room", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("historyList", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("roomList", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("currentPark", new Gson().toJson(new ArrayList<>()));
        
        model.addAttribute("pageTitle", "Park Monitor");
        model.addAttribute("autoRefresh", false);
		
        return "park";
	}
	
	/**
	 * Display the park view with all the values from the given parkId
	 * @param parkId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/park/{parkId}", method = RequestMethod.GET)
    public String park(@PathVariable Integer parkId, Model model, HttpServletRequest request, HttpServletResponse response) {

		// 1. Loading the requested park object
		Park currentPark = ParkManager.getPark(parkId);
		
		// 2. In case of null park (then wrong @param entry, or inexistent park id)
		if(currentPark == null){
			ControllerUtils.sendError(400, response);
			return null;
		}
		
		// 3. Get all room attached to the given parkId
		List<Room> roomList = RoomManager.getAllRoom(parkId);
		
		// 4. Get all Machine from room ids list
		List<Machine> machineList = MachineManager.getAllByRoomIds(roomList.stream().map(Room::getId).collect(Collectors.toList()));
		
		// 5. Mapping all machine per room (Integer = roomId, Machine = Machine object)
		Map<Integer, List<Machine>> machineMap = new HashMap<>();
		
		for(Machine currentMachine : machineList){
			
			if(!machineMap.containsKey(currentMachine.getId_room()))
				machineMap.put(currentMachine.getId_room(), new ArrayList<Machine>());

			machineMap.get(currentMachine.getId_room()).add(currentMachine);
		}
		
		// 6. Creating outputList
		List<RoomDto.GetOutput> outputList = new ArrayList<>();
		
		// 6.1 Adding values to the outputList
		roomList.stream().forEach(x -> { outputList.add(new RoomDto.GetOutput(x.getId(), x.getName(), x.getId_park(), machineMap.get(x.getId()))); });

		// 7. Adding model attributes
		model.addAttribute("currentPark", new Gson().toJson(currentPark));
		model.addAttribute("roomList", new Gson().toJson(outputList));
		
		// 8. Null values here (to match with the JSP), because they are not required but potentially used by the JS
		model.addAttribute("parkList", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("room", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("historyList", new Gson().toJson(new ArrayList<>()));
        
        model.addAttribute("pageTitle", "Rooms Overview");
        model.addAttribute("autoRefresh", false);
	
		return "parkMain";
		
    }
	
	/**
	 * Display the room view
	 * @param parkId
	 * @param roomId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/park/{parkId}/{roomId}", method = RequestMethod.GET)
    public String room(@PathVariable Integer parkId, @PathVariable Integer roomId, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		// 1. Get the requested room
		Room currentRoom = RoomManager.get(roomId);
		
		// 2. In case of null room (then wrong @param entry, or inexistent room id)
		if(currentRoom == null){
			ControllerUtils.sendError(400, response);
			return null;
		}
		
		// 3. Load all machine for the current room
		List<Machine> machineList = MachineManager.getAllByRoomIds(Arrays.asList(currentRoom.getId()));
		
		List<HistoryUpdateDto.GetOutput> recentHistoryList = new ArrayList<>();
		
		for(History currentHistory : HistoryManager.getRecentList())
			recentHistoryList.add(new HistoryUpdateDto.GetOutput(currentHistory.getId_machine(), null, null, currentHistory.getDateEvent(), null , ComponentState.forValue(currentHistory.getCpuState()), ComponentState.forValue(currentHistory.getRamState()), null));

		if(recentHistoryList.isEmpty()){
			for(Machine currentMachine : machineList){
				
				recentHistoryList.add(new HistoryUpdateDto.GetOutput(null, currentMachine.getCpu(), currentMachine.getRam(), null, currentMachine.getMachineIp(), null, null, null));
			}
		}
		else {
			// 4. Parsing values for the output
			for(Machine currentMachine : machineList){
				recentHistoryList.stream().forEach(x -> {
					if(x.getId().equals(currentMachine.getId())){
						x.setCpu(currentMachine.getCpu());
						x.setRam(currentMachine.getRam());
						x.setId(currentMachine.getId());
						x.setMachineIp(currentMachine.getMachineIp() );
					}
				});
			}
		}

		// 5. Getting all rooms of the parkId
		List<Room> roomList = RoomManager.getAllRoom(parkId);
		roomList.remove(currentRoom);
		
		// 6. Adding the GetOutput to the JSP Model
		model.addAttribute("room", new Gson().toJson(new RoomDto.GetOutput(currentRoom.getId(), currentRoom.getName(), currentRoom.getId_park(), machineList)));
		model.addAttribute("historyList", new Gson().toJson(recentHistoryList));
		model.addAttribute("roomList", new Gson().toJson(roomList));
		
		// 7. Null values here (to match with the JSP), because they are not required but potentially used by the JS
		model.addAttribute("currentPark", new Gson().toJson(new ArrayList<>()));
		model.addAttribute("parkList", new Gson().toJson(new ArrayList<>()));
		
        model.addAttribute("pageTitle", "Room Manager");
        model.addAttribute("autoRefresh", true);
		
		return "roomProfile";
		
    }

}
