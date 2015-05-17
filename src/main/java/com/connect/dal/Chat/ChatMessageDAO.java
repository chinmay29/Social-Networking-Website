package com.connect.dal.Chat;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.connect.models.Chat.ChatMessage;

public interface ChatMessageDAO {
	
	public long insertChatMessage(ChatMessage chatMsg) throws DataAccessException;
	
	public int updateChatMessage(ChatMessage chatMsg) throws DataAccessException;
	
	public int deleteChatMessage(long msgId) throws DataAccessException;
	
	public ChatMessage getChatMessage(long msgId) throws DataAccessException;
	
	public List<ChatMessage> getAllChatMessages() throws DataAccessException;
	
}
