package com.connect.dal.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connect.models.Chat.ChatHistoryUserMessage;

public class ChatHistoryUserMessageMapper implements RowMapper<ChatHistoryUserMessage> {

	@Override
	public ChatHistoryUserMessage mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		ChatHistoryUserMessage chatHistoryUserMsg = new ChatHistoryUserMessage();
		
		chatHistoryUserMsg.setMsgId(rs.getLong("msg_id"));
		chatHistoryUserMsg.setMsgText(rs.getString("msg_text"));
		chatHistoryUserMsg.setMsgTime(rs.getTimestamp("msg_time"));
		chatHistoryUserMsg.setChatId(rs.getLong("chat_id"));
		chatHistoryUserMsg.setFromUserId(rs.getInt("from_user_id"));
		chatHistoryUserMsg.setToUserId(rs.getInt("to_user_id"));
		chatHistoryUserMsg.setFirstName(rs.getString("first_name"));
		chatHistoryUserMsg.setLastName(rs.getString("last_name"));
		
		return chatHistoryUserMsg;
	}

}
