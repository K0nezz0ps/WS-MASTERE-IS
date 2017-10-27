package com.ingesup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;
import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.manager.ParkManager;

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
		
		// 1. Validating the user
		if(!ControllerUtils.validateUser(request))
			ControllerUtils.redirect("/WS-CNS-AUTH/login", response);
		
		// 2. Adding the park List to the attribute
		model.addAttribute("parkList", new Gson().toJson(ParkManager.getParkListWithUserId(ParkManager.getUser(ControllerUtils.getCookieEmail(request)).getId())));
        
        return "/park/park";
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
    public String afficher(@PathVariable Integer parkId, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		// 1. Validating the user
		if(!ControllerUtils.validateUser(request))
			ControllerUtils.redirect("/WS-CNS-AUTH/auth/login", response);
		
		model.addAttribute("testModel", "hello, this is a example.");
		
		return "/park/parkMain";
		
    }

}
