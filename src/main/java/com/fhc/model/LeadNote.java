package com.fhc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LeadNote implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	private Lead lead;

	// User who created the note
	private Long createdByUserId;

	// User who last updated the note
	private Long updatedByUserId;

	private String note;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	// Constructors, getters, and setters

	public LeadNote() {
		this.createdAt = LocalDateTime.now();
	}

	public LeadNote(Lead lead, Long createdByUserId, Long updatedByUserId, String note, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.lead = lead;
		this.createdByUserId = createdByUserId;
		this.updatedByUserId = updatedByUserId;
		this.note = note;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

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
	 * @return the lead
	 */
	public Lead getLead() {
		return lead;
	}

	/**
	 * @param lead the lead to set
	 */
	public void setLead(Lead lead) {
		this.lead = lead;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, createdByUserId, id, note, updatedAt, updatedByUserId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeadNote other = (LeadNote) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(createdByUserId, other.createdByUserId)
				&& Objects.equals(id, other.id) && Objects.equals(note, other.note)
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(updatedByUserId, other.updatedByUserId);
	}

	@Override
	public String toString() {
		return "LeadNote [id=" + id + ", createdByUserId=" + createdByUserId + ", updatedByUserId=" + updatedByUserId
				+ ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
