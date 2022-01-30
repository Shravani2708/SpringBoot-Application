package com.springbootproject.coronovirusTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbootproject.coronovirusTracker.models.LocationStats;
import com.springbootproject.coronovirusTracker.service.cornoVirusDataServices;

@Controller
//@RESTController -to return the data in JSON response
public class HomeController {
	
	//to access the service class..use autowire
	@Autowired
	cornoVirusDataServices cornoVirusDataServicesobj;
	
	//to return the data html ui using model object so that u can pass data to html page
	@GetMapping("/")
	public String home(Model model) {
		//to get the value present in allStats in service 
		//model.addAttribute("locationStats" ,cornoVirusDataServicesobj.getAllStats() );
		List<LocationStats> allStats = cornoVirusDataServicesobj.getAllStats();
		
		//convert list of objects to stream and mapping to int...each objects maps to int values and finally do sum
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}

}
