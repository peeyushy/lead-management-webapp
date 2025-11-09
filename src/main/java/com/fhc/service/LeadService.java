package com.fhc.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fhc.dto.LeadDto;
import com.fhc.dto.QuoteDto;
import com.fhc.dto.RequirementDto;
import com.fhc.model.Lead;
import com.fhc.model.LeadNote;

@Service
public class LeadService {

	private static final Logger log = LoggerFactory.getLogger(LeadService.class);

	@Value("${webservicebaseurl}")
	private String WS_BASE_URL = null;

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	public List<Lead> getAllLeads() {
		ResponseEntity<List<Lead>> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/leads/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Lead>>() {
				});
		return clientResponse.getBody();
	}

	public List<Lead> getAllLeadsByStatus(String status) {
		ResponseEntity<List<Lead>> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/leads/status/" + status,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Lead>>() {
				});
		return clientResponse.getBody();
	}

	public List<Lead> getAllLeadsByStatusIn(List<String> statusLst) {
		ResponseEntity<List<Lead>> clientResponse = restTemplate.exchange(
				WS_BASE_URL + "/api/leads/statusin/" + statusLst, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Lead>>() {
				});
		return clientResponse.getBody();
	}

	public List<Lead> getAllLeadsForTL(Long teamLeaderId) {
		ResponseEntity<List<Lead>> clientResponse = restTemplate.exchange(
				WS_BASE_URL + "/api/leads/userin/" + userService.getTeamLeaderAndExecutivesUserIds(teamLeaderId),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Lead>>() {
				});
		return clientResponse.getBody();
	}

	public List<Lead> getAllLeadsByAssignedUserId(Long userId) {
		ResponseEntity<List<Lead>> clientResponse = restTemplate.exchange(
				WS_BASE_URL + "/api/leads/userid/" + userId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Lead>>() {
				});
		return clientResponse.getBody();
	}

	public Lead getLeadById(String id) {
		ResponseEntity<Lead> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/leads/" + id, HttpMethod.GET,
				null, new ParameterizedTypeReference<Lead>() {
				});
		return clientResponse.getBody();
	}

	/**
	 * Get all LeadNotes associated with a given lead ID.
	 * 
	 * @param leadId the ID of the lead
	 * @return a list of LeadNotes for the lead
	 */
	public List<LeadNote> getAllNotesByLeadId(Long leadId) {
		ResponseEntity<List<LeadNote>> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/leads/" + leadId+"/notes",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<LeadNote>>() {
				});
		return clientResponse.getBody();
	}

	public void deleteLead(String id) {
		restTemplate.delete(WS_BASE_URL + "/api/leads/" + id);
		return;
	}

	public void addLead(Lead req) {
		HttpEntity<Lead> request = new HttpEntity<>(req);
		ResponseEntity<Lead> response = restTemplate.exchange(WS_BASE_URL + "/api/leads/create", HttpMethod.POST,
				request, Lead.class);
		return;
	}

	public void updateLead(String id, LeadDto leaddto) {		
		HttpEntity<LeadDto> request = new HttpEntity<>(leaddto);
		ResponseEntity<LeadDto> response = restTemplate.exchange(WS_BASE_URL + "/api/leads/" + id, HttpMethod.PUT,
				request, LeadDto.class);
		return;
	}

	public RequirementDto getReqDetailsByClientIdAndReqId(String cid, String reqid)
			throws RestClientException, UnsupportedEncodingException {
		ResponseEntity<RequirementDto> clientResponse = restTemplate.exchange(
				WS_BASE_URL + "/api/leads/getOpenAndQuotedReq/" + URLEncoder.encode(cid, "UTF-8") + '/' + reqid,
				HttpMethod.GET, null, new ParameterizedTypeReference<RequirementDto>() {
				});
		return clientResponse.getBody();
	}

	public ResponseEntity<Integer> addQuotesToReq(RequirementDto reqdto, String cid)
			throws RestClientException, UnsupportedEncodingException {

		HttpEntity<RequirementDto> request = new HttpEntity<>(reqdto);
		ResponseEntity<Integer> response = restTemplate.exchange(
				WS_BASE_URL + "/api/leads/addquotes/" + URLEncoder.encode(cid, "UTF-8"), HttpMethod.POST, request,
				int.class);
		return response;
	}

	public List<QuoteDto> getLatestQuotesByReqId(String reqid)
			throws NestedRuntimeException, UnsupportedEncodingException {
		ResponseEntity<List<QuoteDto>> clientResponse = restTemplate.exchange(
				WS_BASE_URL + "/api/leads/getLatestQuotesByReqId/" + reqid, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<QuoteDto>>() {
				});
		return clientResponse.getBody();
	}
}