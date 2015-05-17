package com.connect.dal.Events;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.connect.models.Events.Event;
import com.connect.models.Events.EventUser;

@Repository("eventuserDAO")
public class EventUserDAO{
   private JdbcTemplate jdbcTemplateObject;
   private static final Logger log = Logger.getLogger(EventUserDAO.class);
   
   @Autowired
   public void setDataSource(DataSource dataSource) {
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }


public EventUser getUser(int id) {
	
	String SQL = "select * from user where id = ?";
	EventUser eventuser = jdbcTemplateObject.queryForObject(SQL, 
            new Object[]{id}, new EventUserMapper());
	return eventuser;
}


public List<EventUser> getEventId(int id) {
	
	String SQL = "select * from event_user where u_id = ?";
	List<EventUser> eventuserlist = jdbcTemplateObject.query(SQL,new Object[]{id}, new EventUserMapper());
	return eventuserlist;
}

public Event getEvent(int id) {
	  String SQL = "select * from e_event where e_id = ?";
	  Event event = jdbcTemplateObject.queryForObject(SQL, 
                      new Object[]{id}, new EventsMapper());
    return event;
 }

   
}
