package com.waterharvest.controller;

import com.waterharvest.model.*;
import com.waterharvest.service.AssessmentService;
import com.waterharvest.service.AquiferDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private AquiferDataService aquiferDataService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("states", aquiferDataService.getAvailableStates());
        return "index";
    }

    @PostMapping("/assess")
    @ResponseBody
    public ResponseEntity<?> assess(@RequestBody UserInput input) {
        try {
            System.out.println("Received assessment request: " + input);
            
            if (input.getName() == null || input.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
            }
            if (input.getState() == null || input.getState().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "State is required"));
            }
            if (input.getRoofType() == null || input.getRoofType().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Roof type is required"));
            }
            
            AssessmentResult result = assessmentService.assess(input);
            System.out.println("Assessment completed: " + result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error processing assessment: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/api/districts/{state}")
    @ResponseBody
    public List<String> getDistricts(@PathVariable String state) {
        return aquiferDataService.getDistrictsByState(state);
    }

    @GetMapping("/api/states")
    @ResponseBody
    public List<String> getStates() {
        return aquiferDataService.getAvailableStates();
    }
}