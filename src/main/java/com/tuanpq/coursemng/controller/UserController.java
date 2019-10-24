/**
 * @author admin
 * @date 03-10-2019
 */

package com.tuanpq.coursemng.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tuanpq.coursemng.entity.User;
import com.tuanpq.coursemng.model.NewUser;
import com.tuanpq.coursemng.model.PasswordModel;
import com.tuanpq.coursemng.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	private boolean isLoggedIn(Principal principal) {
		return principal != null;
	}
	
	@GetMapping("/login")
	public String showLogin(Principal principal){
		return isLoggedIn(principal) ?  "redirect:/course" : "/user/login"; 
	}
	
	@GetMapping("/register")
	public String showRegisterForm(Principal principal, Model model){
		if (isLoggedIn(principal)) return "redirect:/course";
		model.addAttribute("newUser", new NewUser());
		return "/user/register";
	}
	
	@PostMapping("/register")
	public String createNewUser(@Valid @ModelAttribute NewUser newUser, 
			BindingResult result){
		if (result.hasErrors()) return "/user/register";
		userService.saveUser(newUser);
		return "redirect:/login?success";
	}
	
	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "/user/view-profile";
	}
	
	@GetMapping("/profile/edit")
	public String showProfileForm(Model model, Principal principal) {
		showProfile(model, principal);
		return "/user/profile-form";
	}
	
	@PostMapping("/profile/edit")
	public String updateProfile(@Valid @ModelAttribute User user, 
			BindingResult result, Principal principal){
		if (result.hasErrors()) return "/user/profile-form";
		user.setUsername(principal.getName());
		userService.updateInfo(user);
		return "redirect:/profile?success";
	}
	
	@GetMapping("/profile/change-password")
	public String showChangePassForm(Model model){
		model.addAttribute("passwordModel", new PasswordModel());
		return "/user/change-pass";
	}
	
	@PostMapping("/profile/change-password")
	public String changePassword(@Valid @ModelAttribute PasswordModel passwordModel, 
			BindingResult result, Principal principal) {
		String username = principal.getName();
		if (result.hasErrors() || !userService.changePassword(passwordModel, username))
			return "/user/change-pass";
		return "redirect:/profile?success";
	}
	
}
