package com.fhc.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.fhc.dto.RequirementDto;
import com.fhc.service.ClientService;
import com.fhc.service.LeadService;
import com.fhc.service.UserService;

@Controller
public class NoAuthController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NoAuthController.class);
	
	@Autowired
	UserService userService;

	@Autowired
	LeadService reqService;

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/noauth", method = RequestMethod.GET)
	public String showPublicRequirementPage(ModelMap model, @RequestParam String cid) {
		try {
			//model.put("openandquotedrequirements", reqService.getAllOpenAndQuotedReqsForClient(cid));
			model.put("cid", cid);
		} catch (NestedRuntimeException e) {
			e.printStackTrace();
			LOGGER.error("Exception occured while displaying requirements to client {} : {} ",cid,e);
		}
		return "noauth/publicrequirements";
	}

	@RequestMapping(value = "/noauth/sendquotes", method = RequestMethod.GET)
	public String showGetQuotesPage(ModelMap model, @RequestParam String cid, @RequestParam String reqid) {
		RequirementDto requirement;
		try {
			requirement = reqService.getReqDetailsByClientIdAndReqId(cid, reqid);
			model.put("requirement", requirement);
			model.put("cid", cid);
		} catch (RestClientException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "noauth/sendquote";
	}

}
