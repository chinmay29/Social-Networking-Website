package com.connect.dal.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.connect.dal.UserMgmt.UserMgmtDAO;
import com.connect.models.Chat.ChatHistory;
import com.connect.models.Chat.ChatHistoryMessage;
import com.connect.models.Chat.ChatHistoryUserMessage;
import com.connect.models.UserMgmt.User;

@Repository("chatHistoryDAO")
public class ChatHistoryDAOImpl implements ChatHistoryDAO {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private UserMgmtDAO userMgmtDAO;

	@Autowired
	public void setUserMgmtDAO(UserMgmtDAO userMgmtDAO) {
		this.userMgmtDAO = userMgmtDAO;
	}

	@Override
	public int insertChatHistory(ChatHistory chatHistory) throws DataAccessException {

		String SQL = "INSERT INTO chat_history (chat_id, msg_id, from_user_id, to_user_id) "
				+ " VALUES (:chatId, :msgId, :fromUserId, :toUserId) ";

		Map<String, Number> m = new HashMap<String, Number>();
		m.put("chatId", chatHistory.getChatId());
		m.put("msgId", chatHistory.getMsgId());
		m.put("fromUserId", chatHistory.getFromUserId());
		m.put("toUserId", chatHistory.getToUserId());

		int numRowsAffected = jdbc.update(SQL, m);

		return numRowsAffected;
	}

	@Override
	public int updateChatHistory(ChatHistory chatHistory) throws DataAccessException {

		return 0;
	}

	@Override
	public int saveChatHistory(ChatHistory chatHistory) throws DataAccessException {
		return insertChatHistory(chatHistory);
	}

	@Override
	public int deleteChatHistory(ChatHistory chatHistory) throws DataAccessException {

		String SQL = "DELETE FROM chat_history "
				+ " WHERE chat_id = :chatId "
				+ " AND msg_id = :msgId "
				+ " AND from_user_id = :fromUserId "
				+ " AND to_user_id = :toUserId ";

		Map<String, Number> m = new HashMap<String, Number>();
		m.put("chatId", chatHistory.getChatId());
		m.put("msgId", chatHistory.getMsgId());
		m.put("fromUserId", chatHistory.getFromUserId());
		m.put("toUserId", chatHistory.getToUserId());

		int numRowsAffected = jdbc.update(SQL, m);

		return numRowsAffected;
	}

	@Override
	public List<ChatHistory> getAllChatHistoryOfChat(long chatId) throws DataAccessException {

		ChatHistoryMapper chatHistoryMapper = new ChatHistoryMapper();

		String SQL = "SELECT " + chatHistoryMapper.getDbColumns()
				+ " FROM chat_history "
				+ " WHERE chat_id = :chatId ";

		Map<String, Long> m = new HashMap<String, Long>();
		m.put("chatId", chatId);

		List<ChatHistory> chatHistoryList = (List<ChatHistory>) jdbc.query(SQL, chatHistoryMapper);

		return chatHistoryList;
	}

	@Override
	public List<ChatHistory> getAllChatHistoryOfUser(int userId) throws DataAccessException {

		ChatHistoryMapper chatHistoryMapper = new ChatHistoryMapper();

		String SQL = "SELECT " + chatHistoryMapper.getDbColumns()
				+ " FROM chat_history "
				+ " WHERE from_user_id = :userId "
				+ " UNION "
				+ "SELECT " + chatHistoryMapper.getDbColumns()
				+ " FROM chat_history "
				+ " WHERE to_user_id = :userId ";

		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("userId", userId);

		List<ChatHistory> chatHistoryList = (List<ChatHistory>) jdbc.query(SQL, chatHistoryMapper);

		return chatHistoryList;
	}

	@Override
	public List<ChatHistoryMessage> getChatHistoryMessagesForUser(int toUserId)
			throws DataAccessException {

		String SQL = "SELECT M.msg_id, M.msg_text, M.msg_time, H.chat_id, H.from_user_id, H.to_user_id "
				+ " FROM chat_messages M "
				+ " JOIN chat_history H "
				+ " ON M.msg_id = H.msg_id "
				+ " WHERE to_user_id = :toUserId ";

		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("toUserId", toUserId);

		List<ChatHistoryMessage> chatHistoryMsgList = (List<ChatHistoryMessage>) jdbc.query(SQL, m, new ChatHistoryMessageMapper());

		return chatHistoryMsgList;
	}

