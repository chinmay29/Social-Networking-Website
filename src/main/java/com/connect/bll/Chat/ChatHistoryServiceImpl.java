package com.connect.bll.Chat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.Chat.ChatHistoryDAO;
import com.connect.models.Chat.ChatHistory;
import com.connect.models.Chat.ChatHistoryMessage;
import com.connect.models.Chat.ChatHistoryUserMessage;
import com.connect.models.UserMgmt.User;

@Service("chatHistoryService")
public class ChatHistoryServiceImpl implements ChatHistoryService {
	
	private ChatHistoryDAO chatHistoryDAO;

	@Autowired
	public void setChatHistoryDAO(ChatHistoryDAO chatHistoryDAO) {
		this.chatHistoryDAO = chatHistoryDAO;
	}
	
	@Override
	public List<ChatHistoryMessage> getChatHistoryMessagesForUser(int toUserId) {
		
		return chatHistoryDAO.getChatHistoryMessagesForUser(toUserId);
	}

	@Override
	public Map<Long, List<User>> getChatsForUser(int toUserId) {
		
		return chatHistoryDAO.getChatsForUser(toUserId);
	}

	@Override
	public List<User> getUsersForChat(long chatId) {
		
		return chatHistoryDAO.getUsersForChat(chatId);
	}

	@Override
	public List<ChatHistoryUserMessage> getAllChatHistoryUserMsgsForChatAndUser(long chatId, int userId) {
		
		return chatHistoryDAO.getAllChatHistoryUserMsgsForChatAndUser(chatId, userId);
	}

	@Override
	public int deleteChatHistory(ChatHistory chatHistory) {
		return chatHistoryDAO.deleteChatHistory(chatHistory);
	}
	
}
