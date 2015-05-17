package com.connect.controllers.NewsFeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.bll.Friends.FriendService;
import com.connect.bll.NewsFeed.StatusService;
import com.connect.bll.UserMgmt.UserMgmtService;
import com.connect.models.Friends.Friends;
import com.connect.models.NewsFeed.Comment;
import com.connect.models.NewsFeed.Message;
import com.connect.models.NewsFeed.Status;
import com.connect.models.UserMgmt.User;

@Controller
public class NewsFeedController {
	
	private StatusService statusService;
	private FriendService friendService;
	private UserMgmtService userMgmtService;

	@Autowired
	public void setUserMgmtService(UserMgmtService userMgmtService) {
		this.userMgmtService = userMgmtService;
	}
	
	
	
	@RequestMapping("/newsfeed")
	public String newsFeedPage() {
		
		return "NewsFeed/newsfeed";
	}
	
	@Autowired
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
	
	
	@Autowired
	public void setFriendService(FriendService friendService){
		this.friendService=friendService;
	}
	
	
	@RequestMapping("/statushistory")
	public String showOffers(Model model,HttpSession session) {
		
		List<Status> mystatushistory=new CopyOnWriteArrayList<>();
		List<Status> statushistory=new CopyOnWriteArrayList<>();
		statushistory = statusService.getStatusHistory();
		
		for(Status status : statushistory)
		{
			if(status.userId==(int)session.getAttribute("userId"))
			{
				mystatushistory.add(status);
			}
		}
		
		
		model.addAttribute("mystatushistory", mystatushistory);
		
		return "NewsFeed/statushistory";
	}
	
	@RequestMapping(value="/createstatus")
    public String createStatus(Model model)
    {
		return "NewsFeed/createstatus";
		
    }
	
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String createStatusMessage(@ModelAttribute("statusmessage") String inputtext,BindingResult result,Model model,HttpSession session) throws ParseException 
	//public String createStatusMessage(Model model,@Valid String somestring, BindingResult result) throws ParseException
	{
		
	    Message newmessage=new Message();
	    newmessage.userId=(int)session.getAttribute("userId");
		int rand=(new Random()).nextInt(10000)+1;
		newmessage.setId(rand);
		newmessage.setLike(0);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		newmessage.setFlag(0);
		newmessage.setTimestamp(df.parse(df.format(new Date())));
		//String inputtext=null;
		User user=userMgmtService.getUserById((int)session.getAttribute("userId"));
		newmessage.setName(user.getFirstName());
		
		model.addAttribute("statusmessage",inputtext);
		newmessage.setText(inputtext);
        statusService.create(newmessage);   
		return "NewsFeed/newsfeed";
	}
	
	@RequestMapping(value="/currentstatus")
    public String currentStatus(Model model,HttpSession session)
    {
		
		List<Status> mycurrentupdates=new CopyOnWriteArrayList<>();
		List<Status> currentupdates=new CopyOnWriteArrayList<>();
		currentupdates = statusService.getStatusHistory();
		
		
		int userid=(int)session.getAttribute("userId");
		System.out.println("The user id is "+ userid);
		List<Friends> friends= friendService.getCurrent(userid); //getting id's of all the friends
		
		System.out.println("The size of friends list is"+ friends.size());
		
		for(Status status : currentupdates)
		{
			for(Friends friend : friends)
			{
				if(status.userId==friend.getId() || status.userId==userid)
				{
					mycurrentupdates.add(status);
				}
			}
		}
		
		System.out.println("The size of mycurrentupdates is "+mycurrentupdates.size());
		
		model.addAttribute("mycurrentupdates", mycurrentupdates);
		
		return "NewsFeed/currentstatus";
		
    }
	
	
	@RequestMapping(value="/addcomment", method=RequestMethod.POST)
    public String addComment(@ModelAttribute("id") String idstring,@ModelAttribute("newcomment") String newcomment,BindingResult result,Model model,HttpSession session) throws ParseException
    {
		Comment comment=new Comment();
		int rand=(new Random()).nextInt(10000)+10000;
		comment.userId=(int)session.getAttribute("userId");
		comment.setId(rand);
		comment.setLike(0);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		comment.setFlag(0);
		comment.setTimestamp(df.parse(df.format(new Date())));
		model.addAttribute("id",idstring);
		model.addAttribute("newcomment",newcomment);
		comment.setMessageid(Integer.parseInt(idstring));
		comment.setText(newcomment);
		User user=userMgmtService.getUserById((int)session.getAttribute("userId"));
		comment.setName(user.getFirstName());
		statusService.create(comment);
		
		List<Status> mycurrentupdates=new CopyOnWriteArrayList<>();
		List<Status> currentupdates=new CopyOnWriteArrayList<>();
		currentupdates = statusService.getStatusHistory();
		
		
		
		int userid=(int)session.getAttribute("userId");
		
		List<Friends> friends= friendService.getCurrent(userid); //getting id's of all the friends
		
		System.out.println("The size of friends list is"+ friends.size());
		
		for(Status status : currentupdates)
		{
			for(Friends friend : friends)
			{
				if(status.userId==friend.getId() || status.userId==userid)
				{
					mycurrentupdates.add(status);
				}
			}
		}
		
		Set<Status> set = new HashSet<Status>();
		set.addAll(mycurrentupdates);
		mycurrentupdates.clear();
		mycurrentupdates.addAll(set);
		
		
		
model.addAttribute("mycurrentupdates", mycurrentupdates);
		
		
		return "NewsFeed/currentstatus";
		
    }
	
	
	@RequestMapping(value="/likeclicked")
	public String likeClicked(@ModelAttribute("id") String idstring,BindingResult result,Model model,HttpSession session) throws ParseException
	{
		model.addAttribute("id",idstring);
		int id=Integer.parseInt(idstring);
		
		if(id<10000)
			statusService.likeMessage(id);
		else
			statusService.likeComment(id);
		
		List<Status> mycurrentupdates=new CopyOnWriteArrayList<>();
		List<Status> currentupdates=new CopyOnWriteArrayList<>();
		currentupdates = statusService.getStatusHistory();
		
		
		
		int userid=(int)session.getAttribute("userId");
		
		List<Friends> friends= friendService.getCurrent(userid); //getting id's of all the friends
		
		System.out.println("The size of friends list is"+ friends.size());
		
		for(Status status : currentupdates)
		{
			for(Friends friend : friends)
			{
				if(status.userId==friend.getId() || status.userId==userid)
				{
					mycurrentupdates.add(status);
				}
			}
		}
		
		Set<Status> set = new HashSet<Status>();
		set.addAll(mycurrentupdates);
		mycurrentupdates.clear();
		mycurrentupdates.addAll(set);
		
		
		
model.addAttribute("mycurrentupdates", mycurrentupdates);
		
		return "NewsFeed/currentstatus";
		
			
			
		}
		
