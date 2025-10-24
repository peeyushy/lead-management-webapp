package com.fhc.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fhc.constants.AppConstants;
import com.fhc.dto.QuoteDto;
import com.fhc.model.Lead;
import com.fhc.model.LeadNote;
import com.fhc.model.User;
import com.fhc.service.LeadService;
import com.fhc.service.UserService;

import jakarta.validation.Valid;

@Controller
public class LeadController {

	private static final Logger log = LoggerFactory.getLogger(LeadController.class);
	
	@Autowired
	UserService userService;

	@Autowired
	LeadService leadService;
	
	@GetMapping("/leadnotes/{leadid}")
	public ResponseEntity<List<LeadNote>> getLeadNotes(@PathVariable Long leadid) {
	    List<LeadNote> notes = leadService.getAllNotesByLeadId(leadid);
	    return ResponseEntity.ok(notes);
	}

	@RequestMapping(value = "/leads", method = RequestMethod.GET)
	public String getLeads(ModelMap model) {
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

		return "findleads";
	}

	
	@RequestMapping(value = "/deletelead", method = RequestMethod.GET)
	public String deleteLead(ModelMap model, @RequestParam String leadid, final RedirectAttributes redirectAttributes) {
		leadService.deleteLead(leadid);
		redirectAttributes.addFlashAttribute("msg", "Lead deleted successfully!");
		return "redirect:/lead";
	}

	@RequestMapping(value = "/addlead", method = RequestMethod.GET)
	public String getAddLeadPage(ModelMap model) {
		model.put("action", "Add");
		model.put("statusMap", AppConstants.LeadStatus.getStatusMap());		
		model.put("usersMap", userService.getAllUsersMap());
		model.put("lead", new Lead(AppConstants.LeadStatus.HOT));
		return "lead";
	}

	@RequestMapping(value = "/addlead", method = RequestMethod.POST)
	public String postAddleadPage(ModelMap model, @Valid Lead lead, BindingResult result,
	                              final RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	        model.put("action", "Add");
	        // Add any additional model attributes if needed for the form
	        return "lead"; // Return to lead form with errors shown
	    } else {
	        User currentUser = userService.getLoggedinUserObj();	        
	        lead.setCreatedByUserId(currentUser.getId());
	        lead.setUpdatedByUserId(currentUser.getId());
	        lead.setStatus(lead.getStatus());
	        lead.setCreatedAt(LocalDateTime.now());
	        lead.setUpdatedAt(LocalDateTime.now());
	        
	        leadService.addLead(lead);
	        
	        redirectAttributes.addFlashAttribute("msg", "Lead added successfully!");
	        return "redirect:/leads"; // Redirect after success to avoid form resubmission
	    }
	}

	@RequestMapping(value = "/editlead", method = RequestMethod.GET)
	public String getEditLeadPage(ModelMap model, @RequestParam String leadid) {
		Lead lead = leadService.getLeadById(leadid);
		List<QuoteDto> quotesLst = new ArrayList<QuoteDto>();
		try {
			quotesLst = leadService.getLatestQuotesByReqId(leadid);
		} catch (NestedRuntimeException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.put("reqTypeMap", AppConstants.getReqTypeMap());
		//model.put("selectedReqType", req.getReqtype());

		model.put("lead", lead);
		model.put("quotes", quotesLst);
		if (lead.getStatus().equals(AppConstants.LeadStatus.DEAD)) {
			model.put("action", "Edit");
		} else {
			model.put("action", "View");
		}
		return "lead";
	}

	@RequestMapping(value = "/editload", method = RequestMethod.POST)
	public String postEditLoadPage(ModelMap model, @RequestParam String loadid, @Valid Lead req,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.put("reqTypeMap", AppConstants.getReqTypeMap());
			//model.put("selectedReqType", req.getReqtype());

			if (req.getStatus().equals(AppConstants.LeadStatus.DEAD)) {
				model.put("action", "Edit");
			} else {
				model.put("action", "View");
			}
			return "lead";
		} else {
			req.setUpdatedByUserId(userService.getLoggedinUserObj().getId());
			//req.setClient(clientService.getClient(reqService.getClientIdByReqId(reqid).toString()));
			// no status update from UI
			req.setStatus(leadService.getLeadById(loadid).getStatus());
			leadService.updateLead(loadid, req);
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("msg", "Lead updated successfully!");
			return "redirect:/load";
		}
	}
}
