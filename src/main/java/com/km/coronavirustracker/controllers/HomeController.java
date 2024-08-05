package com.km.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.km.coronavirustracker.models.LocationStats;
import com.km.coronavirustracker.services.CoronaVirusDataService;


@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService theCoronaVirusDataService;
	
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats> allStats = theCoronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
	
}
