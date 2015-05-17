package com.connect.controllers.Events;

//import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.connect.bll.Events.EventUserService;
import com.connect.bll.Events.EventsService;
import com.connect.models.Events.Event;
import com.connect.models.Events.EventUser;

@Controller
public class EventsController {

	private EventsService eventsService;
	private EventUserService eventuserService;
	private static final Logger log = Logger.getLogger(EventsController.class);
	
	
	@Autowired
   public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model,HttpSession session) {
		int userid = (int)session.getAttribute("userId");
		model.addAttribute("event",new Event());
	    model.addAttribute("userid",userid);
	    return "Events/create";
	   }

	@RequestMapping(value = "/search")
	public String search(Model model, HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		
		log.info("Session id " + userId );
		
	    model.addAttribute("event",new Event());
	    return "Events/search";
	   }
	
////Editing the Event	
	@RequestMapping(value = "/edit/{e_id}", method = RequestMethod.GET)
	public String editEvent(ModelMap model, @PathVariable("e_id") int id) { 
	   	  
		 Event event = eventsService.getEvent(id);
		 model.addAttribute("event", event);
		 return "Events/edit";
	}

//Users Events Page   
//   @RequestMapping("/events")
//	public String events(Model model, HttpSession session) {
//	   
//	   	int userId = (int) session.getAttribute("userId");
//	   	List<Event> eventsTable = eventsService.listEvents();
//	   	model.addAttribute("events", eventsTable);
//	   	return "Events/eventsUser";
//	}
   
//Deleting the Event  
   @RequestMapping(value = "/delete/{e_id}", method = RequestMethod.GET)
	public ModelAndView deleteEvent(ModelMap model, @PathVariable("e_id") int id) {
	   
	   	eventsService.deleteEvent(id);
	   	eventsService.deleteEventUser(id);
	   	//List<Event> eventsTable = eventsService.listEvents();
	   	//model.addAttribute("events", eventsTable);
	   	return new ModelAndView("redirect:/events");
	}
   
//Creating the Event
   @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
   public String createEvent(@ModelAttribute("event") @Valid Event event, 
ModelMap model, BindingResult result, HttpSession session) {
	   
	 
	  int userid = (int)session.getAttribute("userId"); 
	  
	  if(result.hasErrors())
	  { 
		  log.info("Hello1");
		  return "Events/create";
	  }
	  
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  	 
		Date currentTime = null;
		try {
			currentTime = sdf.parse(sdf.format(event.getE_date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 	
		 model.addAttribute("e_name", event.getE_name());
		 model.addAttribute("e_location", event.getE_location());
		 model.addAttribute("e_date", currentTime);
		 model.addAttribute("e_description", event.getE_description());
		 model.addAttribute("e_id",event.getE_id());
		 model.addAttribute("e_joined",1);
		 event.setE_creator(userid);
		 event.setE_joined(1);
		 
		 log.info("Before Create " );
		 int eventid = eventsService.createEvent(event);
		 eventsService.insert(eventid,userid);
		 log.info("After Insert");
				 
		 return "Events/createEvent";
   }
   
   
   @RequestMapping(value = "/viewEventUser/{e_id}", method = RequestMethod.GET)
	public String viewEventUser(ModelMap model, @PathVariable("e_id") int id, HttpSession session) {
	   
	    Event event = eventsService.getEvent(id);
	   	model.addAttribute("events", event);
	    int userid = (int)session.getAttribute("userId"); 
	    int creatorid = eventsService.getCreator(id);
	    if(userid == creatorid)
	    {
	    	return "Events/viewEventUser";
	    }
	    
	    else
	    {
	    	List<EventUser> participants = eventsService.getListOfParticipants(id);
	    	for(EventUser userlist : participants)
			{
				if (userlist instanceof EventUser)
				{	
					if(userlist.getU_id() == userid)
					{
						return "Events/viewEventLeave";
					}
				}
			}
	    
	    	return "Events/viewEventJoin";
	    	
	    }
	    	
	}
   
   
   @RequestMapping(value = "/viewEvent/{e_id}", method = RequestMethod.GET)
	public String viewEvent(ModelMap model, @PathVariable("e_id") int id) {
	   	Event event = eventsService.getEvent(id);
	   	model.addAttribute("events", event);
	   	return "Events/viewEvent";
	}
   
//Joining the Event   
   @RequestMapping(value = "/joinEvent/{e_id}", method = RequestMethod.GET)
	public ModelAndView joinEvent(ModelMap model, @PathVariable("e_id") int id, HttpSession session) {
	   	
	    int userid = (int)session.getAttribute("userId"); 
	    Event event = eventsService.getEvent(id);
	   	event.setE_joined((event.getE_joined())+1);
	   	eventsService.updateCount(id,"add");
	   	eventsService.insert(id, userid);	   	
		//model.addAttribute("events", event);
	   	return new ModelAndView("redirect:/events");
	}
   
//Leaving the event   
   @RequestMapping(value = "/leaveEvent/{e_id}", method = RequestMethod.GET)
	public ModelAndView leaveEvent(ModelMap model, @PathVariable("e_id") int id,HttpSession session) {
	   	
	    int userid = (int)session.getAttribute("userId"); 
	    Event event = eventsService.getEvent(id);
	   	event.setE_joined((event.getE_joined())-1);
	   	eventsService.updateCount(id,"subtract");
	   	eventsService.deleteRecord(id,userid);
		model.addAttribute("events", event);
		return new ModelAndView("redirect:/events");
	}
   
   
//Searching the Event
   @RequestMapping(value = "/searchEvent", method = RequestMethod.POST)
   public String searchEvent(@ModelAttribute("event") @Valid Event event, 
   ModelMap model, BindingResult result) {
   
	   String name = event.getE_name();
	   name = name + "%";
	   List<Event> eventlist = eventsService.getEvent(name);
	   if(eventlist.isEmpty())
		   return "Events/searchError";
	   else
	   {
		   model.addAttribute("events", eventlist);
		   return "Events/searchView";
	   }
   }
   
//Editing the event   
   @RequestMapping(value = "/editing/{e_id}", method = RequestMethod.POST)
   public String editEvent(@ModelAttribute("event") @Valid Event event, 
   ModelMap model, BindingResult result, @PathVariable("e_id") int id) {
	  
	   int joined = eventsService.getJoined(id);
	   //eventsService.deleteEvent(id);
	   event.setE_joined((joined));
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  	 
		Date currentTime = null;
		try {
			currentTime = sdf.parse(sdf.format(event.getE_date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	   model.addAttribute("e_name", event.getE_name());
	   model.addAttribute("e_location", event.getE_location());
	   model.addAttribute("e_date", currentTime);
	   model.addAttribute("e_description", event.getE_description());
	   model.addAttribute("e_id",event.getE_id());
	   model.addAttribute("e_joined", event.getE_joined());
	   //model.addAttribute("e_joined",event.getE_joined());
	   //model.addAttribute("e_joined",1);
	   //event.setE_joined(1);
	   event.setE_id(id);
	   //eventsService.create(event);
	   eventsService.updateE(event);
	   return "Events/createEvent";
   }
   
}
   
	  
