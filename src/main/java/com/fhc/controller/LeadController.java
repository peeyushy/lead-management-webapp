package com.fhc.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fhc.constants.AppConstants;
import com.fhc.dto.LeadDetailsDto;
import com.fhc.dto.LeadDto;
import com.fhc.dto.LeadNotesDto;
import com.fhc.model.Lead;
import com.fhc.model.LeadNote;
import com.fhc.model.User;
import com.fhc.service.LeadService;
import com.fhc.service.UserService;
import com.fhc.util.LeadMapper;

import jakarta.validation.Valid;

@Controller
public class LeadController {

	private static final Logger log = LoggerFactory.getLogger(LeadController.class);

	@Autowired
	UserService userService;

	@Autowired
	LeadService leadService;
	
	@GetMapping("/leads")
	public String getLeads(ModelMap model) {
	    User loggedInUser = userService.getLoggedinUserObj();
	    List<LeadDto> leadDtoList;

	    if (loggedInUser.getRole().equals(AppConstants.RoleNames.ADMIN)) {
	        leadDtoList = leadService.getAllLeads();
	    } else if (loggedInUser.getRole().equals(AppConstants.RoleNames.TEAM_LEADER)) {
	        leadDtoList = leadService.getAllLeadsByAssignedUserId(loggedInUser.getId());
	    } else {
	        // Executives
	        leadDtoList = leadService.getAllLeadsByAssignedUserId(loggedInUser.getId());
	    }

	    // Resolve assigned user names for the leads visible to this user
	    Map<Long, String> userIdNameMap = userService.getAllUsers().stream()
	        .collect(Collectors.toMap(User::getId, User::getFullname));

	    for (LeadDto leadDto : leadDtoList) {
	        leadDto.resolveAssignedUserName(userIdNameMap);
	    }

	    model.put("leads", leadDtoList);
	    return "findleads";
	}

	
	@GetMapping("/deletelead")
	public String deleteLead(ModelMap model, @RequestParam String leadid, final RedirectAttributes redirectAttributes) {
		leadService.deleteLead(leadid);
		redirectAttributes.addFlashAttribute("msg", "Lead deleted successfully!");
		return "redirect:/lead";
	}

	@GetMapping("/addlead")
	public String getAddLeadPage(ModelMap model) {
		model.put("action", "Add");
		model.put("statusMap", AppConstants.LeadStatus.getStatusMap());
		model.put("usersMap", userService.getAllUsersMap());
		model.put("lead", new Lead(AppConstants.LeadStatus.HOT));
		return "lead";
	}

	@PostMapping("/addlead")
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

	@GetMapping("/editlead")
	public String getEditLeadPage(ModelMap model, @RequestParam String id) {
		// Lead lead = leadService.getLeadById(id);
		LeadDto leaddto = LeadMapper.toLeadDTO(leadService.getLeadById(id));
		model.put("usersMap", userService.getAllUsersMap());
		model.put("lead", leaddto);
		model.put("action", "Edit");
		return "lead";
	}

	@PostMapping("/editlead")
	public String postEditLeadPage(ModelMap model, @RequestParam String id, @Valid Lead lead, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.put("action", "Edit");
			return "lead";
		} else {
			// get lead obj from DB and values from UI
			LeadDto leaddto = LeadMapper.toLeadDTO(leadService.getLeadById(id));
			leaddto.setFirstName(lead.getFirstName());
			leaddto.setLastName(lead.getLastName());
			leaddto.setEmail(lead.getEmail());
			leaddto.setContactno(lead.getContactno());
			leaddto.setProject(lead.getProject());
			leaddto.setRequirement(lead.getRequirement());
			leaddto.setBudget(lead.getBudget());
			leaddto.setStatus(lead.getStatus());
			leaddto.setAssignedUserId(lead.getAssignedUserId());
			leaddto.setStatus(lead.getStatus());

			// new notes
			LeadNotesDto newNoteDto = new LeadNotesDto();
			newNoteDto.setCreated_at(LocalDateTime.now());
			newNoteDto.setCreated_by_user_id(userService.getLoggedinUserObj().getId());
			newNoteDto.setNote(lead.getNewNote());
			List<LeadNotesDto> newNoteDTOLst = new ArrayList<>();
			newNoteDTOLst.add(newNoteDto);
			// add new note to lead
			leaddto.setNotes(newNoteDTOLst);

			LeadDetailsDto leadDetailsDto = LeadMapper.toLeadDetailsDTO(lead.getLeadDetails());
			// leadDetailsDto.setAddress(lead.getLeadDetails().getAddress());
			leaddto.setLeadDetails(leadDetailsDto);

			leaddto.setUpdatedAt(LocalDateTime.now());
			leaddto.setUpdatedByUserId(userService.getLoggedinUserObj().getId());
			leadService.updateLead(id, leaddto);
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("msg", "Lead updated successfully!");
			return "redirect:/leads";
		}
	}

	@GetMapping("/leadnotes/{leadid}")
	public ResponseEntity<List<LeadNote>> getLeadNotes(@PathVariable Long leadid) {
		List<LeadNote> notes = leadService.getAllNotesByLeadId(leadid);
		return ResponseEntity.ok(notes);
	}
}
