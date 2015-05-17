package com.connect.dal.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connect.models.Chat.ChatMessage;

public class ChatMessageMapper implements RowMapper<ChatMessage> {
	
	private String dbColumns = "msg_id, msg_text, msg_time, from_user_id";
	
	public String getDbColumns() {
		return dbColumns;
	}

	@Override
	public ChatMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ChatMessage chatMsg = new ChatMessage();
		
		chatMsg.setMsgId(rs.getLong("msg_id"));
		chatMsg.setMsgText(rs.getString("msg_text"));
		chatMsg.setMsgTime(rs.getTimestamp("msg_time"));
		chatMsg.setFromUserId(rs.getInt("from_user_id"));
		
		return chatMsg;
	}
	
}
