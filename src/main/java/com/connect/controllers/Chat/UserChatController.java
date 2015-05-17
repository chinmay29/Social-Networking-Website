package com.connect.controllers.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connect.bll.Chat.UserChatService;
import com.connect.bll.UserMgmt.UserMgmtService;
import com.connect.models.UserMgmt.User;

@Controller
public class UserChatController {

	private Map<Integer, List<User>> userFriends = new HashMap<Integer, List<User>>();
	
	private UserChatService userChatService;

	@Autowired
	public void setUserChatService(UserChatService userChatService) {
		this.userChatService = userChatService;
	}
	
	private UserMgmtService userMgmtService;

	@Autowired
	public void setUserMgmtService(UserMgmtService userMgmtService) {
		this.userMgmtService = userMgmtService;
	}

	@RequestMapping(value="/chat/searchFriend", method=RequestMethod.POST, headers="Accept=*/*")
	public ModelAndView searchFriend(HttpServletRequest request, HttpSession session, Model model, @RequestParam(value="searchQuery", required=false, defaultValue="") String searchQuery, RedirectAttributes redirectAttributes) {
		
		int userId = (int) session.getAttribute("userId");
		
		if(!userFriends.containsKey(userId)) {
			userFriends.put(userId, userChatService.refreshFriendsList(userId));
		}
		
		List<User> usersList = userMgmtService.getAllUsers();
		
		User userToRemove = null;
		
		for(User user : usersList) {
			if(user.getUserId() == userId) {
				userToRemove = user;
			}
		}
		
		if(userToRemove != null) {
			usersList.remove(userToRemove);
		}
		
		List<User> matchedFriendsList = userChatService.getFriendsMatchingQuery(searchQuery, usersList);
		
		redirectAttributes.addFlashAttribute("matchedFriendsList", matchedFriendsList);
		
		return new ModelAndView("redirect:/chatMessenger");
	}
	
	@RequestMapping("/chat/refreshFriendsList")
	public ModelAndView refreshFriendsList(HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		
		if(userFriends.containsKey(userId)) {
			userFriends.remove(userId);
		}

		userFriends.put(userId, userChatService.refreshFriendsList(userId));
		
		return new ModelAndView("redirect:/chatMessenger");
	}
	
}
