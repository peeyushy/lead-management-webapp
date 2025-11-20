package com.fhc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fhc.constants.AppConstants;
import com.fhc.dto.SubscriptionDto;
import com.fhc.model.User;

@Service
public class UserService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Value("${webservicebaseurl}")
	private String WS_BASE_URL = null;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers() {
		ResponseEntity<List<User>> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/users/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
				});
		return clientResponse.getBody();
	}
	
	public Map<Long, String> getAllUsersMap() {
	    List<User> users = getAllUsers();
	    Map<Long, String> usersMap = new LinkedHashMap<>();
	    for (User user : users) {
	        usersMap.put(user.getId(), user.getFullname());
	    }
	    return usersMap;
	}

	public User getUsersByUserId(String id) {
		ResponseEntity<User> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/users/id/" + id, HttpMethod.GET,
				null, new ParameterizedTypeReference<User>() {
				});

		return clientResponse.getBody();
	}

	public User getUserByUserName(String username) {
		ResponseEntity<User> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/users/username/" + username,
				HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
				});
		return clientResponse.getBody();
	}

	public User getAdminOnlyUserByUserName(String username) {
		ResponseEntity<User> clientResponse = restTemplate.exchange(WS_BASE_URL + "/api/users/adminusername/" + username,
				HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
				});
		return clientResponse.getBody();
	}

	public void deleteUser(String id) {
		restTemplate.delete(WS_BASE_URL + "/api/users/id/" + id);
		return;
	}

	public void addUser(User user) {
		HttpEntity<User> request = new HttpEntity<>(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		ResponseEntity<User> response = restTemplate.exchange(WS_BASE_URL + "/api/users/create", HttpMethod.POST,
				request, User.class);
		return;
	}

	public void updateUser(String id, User user) {
		user.setCreatedby(getUsersByUserId(id).getCreatedby());
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> response = restTemplate.exchange(WS_BASE_URL + "/api/users/id/" + id, HttpMethod.PUT,
				request, User.class);
		return;
	}
	
	public void addUserSubs(SubscriptionDto userSubsDto) {
		HttpEntity<SubscriptionDto> request = new HttpEntity<>(userSubsDto);		
		ResponseEntity<SubscriptionDto> response = restTemplate.exchange(WS_BASE_URL + "/api/user-subscriptions", HttpMethod.POST,
				request, SubscriptionDto.class);
		return;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		user.setAuthorities(grantedAuthorities);
		log.info("Logged in user::" + username);
		return user;

	}

	public List<AppConstants.RoleNames> getAllRoles() {

		ResponseEntity<List<AppConstants.RoleNames>> clientResponse = restTemplate.exchange(
				WS_BASE_URL + "/api/role/all/", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AppConstants.RoleNames>>() {
				});

		return clientResponse.getBody();
	}

	public User getLoggedinUserObj() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return (User) ((UserDetails) principal);
		}
		return null;
	}

	public List<Long> getTeamLeaderAndExecutivesUserIds(Long teamLeaderId) {
		// Example of retrieving the team leader's own ID plus executives under them
		// This could call an API or database to retrieve subordinate users

		List<Long> userIds = new ArrayList<>();

		// Add team leader
		userIds.add(teamLeaderId);

		// Add executives under this team leader - pseudo code
		List<User> executives = getExecutivesByTeamLeader(teamLeaderId);
		executives.forEach(u -> userIds.add(u.getId()));

		return userIds;
	}

	public List<User> getExecutivesByTeamLeader(Long teamLeaderId) {
		// Example REST API call to fetch users reporting to the team leader
		String url = WS_BASE_URL + "/api/users/getExecutivesByTeamLeader/" + teamLeaderId;

		ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});

		return response.getBody();
	}

}