package com.connect.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.connect.a.app.Sample.Sample;
import com.connect.a.app.Sample.SampleService;

@Controller
public class SampleController {
	
	private static final Logger log = Logger.getLogger(SampleController.class);
	
	private SampleService sampleService;
	
	@Autowired
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@RequestMapping("/sampleSession")
	public String samplePageWithSession(HttpSession session) {
		
		session.setAttribute("user", "Siddhardha");
		
		log.info("User " + session.getAttribute("user") + " logged in" );
		
		return "Sample/sample";
	}
	
	@RequestMapping("/sampleRequestMV")
	public ModelAndView samplePageWithRequest() {
		
		ModelAndView mv = new ModelAndView("Sample/sample");
		
		Map<String, Object> model = mv.getModel();
		
		model.put("user", "Raju");
		
		return mv;
	}
	
	@RequestMapping("/sampleRequestM")
	public String samplePage(Model model) {
		
		model.addAttribute("user", "<b>Mandapati</b>");
		
		return "Sample/sample";
	}
	
	@RequestMapping(value="/sampleParams", method=RequestMethod.GET)
	public String sampleParams(Model model, @RequestParam("user") String user) {
		
		model.addAttribute("user", user);
		
		return "Sample/sample";
	}
	
	@RequestMapping(value="/sampleParameters", method=RequestMethod.GET)
	public String sampleParamaters(Model model, @RequestParam(value="user", required=false, defaultValue="SRM") String user) {
		
		model.addAttribute("user", user);
		
		return "Sample/sample";
	}
	
	@RequestMapping("/sample")
	public String sample(Model model) {
		
		List<Sample> s = sampleService.getAllSamples();
		
		model.addAttribute("samples", s);
		
		return "Sample/sample";
	}
}
