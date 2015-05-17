package com.connect.dal.Chat;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.connect.models.UserMgmt.User;

public interface UserChatDAO {

	public List<User> getAllFriends(int userId) throws DataAccessException;
}
