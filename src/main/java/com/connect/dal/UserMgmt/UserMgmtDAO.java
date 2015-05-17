package com.connect.dal.UserMgmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.connect.models.UserMgmt.User;

@Repository("userMgmtDAO")
public class UserMgmtDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User getUser(User user) {
		
		UserMapper userMapper = new UserMapper();
		
		String SQL = "Select " + userMapper.getDbColumns() 
				+ " From users "
				+ " Where user_email = :user_email And password = :password ";
		
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("user_email", user.getEmail());
		m.put("password", user.getPassword());
		
		List<User> userFromDB = jdbc.query(SQL, m, userMapper);
		
		if(userFromDB.isEmpty())
			return null;
		else
			return userFromDB.get(0);
	}
	
	public User getUserById(int userId) {
		
		UserMapper userMapper = new UserMapper();
		
		String SQL = "Select " + userMapper.getDbColumns() 
				+ " From users "
				+ " Where user_id = :userId ";
		
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("userId", userId);
		
		User userFromDB = jdbc.queryForObject(SQL, m, userMapper);
		
		return userFromDB;
	}
	
	public List<User> getAllUsers() {
		
		UserMapper userMapper = new UserMapper();
		
		String SQL = "Select " + userMapper.getDbColumns()
				+ " From users ";
		
		List<User> userList = jdbc.query(SQL, userMapper);
		
		return userList;
	}
	
	public int insertUser(User user) {
		
		UserMapper userMapper = new UserMapper();
		
		String SQL = "INSERT INTO users "
				+ " (user_email, password, first_name, last_name) "
				+ " VALUES "
				+ " (:email, :password, :firstName, :lastName) ";
		
		String SQL1 = "INSERT INTO nodes "
				+ " (name) "
				+ " VALUES "
				+ " (:firstName) ";
		
		Map m = new HashMap();
		m.put("email", user.getEmail());
		m.put("password", user.getPassword());
		m.put("firstName", user.getFirstName());
		m.put("lastName", user.getLastName());
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbc.update(SQL, parameters, keyHolder);
		jdbc.update(SQL1, parameters, keyHolder);
		
		int userId = keyHolder.getKey().intValue();
		
		return userId;
	}

	public User update(User user) {
		
		String SQL = "UPDATE users "
				+ " SET user_email = :email, first_name = :firstName, last_name = :lastName "
				+ " WHERE user_id = :userId ";
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("email", user.getEmail());
		m.put("firstName", user.getFirstName());
		m.put("lastName", user.getLastName());
		m.put("userId", user.getUserId());
		
		jdbc.update(SQL, m);
		
		return user;
	}
}
