package com.connect.controllers.Events;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connect.bll.Events.EventUserService;
import com.connect.bll.Events.EventsService;
import com.connect.models.Events.Event;
import com.connect.models.Events.EventUser;

@Controller
public class EventUserController {

	private EventUserService eventuserService;
	private EventsService eventsService;
	private static final Logger log = Logger.getLogger(EventUserController.class);
	
	
	@Autowired
   public void setEventUserService(EventUserService eventuserService) {
		this.eventuserService = eventuserService;
	}
	
	
//	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
//	public String events(ModelMap model, @PathVariable("id") int id) { 
//	   	
//		
//		List<EventUser> eventlist = eventuserService.getEventId(id);
//		List<Event> eventsofuser = new ArrayList<Event>();//Collections.emptyList();
//		log.info("Inside EVENTUSERCONTROLLER");
//		for(EventUser event : eventlist)
//		{
//			if (event instanceof EventUser)
//			{	
//				log.info("Inside lood " + eventuserService.getEvent(event.getE_id()));
//				Event eventtemp = eventuserService.getEvent(event.getE_id());
//				log.info("Before loop");
//				eventsofuser.add(eventtemp);
//				log.info("After loop");
//			}
//		}
//		log.info("Outside Loop");
//		model.addAttribute("events",eventsofuser);
//		 return "Events/eventsUser";
//	}

	@RequestMapping(value = "/events")
	public String events(ModelMap model, HttpSession session) { 
	   	
		int id = (int)session.getAttribute("userId");
		List<EventUser> eventlist = eventuserService.getEventId(id);
		List<Event> eventsofuser = new ArrayList<Event>();//Collections.emptyList();
		log.info("Inside EVENTUSERCONTROLLER");
		for(EventUser event : eventlist)
		{
			if (event instanceof EventUser)
			{	
				log.info("Inside lood " + eventuserService.getEvent(event.getE_id()));
				Event eventtemp = eventuserService.getEvent(event.getE_id());
				eventsofuser.add(eventtemp);
				log.info("After loop");
			}
		}
		log.info("Outside Loop");
		model.addAttribute("events",eventsofuser);
		 return "Events/eventsUser";
	}

}





