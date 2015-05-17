package com.connect.dal.Events;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.connect.models.Events.EventUser;



public class EventUserMapper implements RowMapper<EventUser> {
	
	   public EventUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   EventUser eventuser = new EventUser();
	   eventuser.setE_id(rs.getInt("e_id"));
	   eventuser.setU_id(rs.getInt("u_id"));
	   return eventuser;
   }
}


