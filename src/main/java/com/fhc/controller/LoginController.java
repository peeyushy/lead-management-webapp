package com.fhc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
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
		} else if (userService.getLoggedinUserObj().getRole().equals(AppConstants.RoleNames.TEAM_LEADER)) {
			model.put("leads", leadService.getAllLeadsForTL(userService.getLoggedinUserObj().getId()));
		} else {
			//Executive gets only leads assigned to them
			List<Long> userids=new ArrayList<>(); 
			userids.add(userService.getLoggedinUserObj().getId());
			model.put("leads", leadService.getAllLeadsByAssignee(userids));
		}
		/*List<String> openAndQuotedstatusLst = new ArrayList<String>();
		openAndQuotedstatusLst.add(ReqStatus.OPEN.toString());
		openAndQuotedstatusLst.add(ReqStatus.QUOTED.toString());
		model.put("openandquotedrequirements", reqService.getAllLeadsByStatusIn(openAndQuotedstatusLst));
		model.put("reqTypeMap", AppConstants.getReqTypeMap());*/
		
		return "welcome";
	}

}
