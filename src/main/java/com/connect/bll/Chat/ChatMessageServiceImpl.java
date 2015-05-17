package com.connect.bll.Chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.Chat.ChatHistoryDAO;
import com.connect.dal.Chat.ChatMessageDAO;
import com.connect.dal.UserMgmt.UserMgmtDAO;
import com.connect.models.Chat.ChatHistory;
import com.connect.models.Chat.ChatMessage;
import com.connect.models.UserMgmt.User;

@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {
	
	private ChatMessageDAO chatMsgDAO;

	@Autowired
	public void setChatMsgDAO(ChatMessageDAO chatMsgDAO) {
		this.chatMsgDAO = chatMsgDAO;
	}
	
	private ChatHistoryDAO chatHistoryDAO;

	@Autowired
	public void setChatHistoryDAO(ChatHistoryDAO chatHistoryDAO) {
		this.chatHistoryDAO = chatHistoryDAO;
	}
	
	private UserMgmtDAO userMgmtDAO;

	@Autowired
	public void setUserMgmtDAO(UserMgmtDAO userMgmtDAO) {
		this.userMgmtDAO = userMgmtDAO;
	}

	@Override
	public void sendMsg(String msgText, int fromUserId, List<User> receipients, long chatId) {
		
		ChatMessage chatMsg = new ChatMessage(msgText, fromUserId);
		
		long msgId = chatMsgDAO.insertChatMessage(chatMsg);
		
		chatMsg.setMsgId(msgId);
		
		for(User receipient : receipients) {
			
			int toUserId = receipient.getUserId();
			
			ChatHistory chatHistory = new ChatHistory(chatId, msgId, fromUserId, toUserId);
			
			chatHistoryDAO.insertChatHistory(chatHistory);
		}
	}

	@Override
	public List<User> getFrequentContacts(int userId) {
		
		List<Integer> recentContactsUserIdList = chatHistoryDAO.getRecentContactsList(userId);
		
		List<Integer> mostContactedUserIdList = chatHistoryDAO.getMostContactedList(userId);
		
		List<Integer> frequentContactsUserIdList = new ArrayList<Integer>(mostContactedUserIdList);
		
		frequentContactsUserIdList.retainAll(recentContactsUserIdList);
		
		List<User> frequentContactsList = new ArrayList<User>();
		
		for(int frequentContactUserId : frequentContactsUserIdList) {
			
			User user = userMgmtDAO.getUserById(frequentContactUserId);
			
			frequentContactsList.add(user);
		}
		
		return frequentContactsList;
	}

}
