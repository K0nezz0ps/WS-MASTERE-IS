package com.ingesup.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.ingesup.dto.RoomDto;
import com.ingesup.manager.MachineManager;
import com.ingesup.manager.ParkManager;
import com.ingesup.manager.RoomManager;
import com.ingesup.manager.UserManager;
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
		
//		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// 1. Verifying that the current user is connected
		if(!ControllerUtils.validateUser(request)){
			ControllerUtils.redirect("/WS-CNS-AUTH/login", response);
			return null;
		}
		
		// 2. Adding the park List to the attribute
		model.addAttribute("parkList", new Gson().toJson(ParkManager.getParkListWithUserId(UserManager.getUser(ControllerUtils.getCookieEmail(request)).getId())));
        model.addAttribute("room", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("historyList", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("roomList", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("currentPark", new Gson().toJson(new ArrayList<>()));
		
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
		model.addAttribute("parkList", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("room", new Gson().toJson(new ArrayList<>()));
        model.addAttribute("historyList", new Gson().toJson(new ArrayList<>()));
	
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
		recentHistoryList.add(new HistoryUpdateDto.GetOutput(null, null, null, new Date(), "192.0.0.9" , ComponentState.HEAVEN		, ComponentState.VERYGOOD	, ComponentState.HEAVEN));
		recentHistoryList.add(new HistoryUpdateDto.GetOutput(null, null, null, new Date(), "192.0.0.10", ComponentState.ALERT		, ComponentState.RAISED		, ComponentState.ALERT));
		recentHistoryList.add(new HistoryUpdateDto.GetOutput(null, null, null, new Date(), "192.0.0.11", ComponentState.ALERT		, ComponentState.VERYGOOD	, ComponentState.RAISED));
		recentHistoryList.add(new HistoryUpdateDto.GetOutput(null, null, null, new Date(), "192.0.0.45", ComponentState.HEAVEN		, ComponentState.RAISED		, ComponentState.HEAVEN));
		recentHistoryList.add(new HistoryUpdateDto.GetOutput(null, null, null, new Date(), "192.0.2.98", ComponentState.VERYGOOD	, ComponentState.HEAVEN		, ComponentState.VERYGOOD));
		
		// 4. Parsing values for the output
		for(Machine currentMachine : machineList)
			recentHistoryList.stream().forEach(x -> {
				if(x.getMachineIp().equals(currentMachine.getMachineIp())){
					x.setCpu(currentMachine.getCpu());
					x.setRam(currentMachine.getRam());
					x.setId(currentMachine.getId());
				}
			});
		
		// 5. Getting all rooms of the parkId
		List<Room> roomList = RoomManager.getAllRoom(parkId);
		roomList.remove(currentRoom);
		
		// 6. Adding the GetOutput to the JSP Model
		model.addAttribute("room", new Gson().toJson(new RoomDto.GetOutput(currentRoom.getId(), currentRoom.getName(), currentRoom.getId_park(), machineList)));
		model.addAttribute("historyList", new Gson().toJson(recentHistoryList));
		model.addAttribute("roomList", new Gson().toJson(roomList));
		model.addAttribute("currentPark", new Gson().toJson(new ArrayList<>()));
		model.addAttribute("parkList", new Gson().toJson(new ArrayList<>()));
		
		return "roomProfile";
		
    }

}
