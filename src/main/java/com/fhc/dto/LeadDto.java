package com.fhc.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fhc.constants.AppConstants;

public class LeadDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactno;
	private String budget;
	private String project;
	private String requirement;
	private AppConstants.LeadStatus status;
	private AppConstants.LeadSource source;
	private Long assignedUserId;
	private String assignedUserName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long createdByUserId;
	private Long updatedByUserId;

	// Embed details as a nested DTO
	private LeadDetailsDto leadDetails;

	// Notes as a flat list of simple DTOs
	private List<LeadNotesDto> notes;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactno
	 */
	public String getContactno() {
		return contactno;
	}

	/**
	 * @param contactno the contactno to set
	 */
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	/**
	 * @return the budget
	 */
	public String getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the requirement
	 */
	public String getRequirement() {
		return requirement;
	}

	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * @return the status
	 */
	public AppConstants.LeadStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AppConstants.LeadStatus status) {
		this.status = status;
	}

	/**
	 * @return the source
	 */
	public AppConstants.LeadSource getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(AppConstants.LeadSource source) {
		this.source = source;
	}

	/**
	 * @return the assignedUserId
	 */
	public Long getAssignedUserId() {
		return assignedUserId;
	}

	/**
	 * @param assignedUserId the assignedUserId to set
	 */
	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}	

	/**
	 * @return the assignedUserName
	 */
	public String getAssignedUserName() {
		return assignedUserName;
	}

	/**
	 * @param assignedUserName the assignedUserName to set
	 */
	public void setAssignedUserName(String assignedUserName) {
		this.assignedUserName = assignedUserName;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the createdByUserId
	 */
	public Long getCreatedByUserId() {
		return createdByUserId;
	}

	/**
	 * @param createdByUserId the createdByUserId to set
	 */
	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	/**
	 * @return the updatedByUserId
	 */
	public Long getUpdatedByUserId() {
		return updatedByUserId;
	}

	/**
	 * @param updatedByUserId the updatedByUserId to set
	 */
	public void setUpdatedByUserId(Long updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}

	/**
	 * @return the leadDetails
	 */
	public LeadDetailsDto getLeadDetails() {
		return leadDetails;
	}

	/**
	 * @param leadDetails the leadDetails to set
	 */
	public void setLeadDetails(LeadDetailsDto leadDetails) {
		this.leadDetails = leadDetails;
	}

	/**
	 * @return the notes
	 */
	public List<LeadNotesDto> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<LeadNotesDto> notes) {
		this.notes = notes;
	}
	
	public void resolveAssignedUserName(Map<Long, String> userIdNameMap) {
        if (assignedUserId != null) {
            this.assignedUserName = userIdNameMap.getOrDefault(assignedUserId, "Unknown User");
        } else {
            this.assignedUserName = "Unassigned";
        }
    }
}