	@RequestMapping(value="/flagclicked")
	public String flagClicked(@ModelAttribute("id") String idstring,BindingResult result,Model model,HttpSession session) throws ParseException
	{
		model.addAttribute("id",idstring);
		int id=Integer.parseInt(idstring);
		
		if(id<10000)
			statusService.flagMessage(id);
		else
			statusService.flagComment(id);
		
		
		List<Status> mycurrentupdates=new CopyOnWriteArrayList<>();
		List<Status> currentupdates=new CopyOnWriteArrayList<>();
		currentupdates = statusService.getStatusHistory();
		
		
		
		int userid=(int)session.getAttribute("userId");
		
		List<Friends> friends= friendService.getCurrent(userid); //getting id's of all the friends
		
		System.out.println("The size of friends list is"+ friends.size());
		
		for(Status status : currentupdates)
		{
			for(Friends friend : friends)
			{
				if(status.userId==friend.getId() || status.userId==userid)
				{
					mycurrentupdates.add(status);
				}
			}
		}
		
		Set<Status> set = new HashSet<Status>();
		set.addAll(mycurrentupdates);
		mycurrentupdates.clear();
		mycurrentupdates.addAll(set);
		
		
		
		model.addAttribute("mycurrentupdates", mycurrentupdates);
		
		return "NewsFeed/currentstatus";
			
		}
	
	
	@RequestMapping(value="/editstatus")
	public String editStatus(@ModelAttribute("id") String idstring,@ModelAttribute("newstatus") String newstatus,BindingResult result,Model model,HttpSession session) throws ParseException
	{
		
		model.addAttribute("id",idstring);
		model.addAttribute("newstatus",newstatus);
		int id=Integer.parseInt(idstring);
		
		if(id<10000)
				statusService.modifyMessage(id,newstatus);
		else
			    statusService.modifyComment(id,newstatus);
		
		
		List<Status> mystatushistory=new CopyOnWriteArrayList<>();
	
		List<Status> statushistory=new CopyOnWriteArrayList<>();
		statushistory = statusService.getStatusHistory();
		
		for(Status status : statushistory)
		{
			if(status.userId==(int)session.getAttribute("userId"))
			{
				mystatushistory.add(status);
			}
		}
		
		
		
		
		
		model.addAttribute("mystatushistory", mystatushistory);
		
		return "NewsFeed/statushistory";
		
		
	}
		
	@RequestMapping(value="/deleteclicked")
	public String deleteClicked(@ModelAttribute("id") String idstring,BindingResult result,Model model,HttpSession session) throws ParseException
	{
		model.addAttribute("id",idstring);
		int id=Integer.parseInt(idstring);
		
		if(id<10000)
			statusService.deleteMessage(id);
		else
			statusService.deleteComment(id);
		
		List<Status> mystatushistory=new CopyOnWriteArrayList<>();
		
		List<Status> statushistory=new CopyOnWriteArrayList<>();
		statushistory = statusService.getStatusHistory();
		
		for(Status status : statushistory)
		{
			if(status.userId==(int)session.getAttribute("userId"))
			{
				mystatushistory.add(status);
			}
		}
		
		model.addAttribute("mystatushistory", mystatushistory);
		
		return "NewsFeed/statushistory";
		
		
			
		}	
	
	
	
	
	
	
	
	
	
	
	/*
	@RequestMapping(value="/addcomment", method=RequestMethod.POST)
	public String createComment(@ModelAttribute("statusmessage") String inputtext,@ModelAttribute("name2") String newname,BindingResult result,Model model) throws ParseException 
	//public String createStatusMessage(Model model,@Valid String somestring, BindingResult result) throws ParseException
	{
		
	    Message newmessage=new Message();
		int rand=(new Random()).nextInt(1000)+1;
		newmessage.setId(rand);
		newmessage.setLike(0);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		newmessage.setFlag(0);
		newmessage.setTimestamp(df.parse(df.format(new Date())));
		//String inputtext=null;
		System.out.println("The name sent is "+newname);
		model.addAttribute("statusmessage",inputtext);
		newmessage.setText(inputtext);
        statusService.create(newmessage);   
		return "home";
	}
	
	*/
	
	
	//@Requestmapping(value="/currentstatus")
	
	/*
	@RequestMapping(value="/currentpage", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Comment comment, BindingResult result) {
		
		if(result.hasErrors()) {
			return "createoffer";
		}
		
		statusService.
		
		return "offercreated";
	}
	*/
}
