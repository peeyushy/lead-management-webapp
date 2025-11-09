package com.fhc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LeadDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	private Lead lead;
	private String whatsappno;
	private String address;
	private String dob;

	// Constructors, getters, and setters
	/**
	 * @param lead
	 */
	public LeadDetails(Lead lead) {
		super();
		this.lead = lead;
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
	 * @return the whatsappno
	 */
	public String getWhatsappno() {
		return whatsappno;
	}

	/**
	 * @param whatsappno the whatsappno to set
	 */
	public void setWhatsappno(String whatsappno) {
		this.whatsappno = whatsappno;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dob, id, lead, whatsappno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeadDetails other = (LeadDetails) obj;
		return Objects.equals(address, other.address) && Objects.equals(dob, other.dob) && Objects.equals(id, other.id)
				&& Objects.equals(lead, other.lead) && Objects.equals(whatsappno, other.whatsappno);
	}

	@Override
	public String toString() {
		return "LeadDetails [id=" + id + ", lead=" + lead + ", whatsappno=" + whatsappno + ", address=" + address
				+ ", dob=" + dob + "]";
	}
}
