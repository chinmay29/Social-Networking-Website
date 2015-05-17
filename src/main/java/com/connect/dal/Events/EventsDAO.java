package com.connect.dal.Events;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.connect.controllers.Events.EventsController;
import com.connect.models.Events.Event;
import com.connect.models.Events.EventUser;

@Repository("eventsDAO")
public class EventsDAO{
   private JdbcTemplate jdbcTemplateObject;
   private NamedParameterJdbcTemplate jdbc;
   
   private static final Logger log = Logger.getLogger(EventsController.class);

   
   @Autowired
   public void setDataSSource(DataSource dataSource) {
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   
   @Autowired
   public void setDataSource(DataSource dataSource) {
   	this.jdbc = new NamedParameterJdbcTemplate(dataSource);
   }
   
   public int createEvent(Event event)
   {
	   	SqlParameterSource parameters = new BeanPropertySqlParameterSource(event);
	 	KeyHolder keyHolder = new GeneratedKeyHolder();
	 	String SQL = "insert into e_event (e_name, e_location, e_description,e_joined,e_date,e_creator) values (:e_name, :e_location,:e_description,:e_joined,:e_date,:e_creator)";  
	 	jdbc.update(SQL, parameters, keyHolder);
	 	 
	 	int eventId = keyHolder.getKey().intValue();
	 	 
	 	return eventId;
   }

//   public int createTest(Event event)
//   {
//	  String SQL = "insert into e_event (e_name, e_location, e_description,e_joined,e_date) values (:name,:loc,:des,:join,:date)";
//   	  Map namedParameters = new HashMap();     
//      namedParameters.put("name", event.getE_name());     
//      namedParameters.put("loc", event.getE_location());  
//      namedParameters.put("des", event.getE_description());  
//      namedParameters.put("join", event.getE_joined());  
//      namedParameters.put("date", event.getE_date()); 
//      KeyHolder keyHolder = new GeneratedKeyHolder();
//      jdbcTemplateObject.update(SQL, namedParameters,keyHolder);  
//
//   }
//   
   public void create(Event event) {
      String SQL = "insert into e_event (e_name, e_location, e_description,e_joined,e_date,e_creator) values (?, ?, ?, ?,?,?)";
      jdbcTemplateObject.update( SQL, event.getE_name(), event.getE_location(),event.getE_description(),event.getE_joined(),event.getE_date(),event.getE_creator());
      return;
   }
  
   public List<Event> listEvents() {
      String SQL = "select * from e_event";
      List <Event> events = jdbcTemplateObject.query(SQL, 
                                new EventsMapper());
      return events;
   }

   public void deleteEvent(int id){
      String SQL = "delete from e_event where e_id = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with Name = " + id );
      return;
   }

   public void update(String name, Integer max_participants){
      String SQL = "update e_event set E_max_participants_ = ? where name = ?";
      jdbcTemplateObject.update(SQL,max_participants,name);
      System.out.println("Updated Record with name = " + name );
      return;
   }

public Event getEvent(int id) {
	  String SQL = "select * from e_event where e_id = ?";
      Event event = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new EventsMapper());
      return event;
   }

public int getJoined(int id) {
	
	 String SQL = "select e_joined from e_event where e_id = ?";
	 int count = jdbcTemplateObject.queryForInt(SQL, id);
     return count;
}

public void updateCount(int id, int count, String operation) {
	
	String SQL = "update e_event SET e_joined = ? WHERE e_id = ?";
	
	if(operation == "add")
		jdbcTemplateObject.update(SQL,count+1,id);
	if(operation == "subtract")
		jdbcTemplateObject.update(SQL,count-1,id);
}

public List<Event> getEvent(String name) {
	
	String SQL = "select * from e_event where e_name like ?";
	List <Event> events = jdbcTemplateObject.query(SQL,new Object[]{name}, 
            new EventsMapper());
	return events;
 }

public void insert(int e_id, int userid) {
	log.info("Inside insert");
	String SQL = "insert into event_user values(?, ?)";
	jdbcTemplateObject.update(SQL,e_id,userid);
	
}

public void deleteEventUser(int id) {
	
	String SQL = "delete from event_user where e_id = ?";
    jdbcTemplateObject.update(SQL, id);
    System.out.println("Deleted Record with Name = " + id );
    return;
 }

public void updateE(Event event) {
	
	log.info("Inside Update");
	String SQL = "update e_event set e_name = ?, e_location = ?, e_description =?,e_date = ? where e_id = ?";
	
	jdbcTemplateObject.update( SQL, event.getE_name(), event.getE_location(),event.getE_description(),event.getE_date(),event.getE_id());
    return;
}

public int getCreator(int id) {
	
	String SQL = "select e_creator from e_event where e_id = ?";
	int creatorId = jdbcTemplateObject.queryForInt(SQL, id);
	return creatorId;
}

public List<EventUser> getListOfParticipants(int id) {
	
	String SQL = "select * from event_user where e_id = ?";
	List <EventUser> participants = jdbcTemplateObject.query(SQL,new Object[]{id}, 
            new EventUserMapper());;
 
	return participants;
}

public void deleteRecord(int id, int userid) {
	
	String SQL = "delete from event_user where e_id = ? and u_id = ?";
    jdbcTemplateObject.update(SQL, id, userid);
    System.out.println("Deleted Record with Name = " + id );
    return;
	
}	
	
}




