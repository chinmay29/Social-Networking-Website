package com.connect.dal.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connect.models.Chat.ChatHistoryMessage;

public class ChatHistoryMessageMapper implements RowMapper<ChatHistoryMessage> {

	@Override
	public ChatHistoryMessage mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		ChatHistoryMessage chatHistoryMsg = new ChatHistoryMessage();
		
		chatHistoryMsg.setMsgId(rs.getLong("msg_id"));
		chatHistoryMsg.setMsgText(rs.getString("msg_text"));
		chatHistoryMsg.setMsgTime(rs.getTimestamp("msg_time"));
		chatHistoryMsg.setChatId(rs.getLong("chat_id"));
		chatHistoryMsg.setFromUserId(rs.getInt("from_user_id"));
		chatHistoryMsg.setToUserId(rs.getInt("to_user_id"));
		
		return chatHistoryMsg;
	}

}
