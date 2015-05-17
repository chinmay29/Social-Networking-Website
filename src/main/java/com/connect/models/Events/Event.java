package com.connect.models.Events;

import java.util.Date;

import javax.validation.constraints.Size;

public class Event {
	
	//@Size(min = 1, max = 100, message = "Name Must be between 1 and 100 characters")
	String e_name;
	
	//@Size(min = 10, max = 1000, message = "Address Must be between 10 and 1000 characters")
	String e_location;
	
	//int e_max_participants;
	
	int e_id;
	
	Date e_date =null;
	
	int e_joined;
	
	String e_description;
	
	int e_creator;
	
	
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	
	public int getE_joined() {
		return e_joined;
	}
	public void setE_joined(int joined) {
		this.e_joined = joined;
	}
	public String getE_description() {
		return e_description;
	}
	public void setE_description(String description) {
		this.e_description = description;
	}
	
	//Email: @Pattern(regexp=".*\\@.*\\.*")

	public Date getE_date() {
		return e_date;
	}
	public void setE_date(Date e_date) {
		this.e_date = e_date;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_location() {
		return e_location;
	}
	public void setE_location(String e_location) {
		this.e_location = e_location;
	}
	public int getE_creator() {
		return e_creator;
	}
	public void setE_creator(int e_creator) {
		this.e_creator = e_creator;
	}
	
	
//	public int getE_max_participants() {
//		return e_max_participants`;
//	}
//	public void setE_max_participants(int e_max_participants) {
//		this.e_max_participants` = e_max_participants;
//	}
	
}
