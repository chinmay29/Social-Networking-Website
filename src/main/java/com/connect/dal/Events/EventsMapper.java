package com.connect.dal.Events;

import java.sql.ResultSet;
import java.sql.SQLException;

//import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

//import com.connect.controllers.Events.EventsController;
import com.connect.models.Events.Event;


public class EventsMapper implements RowMapper<Event> {
	
	//private static final Logger log = Logger.getLogger(EventsController.class);
   public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   Event event = new Event();
	   event.setE_name(rs.getString("e_name"));
	   event.setE_location(rs.getString("e_location"));
	   event.setE_date(rs.getDate("e_date"));
	   event.setE_description(rs.getString("e_description"));
	   event.setE_id(rs.getInt("e_id"));
	   event.setE_joined(rs.getInt("e_joined"));
	   event.setE_creator(rs.getInt("e_creator"));
	   return event;
   }
}

