package com.connect.controllers.Chat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connect.bll.Chat.ChatMessageService;
import com.connect.models.UserMgmt.User;

@Controller
public class ChatMessageController {
	
	private ChatMessageService chatMsgService;

	@Autowired
	public void setChatMsgService(ChatMessageService chatMsgService) {
		this.chatMsgService = chatMsgService;
	}
	
	@RequestMapping("/chatMessenger")
	public String chatMessagePage(HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "UserMgmt/login";
		}
		
		int userId = (int) session.getAttribute("userId");
		
		List<User> frequentContactsList = chatMsgService.getFrequentContacts(userId);
		
		model.addAttribute("frequentContactsList", frequentContactsList);
		
		return "Chat/chatMessenger";
	}
	
	@RequestMapping(value="/chat/sendMsg", method=RequestMethod.POST, headers="Accept=*/*")
	public ModelAndView sendMsg(HttpSession session, Model model, @RequestParam(value="message", required=false, defaultValue="") String msgText, RedirectAttributes redirectAttributes) {
		
		int fromUserId = (int) session.getAttribute("userId");

		if(msgText != "" && msgText != null && session.getAttribute("receipients") != null) {
			
			long chatId = ChatController.getUserChatBoxes().get(fromUserId).get(0).hashCode();
			
			session.setAttribute("chatId", chatId);
			
			List<User> receipients = (List<User>) session.getAttribute("receipients");
			
			chatMsgService.sendMsg(msgText, fromUserId, receipients, chatId);
		}
		
		if(session.getAttribute("receipients") != null) {
			session.removeAttribute("receipients");
		}
		
		ChatController.createChatBox(fromUserId);
		
		return new ModelAndView("redirect:/chatHistory");
	}
	
	@RequestMapping(value="/chat/replyMsg", method=RequestMethod.POST, headers="Accept=*/*")
	public ModelAndView replyMsg(HttpSession session, Model model, @RequestParam(value="message", required=false, defaultValue="") String msgText, RedirectAttributes redirectAttributes) {
		
		int fromUserId = (int) session.getAttribute("userId");
		
		if(msgText != "" && msgText != null && session.getAttribute("chatId") != null && session.getAttribute("receipientUserList") != null) {
			
			long chatId = (long) session.getAttribute("chatId");
			
			List<User> receipients = (List<User>) session.getAttribute("receipientUserList");
			
			chatMsgService.sendMsg(msgText, fromUserId, receipients, chatId);
		}
		
		return new ModelAndView("redirect:/chatHistory");
	}

}
