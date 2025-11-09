package com.fhc.dto;

import java.time.LocalDateTime;

public class LeadNotesDto {
	private Long id;
	private String note;
	private Long created_by_user_id;
	private LocalDateTime created_at;

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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the created_by_user_id
	 */
	public Long getCreated_by_user_id() {
		return created_by_user_id;
	}

	/**
	 * @param created_by_user_id the created_by_user_id to set
	 */
	public void setCreated_by_user_id(Long created_by_user_id) {
		this.created_by_user_id = created_by_user_id;
	}

	/**
	 * @return the created_at
	 */
	public LocalDateTime getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
}
