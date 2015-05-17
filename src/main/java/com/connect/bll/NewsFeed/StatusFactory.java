package com.connect.bll.NewsFeed;

import com.connect.models.NewsFeed.Comment;
import com.connect.models.NewsFeed.Message;
import com.connect.models.NewsFeed.Status;


public class StatusFactory {

	public  Status makeStatus(String choice) {
		
		if(choice=="Message")
			return new Message();
		else
			return new Comment();
		
		
	}
	
	
	

}
