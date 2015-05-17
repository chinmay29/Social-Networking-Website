package com.connect.dal.NewsFeed;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.connect.models.NewsFeed.Message;

@Component("MessageDao")
public class MessageDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Message> getMessages() {

		return jdbc.query("select * from Message_Table", new RowMapper<Message>() {

			public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
				Message message = new Message();

				message.setUserId(rs.getInt("User_ID"));
				message.setId(rs.getInt("Message_Id"));
				message.setText(rs.getString("Message_Text"));
				message.setTimestamp(rs.getDate("Message_Time"));
				message.setLike(rs.getInt("Message_Likes"));
				message.setFlag(rs.getInt("Message_Flag"));
				message.setName(rs.getString("Message_UserName"));

				return message;
			}

		});
	}
	
	public boolean update(Message message) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(message);
		
		return jdbc.update("update Message_Table set User_ID=:userId, Message_Text=:text, Message_Time=:timestamp, Message_Likes=:like, Message_Flag=:flag, Message_UserName=:name where Message_Id=:id", params) == 1;
	}
	
	public boolean messagelikeinc(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.update("update Message_Table set Message_Likes=Message_Likes+1 where Message_Id=:id", params) == 1;
	}
	
	
public boolean messageflaginc(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.update("update Message_Table set Message_Flag=Message_Flag+1 where Message_Id=:id", params) == 1;
	}


public boolean messagedelete(int id) {
	
	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("id", id);
	
	return jdbc.update("delete from Message_Table where Message_Id=:id", params) == 1;
}
	


public boolean editmessage(int id,String messagetext) {
	
	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("id", id);
	params.addValue("messagetext", messagetext);
	
	return jdbc.update("update Message_Table set Message_Text=:messagetext,Message_Likes=0,Message_Flag=0 where Message_Id=:id", params) == 1;
}
	
	
	
	public boolean create(Message message) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(message);
		
		return jdbc.update("insert into Message_Table (User_ID,Message_Id, Message_Text, Message_Time, Message_Likes, Message_Flag, Message_UserName) values (:userId,:id, :text, :timestamp, :like, :flag, :name)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<Message> messages) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(messages.toArray());
		
		return jdbc.batchUpdate("insert into Message_Table (User_ID,Message_Id, Message_Text, Message_Time, Message_Likes, Message_Flag) values (:userId,:id, :text, :timestamp, :like, :flag, :name)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from Message_Table where Message_Id=:id", params) == 1;
	}

	public Message getMessage(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from Message_Table where Message_Id=:id", params,
				new RowMapper<Message>() {

					public Message mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Message message = new Message();

						message.setUserId(rs.getInt("User_ID"));
						message.setId(rs.getInt("Message_Id"));
						message.setText(rs.getString("Message_Text"));
						message.setTimestamp(rs.getDate("Message_Time"));
						message.setLike(rs.getInt("Message_Likes"));
						message.setFlag(rs.getInt("Message_Flag"));
						message.setName(rs.getString("Message_UserName"));

						return message;
					}

				});
	}
	
}
