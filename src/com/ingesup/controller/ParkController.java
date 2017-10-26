package com.ingesup.controller;

import java.util.List;

import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.filter.HibernateFilter;
import com.ingesup.manager.ParkManager;
import com.ingesup.model.Park;
import com.ingesup.model.User;

public class ParkController extends HttpServletUtils{

	public void park() {
		
		
		List<Park> parkList = null;
		if(this.request.getMethod().toUpperCase().equals("GET")) {
			String mailUser = HibernateFilter.getCookieEmail(this.request);
			User user = ParkManager.getUser(mailUser);
			System.out.println(user.getId());
			parkList = ParkManager.getParkListWithUserId(user.getId());
	
		}
		this.displayView(parkList);
		return;
	}
}