	@Override
	public Map<Long, List<User>> getChatsForUser(int toUserId)
			throws DataAccessException {
		
		Map<Long, List<User>> userChats = new HashMap<Long, List<User>>();
		
		ChatHistoryMapper chatHistoryMapper = new ChatHistoryMapper();
		
		String SQL1 = "SELECT DISTINCT chat_id, NULL as msg_id, NULL as from_user_id, NULL as to_user_id "
				+ " FROM chat_history "
				+ " WHERE to_user_id = :toUserId ";
		
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("toUserId", toUserId);
		
		List<ChatHistory> chatIdList = (List<ChatHistory>) jdbc.query(SQL1, m, chatHistoryMapper);
		
		Collections.reverse(chatIdList);
		
		String SQL2 = "SELECT DISTINCT to_user_id, NULL as chat_id, NULL as msg_id, NULL as from_user_id "
				+ " FROM chat_history "
				+ " WHERE chat_id = :chatId ";
		
		for(ChatHistory chatHistory: chatIdList) {
			
			Long chatId = chatHistory.getChatId();
			
			Map<String, Long> cm = new HashMap<String, Long>();
			cm.put("chatId", chatId);
			
			List<ChatHistory> chatReceipientList = (List<ChatHistory>) jdbc.query(SQL2, cm, chatHistoryMapper);
			
			List<User> usersList = new ArrayList<User>();
			
			for(ChatHistory chatReceipient: chatReceipientList) {
				int receipientUserId = chatReceipient.getToUserId();
				
				User user = userMgmtDAO.getUserById(receipientUserId);
				
				usersList.add(user);
			}
			
			userChats.put(chatId, usersList);
		}
		
		return userChats;
	}

	@Override
	public List<User> getUsersForChat(long chatId) throws DataAccessException {
		
		List<User> chatUsersList = new ArrayList<User>();
		
		String SQL = "SELECT DISTINCT to_user_id, NULL as chat_id, NULL as msg_id, NULL as from_user_id "
				+ " FROM chat_history "
				+ " WHERE chat_id = :chatId "; 
		
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("chatId", chatId);
		
		 List<ChatHistory> receipientIdList = jdbc.query(SQL, m, new ChatHistoryMapper());
		 
		 for(ChatHistory receipient : receipientIdList) {
			 int receipientId = receipient.getToUserId();
			 
			 User user = userMgmtDAO.getUserById(receipientId);
			 
			 chatUsersList.add(user);
		 }
		
		return chatUsersList;
	}

	@Override
	public List<ChatHistoryUserMessage> getAllChatHistoryUserMsgsForChatAndUser(long chatId, int userId)
			throws DataAccessException {
		
		String SQL = "SELECT M.msg_id, M.msg_text, M.msg_time, H.chat_id, H.from_user_id, H.to_user_id, U.first_name, U.last_name "
				+ " FROM chat_messages M "
				+ " JOIN chat_history H "
				+ " ON M.msg_id = H.msg_id "
				+ " JOIN users U "
				+ " ON H.from_user_id = U.user_id "
				+ " WHERE chat_id = :chatId "
				+ " AND to_user_id = :userId "
				+ " ORDER BY M.msg_time DESC ";
		
		Map<String, Number> m = new HashMap<String, Number>();
		m.put("chatId", chatId);
		m.put("userId", userId);
		
		List<ChatHistoryUserMessage> chatHistoryUserMsgList = (List<ChatHistoryUserMessage>) jdbc.query(SQL, m, new ChatHistoryUserMessageMapper());
		
		return chatHistoryUserMsgList;
	}

	@Override
	public List<Integer> getRecentContactsList(int userId)
			throws DataAccessException {
		
		String SQL = "SELECT DISTINCT user_id "
				+ " FROM (SELECT H.to_user_id as user_id, M.msg_time "
				+ " FROM chat_messages M "
				+ " JOIN chat_history H "
				+ " ON M.msg_id = H.msg_id "
				+ " WHERE H.from_user_id = :userId "
				+ " AND H.to_user_id != :userId "
				+ " UNION "
				+ " SELECT H.from_user_id as user_id, M.msg_time "
				+ " FROM chat_messages M "
				+ " JOIN chat_history H "
				+ " ON M.msg_id = H.msg_id "
				+ " WHERE H.to_user_id = :userId "
				+ " AND H.from_user_id != :userId) as A "
				+ " ORDER BY msg_time DESC "
				+ " LIMIT 5 ";
		
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("userId", userId);
		
		List<Integer> recentContactsUserIdList = jdbc.query(SQL, m, new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("user_id");
			}
			
		});
		
		return recentContactsUserIdList;
	}

	@Override
	public List<Integer> getMostContactedList(int userId)
			throws DataAccessException {
		
		String SQL = "SELECT DISTINCT user_id "
				+ " FROM (SELECT to_user_id as user_id "
				+ " FROM chat_history "
				+ " WHERE from_user_id = :userId "
				+ " AND to_user_id != :userId "
				+ " UNION ALL "
				+ " SELECT from_user_id as user_id "
				+ " FROM chat_history "
				+ " WHERE to_user_id = :userId "
				+ " AND from_user_id != :userId) as A "
				+ " GROUP BY user_id "
				+ " ORDER BY count(user_id) DESC "
				+ " LIMIT 5 ";
		
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("userId", userId);
		
		List<Integer> mostContactedUserIdList = jdbc.query(SQL, m, new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("user_id");
			}
			
		});
		
		return mostContactedUserIdList;
	}
	
}
