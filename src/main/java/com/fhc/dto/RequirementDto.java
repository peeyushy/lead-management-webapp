package com.fhc.dto;

import java.time.LocalDateTime;

/**
 * 
 * @author Peeyush
 *
 */
public class RequirementDto {

	private String reqid;
	private LocalDateTime reqdatetime;
	private String reqComment;
	private String reqdroploc;
	private String reqpickuploc;
	private boolean reqdatetimeflexi;
	private boolean reqpickupdropflexi;
	private String reqtype;
	private String vtype;
	private String ltype;
	private String clientComment;
	private String quote;
	private String pickuplat;
	private String pickuplong;
	private String droplat;
	private String droplong;

	public String getClientComment() {
		return clientComment;
	}

	public void setClientComment(String clientComment) {
		this.clientComment = clientComment;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	/**
	 * @return the reqdatetime
	 */
	public LocalDateTime getReqdatetime() {
		return reqdatetime;
	}

	/**
	 * @param reqdatetime the reqdatetime to set
	 */
	public void setReqdatetime(LocalDateTime reqdatetime) {
		this.reqdatetime = reqdatetime;
	}

	public String getReqComment() {
		return reqComment;
	}

	public void setReqComment(String reqComment) {
		this.reqComment = reqComment;
	}

	public String getReqdroploc() {
		return reqdroploc;
	}

	public void setReqdroploc(String reqdroploc) {
		this.reqdroploc = reqdroploc;
	}

	public String getReqpickuploc() {
		return reqpickuploc;
	}

	public void setReqpickuploc(String reqpickuploc) {
		this.reqpickuploc = reqpickuploc;
	}

	public boolean isReqdatetimeflexi() {
		return reqdatetimeflexi;
	}

	public void setReqdatetimeflexi(boolean reqdatetimeflexi) {
		this.reqdatetimeflexi = reqdatetimeflexi;
	}

	public boolean isReqpickupdropflexi() {
		return reqpickupdropflexi;
	}

	public void setReqpickupdropflexi(boolean reqpickupdropflexi) {
		this.reqpickupdropflexi = reqpickupdropflexi;
	}

	public String getReqtype() {
		return reqtype;
	}

	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getLtype() {
		return ltype;
	}

	public void setLtype(String ltype) {
		this.ltype = ltype;
	}

	/**
	 * @return the pickuplat
	 */
	public String getPickuplat() {
		return pickuplat;
	}

	/**
	 * @param pickuplat the pickuplat to set
	 */
	public void setPickuplat(String pickuplat) {
		this.pickuplat = pickuplat;
	}

	/**
	 * @return the pickuplong
	 */
	public String getPickuplong() {
		return pickuplong;
	}

	/**
	 * @param pickuplong the pickuplong to set
	 */
	public void setPickuplong(String pickuplong) {
		this.pickuplong = pickuplong;
	}

	/**
	 * @return the droplat
	 */
	public String getDroplat() {
		return droplat;
	}

	/**
	 * @param droplat the droplat to set
	 */
	public void setDroplat(String droplat) {
		this.droplat = droplat;
	}

	/**
	 * @return the droplong
	 */
	public String getDroplong() {
		return droplong;
	}

	/**
	 * @param droplong the droplong to set
	 */
	public void setDroplong(String droplong) {
		this.droplong = droplong;
	}

}
