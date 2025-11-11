package com.fhc.util;

import java.util.Comparator;
import java.util.stream.Collectors;

import com.fhc.dto.LeadDetailsDto;
import com.fhc.dto.LeadDto;
import com.fhc.dto.LeadNotesDto;
import com.fhc.model.Lead;
import com.fhc.model.LeadDetails;
import com.fhc.model.LeadNote;

public class LeadMapper {

	public static LeadDto toLeadDTO(Lead lead) {
		if (lead == null) {
			return null;
		}
		LeadDto dto = new LeadDto();
		dto.setId(lead.getId());
		dto.setFirstName(lead.getFirstName());
		dto.setLastName(lead.getLastName());
		dto.setEmail(lead.getEmail());
		dto.setContactno(lead.getContactno());
		dto.setBudget(lead.getBudget());
		dto.setProject(lead.getProject());
		dto.setRequirement(lead.getRequirement());
		dto.setStatus(lead.getStatus() != null ? lead.getStatus() : null);
		dto.setSource(lead.getSource() != null ? lead.getSource() : null);
		dto.setAssignedUserId(lead.getAssignedUserId());
		dto.setCreatedAt(lead.getCreatedAt());
		dto.setUpdatedAt(lead.getUpdatedAt());
		dto.setCreatedByUserId(lead.getCreatedByUserId());
		dto.setUpdatedByUserId(lead.getUpdatedByUserId());

		if (lead.getLeadDetails() != null) {
			dto.setLeadDetails(toLeadDetailsDTO(lead.getLeadDetails()));
		}

		if (lead.getNotes() != null) {
			dto.setNotes(lead.getNotes().stream().map(LeadMapper::toLeadNoteDTO)
					.sorted(Comparator.comparing(LeadNotesDto::getCreated_at).reversed()).collect(Collectors.toList()));
		}

		return dto;
	}

	public static LeadDetailsDto toLeadDetailsDTO(LeadDetails details) {
		if (details == null) {
			return null;
		}
		LeadDetailsDto dto = new LeadDetailsDto();
		dto.setId(details.getId());
		dto.setWhatsappno(details.getWhatsappno());
		dto.setAddress(details.getAddress());
		dto.setDob(details.getDob());
		return dto;
	}

	public static LeadNotesDto toLeadNoteDTO(LeadNote note) {
		if (note == null) {
			return null;
		}
		LeadNotesDto dto = new LeadNotesDto();
		dto.setId(note.getId());
		dto.setNote(note.getNote());
		dto.setCreated_at(note.getCreatedAt());
		return dto;
	}

	// Add other mapping methods as needed like fromDTO to Entity

}
