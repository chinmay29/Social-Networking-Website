package com.connect.controllers.UserMgmt;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connect.bll.UserMgmt.UserMgmtService;
import com.connect.models.UserMgmt.User;

@Controller
public class ProfileController {

	private UserMgmtService userMgmtService;

	@Autowired
	public void setUserMgmtService(UserMgmtService userMgmtService) {
		this.userMgmtService = userMgmtService;
	}

	@RequestMapping("/profile")
	public String profilePage(HttpSession session, Model model) {

		int userId = (int) session.getAttribute("userId");

		User user = userMgmtService.getUserById(userId);

		model.addAttribute("user", user);

		return "UserMgmt/profile";
	}

	@RequestMapping("/editProfile")
	public String editProfilePage(HttpSession session, Model model) {

		int userId = (int) session.getAttribute("userId");

		User user = userMgmtService.getUserById(userId);

		model.addAttribute("user", user);

		return "UserMgmt/editprofile";
	}
	
	@RequestMapping(value="/editUserProfile", method=RequestMethod.POST)
	public ModelAndView editUserProfile(HttpSession session, Model model, User user, RedirectAttributes redirectAttributes) {
		
		int userId = (int) session.getAttribute("userId");
		
		user.setUserId(userId);
		
		User updatedUser = userMgmtService.updateUser(user);
		
		redirectAttributes.addFlashAttribute("user", updatedUser);
		
		return new ModelAndView("redirect:/profile");
	}
	
}
