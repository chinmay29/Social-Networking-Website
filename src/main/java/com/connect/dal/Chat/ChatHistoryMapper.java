package com.connect.dal.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connect.models.Chat.ChatHistory;

public class ChatHistoryMapper implements RowMapper<ChatHistory> {
	
	private String dbColumns = "chat_id, msg_id, from_user_id, to_user_id";

	public String getDbColumns() {
		return dbColumns;
	}

	@Override
	public ChatHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ChatHistory chatHistory = new ChatHistory();
		
		chatHistory.setChatId(rs.getLong("chat_id"));
		chatHistory.setMsgId(rs.getLong("msg_id"));
		chatHistory.setFromUserId(rs.getInt("from_user_id"));
		chatHistory.setToUserId(rs.getInt("to_user_id"));
		
		return chatHistory;
	}
	
}
