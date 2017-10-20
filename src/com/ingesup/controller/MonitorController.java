package com.ingesup.controller;

import java.util.List;
import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.manager.HistoryManager;
import com.ingesup.model.History;

public class MonitorController extends HttpServletUtils {
	
	public void monitor() {
		
		// 1. GET: request to load all recent history data and display on the monitor interface
		if(this.request.getMethod().toUpperCase().equals("GET"))
			this.monitorGet();
		// 2. Else, throw a 404 Error
		else
			this.sendError(404, "Not implemented yet.");
	}
	
	/**
	 * Load the recent history list and display the JSP
	 */
	public void monitorGet(){
		
		// 1. Loading the most recent list history
		List<History> recentList = HistoryManager.getRecentList();
		
		if(recentList == null)
			return;
		
		this.request.setAttribute("historyList", recentList);
		
		// 2. Displaying the monitor interface
		this.displayView();
		
	}

}
