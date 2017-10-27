package com.ingesup.controller;

import java.util.List;
import com.google.gson.Gson;
import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.filter.HibernateFilter;
import com.ingesup.manager.ParkManager;
import com.ingesup.manager.UserManager;
import com.ingesup.model.Park;

//@WebServlet(urlPatterns = "/park")
public class ParkController extends HttpServletUtils{

	public void park() {
		
		// 1. Validating the user
		if(!HibernateFilter.isActiveSession(this.request))
			this.sendError(401);
		
		List<Park> parkList = null;
		
		// 2. GET Method to display view
		if(this.request.getMethod().toUpperCase().equals("GET")) {
			
			// 2.1 Getting user email
			String mailUser = HibernateFilter.getCookieEmail(this.request);
			
			// 2.2 Loading park(s) list
			parkList = ParkManager.getParkListWithUserId(UserManager.getUser(mailUser).getId());
			
			// 2.3 Adding to jsp model
			this.request.setAttribute("parkList", new Gson().toJson(parkList));
	
		}
		
		this.displayView();
		
		return;
	}
}
