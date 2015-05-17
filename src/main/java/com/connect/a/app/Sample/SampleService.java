package com.connect.a.app.Sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sampleService")
public class SampleService {
	
	private SampleDAO sampleDAO;
	
	@Autowired
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}

	public List<Sample> getAllSamples() {
		
		return sampleDAO.getAllSamples();
	}
}
