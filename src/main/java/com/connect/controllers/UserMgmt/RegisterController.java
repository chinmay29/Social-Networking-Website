package com.connect.controllers.UserMgmt;

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
public class RegisterController {
	
	private UserMgmtService userMgmtService;

	@Autowired
	public void setUserMgmtService(UserMgmtService userMgmtService) {
		this.userMgmtService = userMgmtService;
	}
	
	@RequestMapping("/register")
	public String registerPage() {
		return "UserMgmt/register";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public ModelAndView registerUser(Model model, User user, RedirectAttributes redirectAttributes) {
		
		int userId = userMgmtService.insertUser(user);
		
		return new ModelAndView("redirect:/login");
	}
}
