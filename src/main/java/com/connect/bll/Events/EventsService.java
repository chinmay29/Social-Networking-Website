package com.connect.bll.Events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.dal.Events.EventsDAO;
import com.connect.models.Events.Event;
import com.connect.models.Events.EventUser;

@Service("eventsService")
public class EventsService {

	private EventsDAO eventsDAO;
	
	@Autowired
	public void setEventsDAO(EventsDAO eventsDAO) {
		this.eventsDAO = eventsDAO;
	}	
	
	public List<Event> listEvents()
	{
		return eventsDAO.listEvents();
	}

	public void create(Event event) {
		
		eventsDAO.create(event);
	}

	public Event getEvent(int id) {
		// 
		return eventsDAO.getEvent(id);
	}

	public void deleteEvent(int id) {
		
		eventsDAO.deleteEvent(id);
	}

	public int getJoined(int id) {
		
		return eventsDAO.getJoined(id);
	}

	public void updateCount(int id, String operation) {
		
		int count = eventsDAO.getJoined(id);
		eventsDAO.updateCount(id,count,operation);
		
	}

	public List<Event> getEvent(String name) {
		
		return eventsDAO.getEvent(name);
	}

	public int createEvent(Event event) {
		
		return eventsDAO.createEvent(event);
	}

	
	public void insert(int e_id, int userid) {
	
	eventsDAO.insert(e_id,userid);
	
}
//	public int createTest(Event event) {
//		
//		return eventsDAO.createTest(event);
//	}

	public void deleteEventUser(int id) {
		
		eventsDAO.deleteEventUser(id);
		
	}

	public void updateE(Event event) {
		
		eventsDAO.updateE(event);
	}

	public int getCreator(int id) {
		
		return eventsDAO.getCreator(id);
	}

	public List<EventUser> getListOfParticipants(int id) {
		
		return eventsDAO.getListOfParticipants(id);
	}

	public void deleteRecord(int id, int userid) {
		
		eventsDAO.deleteRecord(id,userid);
		
	}


//	public int getEventId(String name) {
//				
//		return eventsDAO.getEventId(name);
//	}

	
}
