package com.connect.controllers.Friends;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.bll.Friends.FriendService;
import com.connect.models.Friends.Friends;
import com.connect.models.UserMgmt.User;

@Controller
public class FriendsController {
	
	
	private FriendService friendService;
	
	@Autowired
	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}


	@RequestMapping("/friends")
	public String friendPage(Model model){
		
	
		return "Friends/friendshome";
	}
	@RequestMapping("/showfriends")
	public String showFriends(Model model, HttpSession session){
		int sessionId= (int) session.getAttribute("userId");
		List<Friends> friends= friendService.getCurrent(sessionId); //getting id's of all the friends
		List<Friends> friendList= new ArrayList(); //list to store names of friends
		for(int i=0;i<friends.size();i++){
			Friends friendName=friendService.getName(friends.get(i).getId()); //get name for a particular id
			friendList.add(friendName); //Add that name to list of friends
		}
		if(friendList.isEmpty()){
			System.out.println("no friends");
			model.addAttribute("MSG","You do not have any friends yet!");
		}
		model.addAttribute("friends", friendList); //pass list of names of friends
		return "Friends/showfriends";
	}
	@RequestMapping("/recentfriends")
	public String showRecentFriends(ModelMap model,HttpSession session){
		
		int id= (int) session.getAttribute("userId");
		System.out.println("Id:"+id);
		List<Friends> friends= friendService.getRecent(id); //getting id's of all the friends
		List<Friends> friendList= new ArrayList(); //list to store names of friends
		for(int i=0;i<friends.size();i++){
			Friends friendName=friendService.getName(friends.get(i).getId()); //get name for a particular id
			friendList.add(friendName); //Add that name to list of friends
		}
		//model.addAttribute("friends", friendList); //pass list of names of friends
		model.put("recentfriend", friendList);
		return "Friends/recentfriends";
	}

	@RequestMapping("/getfriendReq")
	public String showFriendReq(Model model, HttpSession session){
		int id= (int) session.getAttribute("userId");
		//1:session id
		List<Friends> friends= friendService.getFriendReq(id); //getting id's of all the friends
		//System.out.println(friends.get(0).getId());
		List<Friends> friendList= new ArrayList(); //list to store names of friends
		for(int i=0;i<friends.size();i++){
			Friends friendName=friendService.getName(friends.get(i).getId()); //get name for a particular id
			friendList.add(friendName); //Add that name to list of friends
		}
		if(friends.isEmpty()){
			model.addAttribute("MSG", "No friend request at this time");
		}
		//System.out.println(friendList.get(0).getName());
		model.addAttribute("getfriendReq", friendList); //pass list of names of friends
		return "Friends/getfriendReq";
	}
	
	@RequestMapping(value="/acceptreq", method=RequestMethod.POST)
    public String acceptReq(@ModelAttribute("id") int idstring,BindingResult result,Model model, HttpSession session) throws ParseException
    {
		//System.out.print("ID:"+idstring);
		int sessionId= (int) session.getAttribute("userId");
		//1: session id and idstring: whose request is being accepted.
		Boolean status=friendService.acceptFriend(idstring,sessionId);
		if(status==true){
		return "Friends/friendshome";
		}
		else{
			return "Friends/getfriendReq";
		}
    }
	
	@RequestMapping(value="/findfriend", method=RequestMethod.POST)
    public String findFriend(@ModelAttribute("name") String name,BindingResult result,Model model, HttpSession session) throws ParseException
    {
		//System.out.print("ID:"+idstring);
		int sessionId= (int) session.getAttribute("userId");
		//1:session id
		name = name+"%";
		List<Friends> friends= friendService.findFriend(sessionId, name);
		int NoOfMutuals=0;
	
		for(int i=0;i<friends.size();i++){
			NoOfMutuals=friendService.calculateMutual(sessionId,friends.get(i).getId());
			//System.out.println("no of mutual friends:"+NoOfMutuals);
			friends.get(i).setId2(NoOfMutuals);
			
		}
		
		if(!friends.isEmpty()){
			model.addAttribute("findfriends", friends); //pass list of names of friends
			
		}
		else{
			model.addAttribute("MSG","no such friend in your friend list");
		}
		return "Friends/findfriend";
    }

	@RequestMapping(value="/findperson", method=RequestMethod.POST)
    public String findPerson(@ModelAttribute("name") String name,HttpSession session, BindingResult result,Model model) throws ParseException
    {
		//System.out.print("ID:"+idstring);
		//1:session id
		int sessionId= (int) session.getAttribute("userId");
		name = name+"%";
		List<Friends> friends= friendService.findPerson(sessionId,name);
		int NoOfMutuals=0;
		System.out.println("size of frnds:"+friends.size());
		for(int i=0;i<friends.size();i++){
			NoOfMutuals=friendService.calculateMutual(sessionId,friends.get(i).getId());
			//System.out.println("no of mutual friends:"+NoOfMutuals);
			friends.get(i).setId2(NoOfMutuals);
			boolean status=friendService.findStatus(sessionId,friends.get(i).getId());
			if(status==true){
				friends.get(i).setStatus("Friends");
			}
			else{
				friends.get(i).setStatus("Strangers ");
			}
		}
		if(!friends.isEmpty()){
		model.addAttribute("findpersons", friends); //pass list of names of friends
		}
		else{
			model.addAttribute("MSG","no such person on connect network");
		}
		return "Friends/findperson";
    }

	@RequestMapping(value="/rejectreq", method=RequestMethod.POST)
    public String rejectReq(@ModelAttribute("id") int idstring,HttpSession session, BindingResult result,Model model) throws ParseException
    {
		//System.out.print("ID:"+idstring);
		int sessionId= (int) session.getAttribute("userId");
		//1: session id and 5: whose request is being rejected.
		Boolean status=friendService.rejectFriend(idstring,sessionId);
		if(status==true){
		return "home";
		}
		else{
			return "Friends/getfriendReq";
		}
    }
	
	@RequestMapping(value="/showProfile", method=RequestMethod.POST)
    public String showProfile(@ModelAttribute("id") int idString,HttpSession session, BindingResult result,Model model) throws ParseException
    {
		//System.out.print("ID:"+idstring);
		int sessionId= (int) session.getAttribute("userId");
		//1: session id and 5: whose request is being rejected.
		//Boolean status=friendService.rejectFriend(idstring,sessionId);
		List<User> users=friendService.getProfile(idString);
		model.addAttribute("profile", users);
			return "Friends/showProfile";
	}
	@RequestMapping(value="/removefriend", method=RequestMethod.POST)
    public String removeFriend(@ModelAttribute("id") int idstring,HttpSession session, BindingResult result,Model model) throws ParseException
    {
		//System.out.print("ID:"+idstring);
		int sessionId= (int) session.getAttribute("userId");
		//1: session id and 5: whose request is being rejected.
		Boolean status=friendService.removeFriend(sessionId,idstring);
		if(status==true){
		return "home";
		}
		else{
			return "Friends/findfriend";
		}
	}
	
	@RequestMapping(value="/addfriend", method=RequestMethod.POST)
    public String addFriend(@ModelAttribute("id") int idstring,HttpSession session, BindingResult result,Model model) throws ParseException
    {	
		//5:session ID
		int sessionId= (int) session.getAttribute("userId");
		Boolean status=friendService.addFriend(sessionId,idstring);
		//System.out.println("fsfs");
		if(status==true){
			 model.addAttribute("MSG","Friend request sent");			
		}
		else{
		 model.addAttribute("MSG","You have already sent the request");
	
		}
		return "Friends/addfriend";
	}
	
	@RequestMapping(value="/mutualfriend", method=RequestMethod.POST)
    public String mutualFriend(@ModelAttribute("id") int idstring,HttpSession session, BindingResult result,Model model) throws ParseException
    {	
		//5:session ID
		int sessionId= (int) session.getAttribute("userId");
		List<Friends> mutualfriends=friendService.mutualFriend(sessionId,idstring);
		if(mutualfriends.isEmpty()){
			model.addAttribute("MSG","No mutual friends");
		}
		
		//System.out.println("size"+mutualfriends.size());
		model.addAttribute("mutual", mutualfriends);
		return "Friends/mutualfriend";
	}
}
