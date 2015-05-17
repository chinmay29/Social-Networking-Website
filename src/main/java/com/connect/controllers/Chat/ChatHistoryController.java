package com.connect.controllers.Chat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connect.bll.Chat.ChatHistoryService;
import com.connect.models.Chat.ChatHistory;
import com.connect.models.Chat.ChatHistoryUserMessage;
import com.connect.models.UserMgmt.User;

@Controller
public class ChatHistoryController {

	private ChatHistoryService chatHistoryService;

	@Autowired
	public void setChatHistoryService(ChatHistoryService chatHistoryService) {
		this.chatHistoryService = chatHistoryService;
	}

	@RequestMapping("/chatHistory")
	public String chatHistoryPage(HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "UserMgmt/login";
		}

		int toUserId = (int) session.getAttribute("userId");

		Map<Long, List<User>> userChats = chatHistoryService.getChatsForUser(toUserId);

		model.addAttribute("userChats", userChats);

		if(session.getAttribute("chatId") != null) {
			long chatId = (long) session.getAttribute("chatId");
			
			List<User> receipientUserList = chatHistoryService.getUsersForChat(chatId);

			session.setAttribute("receipientUserList", receipientUserList);

			List<ChatHistoryUserMessage> chatHistoryUserMsgList = chatHistoryService.getAllChatHistoryUserMsgsForChatAndUser(chatId, toUserId);

			session.setAttribute("chatHistoryUserMsgList", chatHistoryUserMsgList);
		}

		return "Chat/chatHistory";
	}

	@RequestMapping("/chat/refreshChatHistory")
	public ModelAndView refreshChatHistory() {

		return new ModelAndView("redirect:/chatHistory");
	}

	@RequestMapping("/chat/refreshChat")
	public ModelAndView refreshChat(HttpSession session, @RequestParam("chatId") long chatId, Model model, RedirectAttributes redirectAttributes) {

		int userId = (int) session.getAttribute("userId");

		if(session.getAttribute("chatId") != null) {
			session.removeAttribute("chatId");
		}

		session.setAttribute("chatId", chatId);

		List<User> receipientUserList = chatHistoryService.getUsersForChat(chatId);

		session.setAttribute("receipientUserList", receipientUserList);

		List<ChatHistoryUserMessage> chatHistoryUserMsgList = chatHistoryService.getAllChatHistoryUserMsgsForChatAndUser(chatId, userId);

		session.setAttribute("chatHistoryUserMsgList", chatHistoryUserMsgList);

		return new ModelAndView("redirect:/chatHistory");
	}

	@RequestMapping("/chat/deleteMsg")
	public ModelAndView deleteMsg(@RequestParam("chatId") long chatId, @RequestParam("msgId") long msgId, @RequestParam("fromUserId") int fromUserId, @RequestParam("toUserId") int toUserId) {

		ChatHistory chatHistory = new ChatHistory(chatId, msgId, fromUserId, toUserId);

		chatHistoryService.deleteChatHistory(chatHistory);

		return new ModelAndView("redirect:/chatHistory");
	}
}
