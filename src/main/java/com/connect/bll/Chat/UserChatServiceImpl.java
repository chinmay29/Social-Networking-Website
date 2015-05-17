package com.connect.bll.Chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.Chat.UserChatDAO;
import com.connect.dal.UserMgmt.UserMgmtDAO;
import com.connect.models.UserMgmt.User;

@Service("userChatService")
public class UserChatServiceImpl implements UserChatService {

	private UserChatDAO userChatDAO;

	@Autowired
	public void setUserChatDAO(UserChatDAO userChatDAO) {
		this.userChatDAO = userChatDAO;
	}
	
	private UserMgmtDAO userMgmtDAO;

	@Autowired
	public void setUserMgmtDAO(UserMgmtDAO userMgmtDAO) {
		this.userMgmtDAO = userMgmtDAO;
	}

	@Override
	public List<User> refreshFriendsList(int userId) {
		
		List<User> friendsList = userChatDAO.getAllFriends(userId);
		
		return friendsList;
	}

	@Override
	public List<User> getFriendsMatchingQuery(String searchQuery, List<User> friendsList) {
		
		String firstName = null;
		String lastName = null;
		searchQuery = searchQuery.toLowerCase();
		List<User> matchedFriendsList = new ArrayList<User>();
		
		for(User friend: friendsList) {
			firstName = friend.getFirstName().toLowerCase();
			lastName = friend.getLastName().toLowerCase();
			
			if(firstName.startsWith(searchQuery) || lastName.startsWith(searchQuery))
				matchedFriendsList.add(friend);
		}
		
		return matchedFriendsList;
	}

	@Override
	public List<User> getUsers(List<Integer> users) {
		
		List<User> usersList = new ArrayList<User>();
		
		for(int userId: users) {
			usersList.add(userMgmtDAO.getUserById(userId));
		}
		
		return usersList;
	}
}
