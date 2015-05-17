package com.connect.bll.NewsFeed;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.connect.models.NewsFeed.Status;


public class StatusModule {

	public static void main(String[] args) throws IOException {
		
		StatusFactory statusfactory=new StatusFactory();
		
		//placeholders to follow
		System.out.println("testing model, enter the status ( message/comment");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice=br.readLine();
		Status status;
		status=statusfactory.makeStatus(choice);
		
		
		
	}

}
