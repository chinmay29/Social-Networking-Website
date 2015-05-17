package com.connect.controllers.UserMgmt;

import javax.servlet.http.HttpServletRequest;
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
public class LoginController {

	private UserMgmtService userMgmtService;

	@Autowired
	public void setUserMgmtService(UserMgmtService userMgmtService) {
		this.userMgmtService = userMgmtService;
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "UserMgmt/login";
	}

	@RequestMapping(value="/verifylogin", method=RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpSession session, Model model, User user, RedirectAttributes redirectAttributes) {

		User userFromDB = userMgmtService.getUser(user);

		if(userFromDB == null || userFromDB.getUserId() <= 0) {

			redirectAttributes.addFlashAttribute("errorMessage", "The email or password you entered is incorrect");

			return new ModelAndView("redirect:/login");
		}
		else {
			session.setAttribute("userId", userFromDB.getUserId());
			session.setAttribute("firstName", userFromDB.getFirstName());
			session.setAttribute("lastName", userFromDB.getLastName());
			return new ModelAndView("redirect:/home");
		}
	}

	@RequestMapping("/resetlogin")
	public ModelAndView logoutUser(HttpSession session) {

		session.removeAttribute("userId");
		session.removeAttribute("firstName");
		session.removeAttribute("lastName");
		
		if(session.getAttribute("receipients") != null) {
			session.removeAttribute("receipients");
		}
		
		if(session.getAttribute("chatId") != null) {
			session.removeAttribute("chatId");
		}
		
		if(session.getAttribute("receipientUserList") != null) {
			session.removeAttribute("receipientUserList");
		}
		
		if(session.getAttribute("chatHistoryUserMsgList") != null) {
			session.removeAttribute("chatHistoryUserMsgList");
		}

		return new ModelAndView("redirect:/index");
	}
}
