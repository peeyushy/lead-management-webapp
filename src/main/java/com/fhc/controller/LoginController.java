package com.fhc.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fhc.constants.AppConstants;
import com.fhc.dto.LeadDto;
import com.fhc.model.User;
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
	public String getLeads(ModelMap model) {
	    User loggedInUser = userService.getLoggedinUserObj();
	    List<LeadDto> leadDtos;

	    if (loggedInUser.getRole().equals(AppConstants.RoleNames.ADMIN)) {
	        leadDtos = leadService.getAllLeads();
	    } else if (loggedInUser.getRole().equals(AppConstants.RoleNames.TEAM_LEADER)) {
	        leadDtos = leadService.getAllLeadsByAssignedUserId(loggedInUser.getId());
	    } else {
	        // Executives get their assigned leads as well
	        leadDtos = leadService.getAllLeadsByAssignedUserId(loggedInUser.getId());
	    }

	    Map<Long, String> userIdNameMap = userService.getAllUsers().stream()
	        .collect(Collectors.toMap(User::getId, User::getFullname));
	    
	    for (LeadDto leadDto : leadDtos) {
	        leadDto.resolveAssignedUserName(userIdNameMap);
	    }

	    model.put("leads", leadDtos);
	    return "findleads";
	}

}
