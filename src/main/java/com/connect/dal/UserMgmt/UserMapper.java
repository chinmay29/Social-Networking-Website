package com.connect.dal.UserMgmt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connect.models.UserMgmt.User;

public class UserMapper implements RowMapper<User> {
	
	private String dbColumns = "user_id, user_email, password, first_name, last_name";

	public String getDbColumns() {
		return dbColumns;
	}

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setUserId(rs.getInt("user_id"));
		user.setEmail(rs.getString("user_email"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		
		return user;
	}
}
