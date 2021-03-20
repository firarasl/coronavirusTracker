package com.coronavirus.demo.controller;

import com.coronavirus.demo.model.LocationStats;
import com.coronavirus.demo.service.CoronavirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CoronavirusController {

    @Autowired
    CoronavirusService coronavirusService;

    @GetMapping("/")
    public String homePage(Model model){
        List<LocationStats> allStatistics = coronavirusService.getAllStatistics();
        long totalActive = (long) allStatistics.stream().mapToDouble(
                LocationStats::getActive
        ).sum();

        long totalDeath = (long) allStatistics.stream().mapToDouble(
                LocationStats::getDeath
        ).sum();
        long totalRecovered = (long) allStatistics.stream().mapToDouble(
                LocationStats::getRecovered
        ).sum();

        model.addAttribute("totalDeath", totalDeath);
        model.addAttribute("totalActive", totalActive);
        model.addAttribute("totalRecovered", totalRecovered);
        model.addAttribute("allStatistics", allStatistics);
        return "home";
    }
}
