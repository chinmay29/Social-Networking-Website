package com.connect.bll.Events;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.Events.EventUserDAO;
import com.connect.models.Events.Event;
import com.connect.models.Events.EventUser;

@Service("eventuserService")
public class EventUserService {

	private EventUserDAO eventuserDAO;
	private static final Logger log = Logger.getLogger(EventUserService.class);
	
	
	@Autowired
	public void setEventUserDAO(EventUserDAO eventuserDAO) {
		this.eventuserDAO = eventuserDAO;
	}
	
	public EventUser getUser(int id) {
		
		return eventuserDAO.getUser(id);
	}

	public List<EventUser> getEventId(int id) {
		
		return eventuserDAO.getEventId(id);
	}
	
	public Event getEvent(int id) {
		// 
		return eventuserDAO.getEvent(id);
	}

}
