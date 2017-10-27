package com.ingesup.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.model.History;
import com.ingesup.state.ComponentState;

@WebServlet(name="MonitorController", urlPatterns = "/monitor")
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
//		List<History> recentList = HistoryManager.getRecentList();
		
		// PROTO: Static list
		List<History> recentList = new ArrayList<>();
		/*recentList.add(new History(null, new Date(), 2, ComponentState.HEAVEN	, ComponentState.VERYGOOD	, ComponentState.HEAVEN));
		recentList.add(new History(null, new Date(), 3, ComponentState.ALERT	, ComponentState.RAISED		, ComponentState.ALERT));
		recentList.add(new History(null, new Date(), 4, ComponentState.ALERT	, ComponentState.VERYGOOD	, ComponentState.RAISED));
		recentList.add(new History(null, new Date(), 5, ComponentState.HEAVEN	, ComponentState.RAISED		, ComponentState.HEAVEN));
		recentList.add(new History(null, new Date(), 6, ComponentState.VERYGOOD	, ComponentState.HEAVEN		, ComponentState.VERYGOOD));*/
		
		if(recentList == null)
			return;
		
		this.request.setAttribute("historyList", recentList);
		
		// 2. Displaying the monitor interface
		this.displayView();
		
	}

}
