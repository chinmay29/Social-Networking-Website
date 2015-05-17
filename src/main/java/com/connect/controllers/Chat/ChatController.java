package com.connect.controllers.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connect.bll.Chat.UserChatService;
import com.connect.models.Chat.ChatBox;
import com.connect.models.UserMgmt.User;

@Controller
public class ChatController {

	private static Map<Integer, Stack<ChatBox>> userChatBoxes = new HashMap<Integer, Stack<ChatBox>>();

	private UserChatService userChatService;

	@Autowired
	public void setUserChatService(UserChatService userChatService) {
		this.userChatService = userChatService;
	}

	public static Map<Integer, Stack<ChatBox>> getUserChatBoxes() {
		return userChatBoxes;
	}

	@RequestMapping("/chat")
	public ModelAndView chatPage(HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}

		int userId = (int) session.getAttribute("userId");

		if(!userChatBoxes.containsKey(userId)) {

			ChatBox cb = new ChatBox();

			Stack<ChatBox> userChatBoxStack = new Stack<ChatBox>();

			userChatBoxStack.push(cb);

			userChatBoxes.put(userId, userChatBoxStack);
		}

		return new ModelAndView("redirect:/chat/refreshChatHistory");
	}

	@RequestMapping("/chat/addUser")
	public ModelAndView addUserToChat(HttpSession session, @RequestParam("friendId") int friendId, Model model, RedirectAttributes redirectAttributes) {

		int userId = (int) session.getAttribute("userId");

		if(!userChatBoxes.containsKey(userId)) {

			ChatBox cb = new ChatBox();

			Stack<ChatBox> userChatBoxStack = new Stack<ChatBox>();

			userChatBoxStack.push(cb);

			userChatBoxes.put(userId, userChatBoxStack);
		}

		if(!userChatBoxes.get(userId).get(0).checkReceipient(userId)) {
			userChatBoxes.get(userId).get(0).addReceipient(userId);
		}

		if(!userChatBoxes.get(userId).get(0).checkReceipient(friendId)) {
			userChatBoxes.get(userId).get(0).addReceipient(friendId);
		}

		List<Integer> receipientIds = userChatBoxes.get(userId).get(0).getReceipients();

		List<User> receipients = userChatService.getUsers(receipientIds);

		session.setAttribute("receipients", receipients);

		return new ModelAndView("redirect:/chatMessenger");
	}
	
	@RequestMapping("/chat/newChatBox")
	public ModelAndView newChatBox(HttpSession session) {

		int userId = (int) session.getAttribute("userId");

		if(session.getAttribute("receipients") != null) {
			session.removeAttribute("receipients");
		}
		
		createChatBox(userId);

		return new ModelAndView("redirect:/chatMessenger");
	}

	public static void createChatBox(int userId) {
		if(userChatBoxes.containsKey(userId)) {
			
			ChatBox cb = new ChatBox();
			
			userChatBoxes.get(userId).pop();
			
			userChatBoxes.get(userId).push(cb);
		}
	}
}
