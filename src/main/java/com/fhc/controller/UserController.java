package com.fhc.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fhc.constants.AppConstants;
import com.fhc.model.User;
import com.fhc.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		model.put("users", userService.getAllUsers());
		return "findusers";
	}

	@GetMapping("/delete-user")
	public String deleteUser(ModelMap model, @RequestParam String cid, @RequestParam String uid,
			final RedirectAttributes redirectAttributes) {
		String msg = "User " + userService.getUsersByUserId(uid).getFullname() + " deleted successfully!";
		userService.deleteUser(uid);
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:/edit-client?cid=" + cid;
	}

	@GetMapping("/adduser")
	public String showAddUserPage(ModelMap model, @RequestParam(required = false) String id) {
		model.put("action", "Add");
		Map<String, String> roleMap = new LinkedHashMap<String, String>();
		for (AppConstants.RoleNames role : AppConstants.RoleNames.values()) {
			roleMap.put(role.toString(), role.toString());
		}
		model.put("roleMap", roleMap);
		model.put("user", new User());
		model.put("id", id);
		return "user";
	}

	@PostMapping("/adduser")
	public String addUser(ModelMap model, @RequestParam(required = false) String id, // ✅ Optional
			@Valid User user, BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "user";
		} else {
			user.setCreatedby(userService.getLoggedinUserObj().getUsername());
			user.setLastupdatedby(userService.getLoggedinUserObj().getUsername());
			userService.addUser(user);

			redirectAttributes.addFlashAttribute("msg", "User " + user.getFullname() + " added successfully!");

			// Redirect based on context
			if (id != null && !id.isEmpty()) {
				return "redirect:/edituser?id=" + id;
			} else {
				return "redirect:/users";
			}
		}
	}

	@GetMapping("/edituser")
	public String showEditUserPage(ModelMap model, @RequestParam String id) {
		model.put("action", "Edit");
		User user = userService.getUsersByUserId(id);
		Map<String, String> roleMap = new LinkedHashMap<String, String>();
		roleMap.put(user.getRole().toString(), user.getRole().toString());
		model.put("roleMap", roleMap);
		model.put("user", user);
		model.put("id", id);
		return "user";
	}

	@PostMapping("/edituser")
	public String updateUser(ModelMap model, @RequestParam String uid, @Valid User user, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		model.put("action", "Edit");

		if (result.hasErrors()) {
			return "user";
		} else {
			user.setLastupdatedby(userService.getLoggedinUserObj().getUsername());
			user.setId(Long.parseLong(uid));
			userService.updateUser(uid, user);
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("msg", "User " + user.getFullname() + " updated successfully!");
			return "redirect:/edituser?id=" + uid;
		}
	}
}
