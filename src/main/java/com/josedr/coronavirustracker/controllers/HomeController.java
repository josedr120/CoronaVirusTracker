package com.josedr.coronavirustracker.controllers;

import com.josedr.coronavirustracker.models.LocationStats;
import com.josedr.coronavirustracker.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataServices.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();

        model.addAttribute("locationStat", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
