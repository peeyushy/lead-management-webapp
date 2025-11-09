package com.fhc.dto;

public class LeadDetailsDto {
	private Long id;
	private String whatsappno;
	private String address;
	private String dob;

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
}
