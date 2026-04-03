package com.waterharvest.controller;

import com.waterharvest.entity.AssessmentHistory;
import com.waterharvest.model.*;
import com.waterharvest.repository.AssessmentHistoryRepository;
import com.waterharvest.service.AssessmentService;
import com.waterharvest.service.AquiferDataService;
import com.waterharvest.service.PdfReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private AquiferDataService aquiferDataService;

    @Autowired
    private AssessmentHistoryRepository historyRepository;

    @Autowired
    private PdfReportService pdfReportService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("states", aquiferDataService.getAvailableStates());
        return "index";
    }

    @GetMapping("/history")
    public String historyPage() {
        return "history";
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

    @GetMapping("/api/history")
    @ResponseBody
    public List<AssessmentHistory> getHistory() {
        return historyRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/api/generate-pdf")
    @ResponseBody
    public ResponseEntity<?> generatePdf(@RequestBody Map<String, Object> data) {
        try {
            UserInput input = new UserInput();
            input.setName((String) data.getOrDefault("name", "User"));
            input.setState((String) data.get("state"));
            input.setDistrict((String) data.get("district"));
            input.setLocationType((String) data.get("locationType"));
            input.setAreaType((String) data.get("areaType"));
            input.setNumberOfDwellers(((Number) data.get("numberOfDwellers")).intValue());
            input.setRoofArea(((Number) data.get("roofArea")).doubleValue());
            input.setRoofType((String) data.get("roofType"));
            input.setOpenSpace(((Number) data.get("openSpace")).doubleValue());
            input.setSoilType((String) data.get("soilType"));

            AssessmentResult result = new AssessmentResult();
            result.setFeasible((Boolean) data.get("isFeasible"));
            result.setRecommendedStructure((String) data.get("recommendedStructure"));
            result.setRunoffGenerated(((Number) data.get("runoffGenerated")).doubleValue());
            result.setWaterDemand(((Number) data.get("waterDemand")).doubleValue());
            result.setSurplusWater(((Number) data.get("surplusWater")).doubleValue());
            result.setAnnualRainfall(((Number) data.get("annualRainfall")).doubleValue());
            result.setMessage((String) data.get("message"));

            com.waterharvest.model.Dimensions dims = new com.waterharvest.model.Dimensions();
            dims.setType((String) data.get("dimType"));
            dims.setLength(((Number) data.get("dimLength")).doubleValue());
            dims.setWidth(((Number) data.get("dimWidth")).doubleValue());
            dims.setDepth(((Number) data.get("dimDepth")).doubleValue());
            dims.setCapacity(((Number) data.get("dimCapacity")).doubleValue());
            result.setDimensions(dims);

            com.waterharvest.model.CostEstimation cost = new com.waterharvest.model.CostEstimation();
            cost.setStructureCost(((Number) data.get("structureCost")).doubleValue());
            cost.setPlumbingCost(((Number) data.get("plumbingCost")).doubleValue());
            cost.setFiltrationCost(((Number) data.get("filtrationCost")).doubleValue());
            cost.setTotalCost(((Number) data.get("totalCost")).doubleValue());
            cost.setPaybackPeriod(((Number) data.get("paybackPeriod")).doubleValue());
            result.setCostEstimation(cost);

            com.waterharvest.model.EnvironmentalImpact env = new com.waterharvest.model.EnvironmentalImpact();
            env.setWaterSaved20Years(((Number) data.get("waterSaved20Years")).doubleValue());
            env.setCo2Reduction(((Number) data.get("co2Reduction")).doubleValue());
            env.setCostSavings20Years(((Number) data.get("costSavings20Years")).doubleValue());
            result.setEnvironmentalImpact(env);

            com.waterharvest.model.AquiferData aq = new com.waterharvest.model.AquiferData();
            aq.setAquiferType((String) data.get("aquiferType"));
            aq.setGroundwaterLevel(((Number) data.get("groundwaterLevel")).doubleValue());
            aq.setRunoffCoefficient(((Number) data.get("runoffCoefficient")).doubleValue());
            aq.setSoilType((String) data.get("aquiferSoilType"));
            result.setAquiferData(aq);

            byte[] pdfBytes = pdfReportService.generateReport(input, result);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "JalDhara_Assessment_Report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }
}