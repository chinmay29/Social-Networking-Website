package com.connect.bll.UserMgmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.UserMgmt.UserMgmtDAO;
import com.connect.models.UserMgmt.User;

@Service("userMgmtService")
public class UserMgmtService {

	private UserMgmtDAO userMgmtDAO;

	@Autowired
	public void setUserMgmtDAO(UserMgmtDAO userMgmtDAO) {
		this.userMgmtDAO = userMgmtDAO;
	}

	public User getUser(User user) {

		return userMgmtDAO.getUser(user);
	}
	
	public User getUserById(int userId) {
		return userMgmtDAO.getUserById(userId);
	}
	
	public List<User> getAllUsers() {
		return userMgmtDAO.getAllUsers();
	}
	
	public int insertUser(User user) {
		return userMgmtDAO.insertUser(user);
	}

	public User updateUser(User user) {
		
		return userMgmtDAO.update(user);
	}
}
