package com.connect.dal.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.connect.models.Chat.ChatMessage;

@Repository("chatMessageDAO")
public class ChatMessageDAOImpl implements ChatMessageDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public long insertChatMessage(ChatMessage chatMsg) throws DataAccessException {
		
		String SQL = "INSERT INTO chat_messages (msg_text, from_user_id) "
				+ " VALUES (:msgText, :fromUserId) ";
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("msgText", chatMsg.getMsgText());
		m.put("fromUserId", chatMsg.getFromUserId());
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(chatMsg);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		// int numRowsAffected = 
				jdbc.update(SQL, parameters, keyHolder);
		
		long msgId = keyHolder.getKey().longValue();
		
		return msgId;
	}

	@Override
	public int updateChatMessage(ChatMessage chatMsg) throws DataAccessException {
		
		String SQL = "UPDATE chat_messages "
				+ " SET msg_text = :msgText "
				+ " WHERE msg_id = :msgId ";
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("msgId", chatMsg.getMsgId());
		m.put("msgText", chatMsg.getMsgText());
		
		int numRowsAffected = jdbc.update(SQL, m);
		
		return numRowsAffected;
	}

	@Override
	public int deleteChatMessage(long msgId) throws DataAccessException {
		
		String SQL = "DELETE FROM chat_messages "
				+ " WHERE msg_id = :msgId ";
		
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("msgId", msgId);
		
		int numRowsAffected = jdbc.update(SQL, m);
		
		return numRowsAffected;
	}

	@Override
	public ChatMessage getChatMessage(long msgId) throws DataAccessException {
		
		ChatMessageMapper chatMsgMapper = new ChatMessageMapper();
		
		String SQL = "SELECT " + chatMsgMapper.getDbColumns()
				+ " FROM chat_messages "
				+ " WHERE msg_id = :msgId ";
		
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("msgId", msgId);
		
		ChatMessage chatMsg = (ChatMessage)  jdbc.queryForObject(SQL, m, chatMsgMapper);
		
		return chatMsg;
	}

	@Override
	public List<ChatMessage> getAllChatMessages() throws DataAccessException {
		
		ChatMessageMapper chatMsgMapper = new ChatMessageMapper();
		
		String SQL = "SELECT " + chatMsgMapper.getDbColumns()
				+ " FROM chat_messages ";
		
		List<ChatMessage> chatMessages = (List<ChatMessage>) jdbc.query(SQL, chatMsgMapper);
		
		return chatMessages;
	}
	
}
