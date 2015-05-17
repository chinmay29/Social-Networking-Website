package com.connect.dal.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.connect.dal.UserMgmt.UserMapper;
import com.connect.models.UserMgmt.User;

@Repository("userChatDAO")
public class UserChatDAOImpl implements UserChatDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<User> getAllFriends(int userId) throws DataAccessException {
		
		UserMapper userMapper = new UserMapper();
		
		String SQL = "SELECT " + userMapper.getDbColumns()
				+ " FROM users "
				+ " WHERE user_id NOT IN (:userId) ";
		
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("userId", userId);
		
		List<User> friendsList = (List<User>) jdbc.query(SQL, m, userMapper); 
		
		return friendsList;
	}
	
}
