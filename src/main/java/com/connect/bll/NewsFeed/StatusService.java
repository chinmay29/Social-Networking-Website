package com.connect.bll.NewsFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.NewsFeed.CommentDAO;
import com.connect.dal.NewsFeed.MessageDAO;
import com.connect.models.NewsFeed.Comment;
import com.connect.models.NewsFeed.Message;
import com.connect.models.NewsFeed.Status;



@Service("StatusService")
public class StatusService {
	
	private CommentDAO commentDao;
	private MessageDAO messageDao;
	
	@Autowired
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}
	@Autowired
	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}

	public List<Status> getStatusHistory() {
		
		List<Status> statuslist=new CopyOnWriteArrayList<Status>();
		statuslist.addAll(messageDao.getMessages());
		List<Comment> commentappender= new ArrayList<Comment>();
		
		commentappender.addAll(commentDao.getComments());
		
		for(Status message : statuslist)
		{
		     for(Comment comment : commentappender)
		     {
		    	 if(message.id==comment.getMessageid())
		    	 {
  		    		 statuslist.add(statuslist.indexOf(message)+1,comment); 
		    	 }    // This list would contain combination of messages along 
		    	         // with their respective comments
		    	 		     }
						}
		
		//error code
		if(statuslist.size()==0)
		{
			System.out.println("there is some problem");
		statuslist.add(new Message());	
		}
		
		
		return statuslist;
	}

	public void create(Message message) {
		messageDao.create(message);
	}
	
	public void likeMessage(int id)
	{
	
		messageDao.messagelikeinc(id);
	}
	
	public void likeComment(int id)
	{
		commentDao.commentlikeinc(id);
	}
	
	public void flagMessage(int id)
	{
		messageDao.messageflaginc(id);
	}
	
	public void flagComment(int id)
	{
		commentDao.commentflaginc(id);
	}
	
	public void modifyMessage(int id, String message)
	{
		messageDao.editmessage(id, message);
	}
	
	public void modifyComment(int id, String comment)
	{
		commentDao.editcomment(id, comment);
	}
	
	
	
	
	public List<Comment> getCurrent() {
		return commentDao.getComments();
	}

	public void create(Comment comment) {
		commentDao.create(comment);
	}
	
	
	public void deleteMessage(int id)
	{
		messageDao.messagedelete(id);
	}
	
	public void deleteComment(int id)
	{
		commentDao.commentdelete(id);
	}
	
	
	
	
	
}
