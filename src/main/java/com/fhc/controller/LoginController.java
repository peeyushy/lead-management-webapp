package com.fhc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fhc.constants.AppConstants;
import com.fhc.service.LeadService;
import com.fhc.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	LeadService leadService;

	@GetMapping({ "/", "/index" })
	public String root() {
		return "index";
	}

	@RequestMapping(value = "/home", method = { RequestMethod.POST, RequestMethod.GET })
	public String showWelcomePage(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Principal type: " + auth.getPrincipal().getClass().getName());
		model.put("name", userService.getLoggedinUserObj().getFullname());
		if (userService.getLoggedinUserObj().getRole().equals(AppConstants.RoleNames.ADMIN)) {
			model.put("leads", leadService.getAllLeads());
		} else {
			// Executive & TL's get only leads assigned to them handled in api
			model.put("leads", leadService.getAllLeadsByAssignedUserId(userService.getLoggedinUserObj().getId()));
		}
		return "welcome";
	}

}
