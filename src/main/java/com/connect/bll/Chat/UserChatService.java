package com.connect.bll.Chat;

import java.util.List;

import com.connect.models.UserMgmt.User;

public interface UserChatService {

	public List<User> refreshFriendsList(int userId);
	
	public List<User> getFriendsMatchingQuery(String searchQuery, List<User> friendsList);
	
	public List<User> getUsers(List<Integer> users);
}
