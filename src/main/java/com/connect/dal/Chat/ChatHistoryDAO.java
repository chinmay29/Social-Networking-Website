package com.connect.dal.Chat;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.connect.models.Chat.ChatHistory;
import com.connect.models.Chat.ChatHistoryMessage;
import com.connect.models.Chat.ChatHistoryUserMessage;
import com.connect.models.UserMgmt.User;

public interface ChatHistoryDAO {
	
	public int insertChatHistory(ChatHistory chatHistory) throws DataAccessException;
	
	public int updateChatHistory(ChatHistory chatHistory) throws DataAccessException;
	
	public int saveChatHistory(ChatHistory chatHistory) throws DataAccessException;
	
	public int deleteChatHistory(ChatHistory chatHistory) throws DataAccessException;
	
	public List<ChatHistory> getAllChatHistoryOfChat(long chatId) throws DataAccessException;
	
	public List<ChatHistory> getAllChatHistoryOfUser(int userId) throws DataAccessException;
	
	public List<ChatHistoryMessage> getChatHistoryMessagesForUser(int toUserId) throws DataAccessException;
	
	public Map<Long, List<User>> getChatsForUser(int toUserId) throws DataAccessException;
	
	public List<User> getUsersForChat(long chatId) throws DataAccessException;
	
	public List<ChatHistoryUserMessage> getAllChatHistoryUserMsgsForChatAndUser(long chatId, int userId) throws DataAccessException;
	
	public List<Integer> getRecentContactsList(int userId) throws DataAccessException;
	
	public List<Integer> getMostContactedList(int userId) throws DataAccessException;
	
}
