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

import com.connect.models.NewsFeed.Comment;

@Component("CommentDAO")
public class CommentDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Comment> getComments() {

		return jdbc.query("select * from Comment_Table", new RowMapper<Comment>() {

			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comment comment = new Comment();

				comment.setUserId(rs.getInt("User_ID"));
				comment.setId(rs.getInt("Comment_Id"));
				comment.setMessageid(rs.getInt("Msg_Id"));
				comment.setText(rs.getString("Comment_Text"));
				comment.setTimestamp(rs.getDate("Comment_Time"));
				comment.setLike(rs.getInt("Comment_Likes"));
				comment.setFlag(rs.getInt("Comment_Flag"));
				comment.setName(rs.getString("Comment_UserName"));

				return comment;
			}

		});
	}
	
	public List<Comment> getCommentsbymessageid(int messageid) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("messageid", messageid);

		return jdbc.query("select * from Comment_Table where Msg_Id=:messageid",params, new RowMapper<Comment>() {

			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comment comment = new Comment();

				comment.setId(rs.getInt("User_ID"));
				comment.setId(rs.getInt("Comment_Id"));
				comment.setMessageid(rs.getInt("Msg_Id"));
				comment.setText(rs.getString("Comment_Text"));
				comment.setTimestamp(rs.getDate("Comment_Time"));
				comment.setLike(rs.getInt("Comment_Likes"));
				comment.setFlag(rs.getInt("Comment_Flag"));

				return comment;
			}

		});
	}
	
	
	
	
	
	
	
	public boolean update(Comment comment) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		
		return jdbc.update("update Comment_Table set User_ID:userId Msg_Id:messageid, Comment_Text=:text, Comment_Time=:timestamp, Comment_Likes=:like, Comment_Flag=:flag, Comment_UserName=:name where Comment_Id=:id", params) == 1;
	}
	
	
	public boolean editcomment(int id,String commenttext) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("commenttext", commenttext);
		
		return jdbc.update("update Comment_Table set Comment_Text=:commenttext,Comment_Likes=0,Comment_Flag=0 where Comment_Id=:id", params) == 1;
	}
	
	
	
	
	public boolean create(Comment comment) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		
		return jdbc.update("insert into Comment_Table (User_ID,Comment_Id, Msg_Id, Comment_Text, Comment_Time, Comment_Likes, Comment_Flag, Comment_UserName) values (:userId,:id, :messageid, :text, :timestamp, :like, :flag, :name)", params) == 1;
	}
	
public boolean createbyMessageid(Comment comment, int messageid) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		
		
		return jdbc.update("insert into Comment_Table (User_ID,Comment_Id, Msg_Id, Comment_Text, Comment_Time, Comment_Likes, Comment_Flag, Comment_UserName) values (:userId,:id, :messageid, :text, :timestamp, :like, :flag, :name)", params) == 1;
	}
	
	
public boolean commentlikeinc(int id) {
	
	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("id", id);
	
	return jdbc.update("update Comment_Table set Comment_Likes=Comment_Likes+1 where Comment_Id=:id", params) == 1;
}

public boolean commentflaginc(int id) {
	
	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("id", id);
	
	return jdbc.update("update Comment_Table set Comment_Flag=Comment_Flag+1 where Comment_Id=:id", params) == 1;
}

public boolean commentdelete(int id) {
	
	MapSqlParameterSource params = new MapSqlParameterSource();
	params.addValue("id", id);
	
	return jdbc.update("delete from Comment_Table where Comment_Id=:id", params) == 1;
}



	
	@Transactional
	public int[] create(List<Comment> comments) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(comments.toArray());
		
		return jdbc.batchUpdate("insert into Comment_Table (User_ID,Comment_Id, Msg_Id, Comment_Text, Comment_Time, Comment_Likes, Comment_Flag, Comment_UserName) values (:userId,:id, :messageid, :text, :timestamp, :like, :flag, :name)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from Comment_Table where Comment_Id=:id", params) == 1;
	}

	public Comment getComment(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from Comment_Table where Comment_Id=:id", params,
				new RowMapper<Comment>() {

					public Comment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Comment comment = new Comment();

						comment.setId(rs.getInt("User_ID"));
						comment.setId(rs.getInt("Comment_Id"));
						comment.setMessageid(rs.getInt("Msg_Id"));
						comment.setText(rs.getString("Comment_Text"));
						comment.setTimestamp(rs.getDate("Comment_Time"));
						comment.setLike(rs.getInt("Comment_Likes"));
						comment.setFlag(rs.getInt("Comment_Flag"));
						comment.setName(rs.getString("Comment_UserName"));

						return comment;
					}

				});
	}
	
	
	
	
	
}
