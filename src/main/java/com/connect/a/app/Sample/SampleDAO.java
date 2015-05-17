package com.connect.a.app.Sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("sampleDAO")
public class SampleDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Sample> getAllSamples() {
		
		return jdbc.query("select * from sample", new RowMapper<Sample>() {

			@Override
			public Sample mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Sample s = new Sample();
				
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				
				return s;
			}
		});
	}
}
