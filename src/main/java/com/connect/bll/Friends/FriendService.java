package com.connect.bll.Friends;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.Friends.FriendsDAO;
import com.connect.models.Friends.Friends;
import com.connect.models.UserMgmt.User;

@Service("friendsService")
public class FriendService {
	
	public FriendService(){
		System.out.print("It came here");
	}
	
	private FriendsDAO friendsDao;
	
	@Autowired
	public void setFriendsDao(FriendsDAO friendsDao) {
		this.friendsDao = friendsDao;
	}

	public List<Friends> getCurrent(int id){
		return friendsDao.getFriends(id);
	}
	public List<Friends> getRecent(int id){
		return friendsDao.getRecentFriends(id);
	}
	public Friends getName(int id){
		return friendsDao.getName(id);
	}
	public List<Friends> getFriendReq(int id){
		return friendsDao.getFriendReq(id);
	}
	public boolean addFriend(int id1, int id2){
		return friendsDao.addFriend(id1,id2);
	}
	public boolean acceptFriend(int id1, int id2){
		return friendsDao.acceptFriend(id1,id2);
	}
	public boolean rejectFriend(int id1, int id2){
		return friendsDao.rejectFriend(id1,id2);
	}
	public List<Friends> findFriend(int id, String name){
		return friendsDao.findFriend(id, name);
	}
	public List<Friends> findPerson(int id, String name){
		return friendsDao.findPerson(id, name);
	}
	public boolean removeFriend(int id1, int id2){
		return friendsDao.removeFriend(id1,id2);
	}
	public int calculateMutual(int id1, int id2){
		return friendsDao.calculateMutual(id1,id2);
	}
	public boolean findStatus(int id1, int id2){
		return friendsDao.findStatus(id1,id2);
	}
	public List<Friends> mutualFriend(int id1, int id2){
		return friendsDao.mutualFriend(id1,id2);
	}
	public List<User> getProfile(int id){
		return friendsDao.getProfile(id);
	}
	
}
