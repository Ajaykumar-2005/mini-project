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
            input.setState((String) data.getOrDefault("state", ""));
            input.setDistrict((String) data.getOrDefault("district", ""));
            input.setLocationType((String) data.getOrDefault("locationType", "urban"));
            input.setAreaType((String) data.getOrDefault("areaType", "town"));
            input.setNumberOfDwellers(data.get("numberOfDwellers") != null ? ((Number) data.get("numberOfDwellers")).intValue() : 4);
            input.setRoofArea(data.get("roofArea") != null ? ((Number) data.get("roofArea")).doubleValue() : 100.0);
            input.setRoofType((String) data.getOrDefault("roofType", "concrete"));
            input.setOpenSpace(data.get("openSpace") != null ? ((Number) data.get("openSpace")).doubleValue() : 20.0);
            input.setSoilType((String) data.getOrDefault("soilType", "alluvial"));

            AssessmentResult result = new AssessmentResult();
            result.setFeasible(data.get("isFeasible") != null ? (Boolean) data.get("isFeasible") : true);
            result.setRecommendedStructure((String) data.getOrDefault("recommendedStructure", "Recharge Pit"));
            result.setRunoffGenerated(data.get("runoffGenerated") != null ? ((Number) data.get("runoffGenerated")).doubleValue() : 0.0);
            result.setWaterDemand(data.get("waterDemand") != null ? ((Number) data.get("waterDemand")).doubleValue() : 0.0);
            result.setSurplusWater(data.get("surplusWater") != null ? ((Number) data.get("surplusWater")).doubleValue() : 0.0);
            result.setAnnualRainfall(data.get("annualRainfall") != null ? ((Number) data.get("annualRainfall")).doubleValue() : 0.0);
            result.setMessage((String) data.getOrDefault("message", ""));

            StructureDimensions dims = new StructureDimensions();
            dims.setType((String) data.getOrDefault("dimType", "Recharge Pit"));
            dims.setLength(data.get("dimLength") != null ? ((Number) data.get("dimLength")).doubleValue() : 1.0);
            dims.setWidth(data.get("dimWidth") != null ? ((Number) data.get("dimWidth")).doubleValue() : 1.0);
            dims.setDepth(data.get("dimDepth") != null ? ((Number) data.get("dimDepth")).doubleValue() : 1.0);
            dims.setCapacity(data.get("dimCapacity") != null ? ((Number) data.get("dimCapacity")).doubleValue() : 1.0);
            result.setDimensions(dims);

            CostEstimation cost = new CostEstimation();
            cost.setStructureCost(data.get("structureCost") != null ? ((Number) data.get("structureCost")).doubleValue() : 0.0);
            cost.setPlumbingCost(data.get("plumbingCost") != null ? ((Number) data.get("plumbingCost")).doubleValue() : 0.0);
            cost.setFiltrationCost(data.get("filtrationCost") != null ? ((Number) data.get("filtrationCost")).doubleValue() : 0.0);
            cost.setTotalCost(data.get("totalCost") != null ? ((Number) data.get("totalCost")).doubleValue() : 0.0);
            cost.setPaybackPeriod(data.get("paybackPeriod") != null ? ((Number) data.get("paybackPeriod")).doubleValue() : 0.0);
            result.setCostEstimation(cost);

            result.setEnvironmentalImpact(data.get("co2Reduction") != null ? ((Number) data.get("co2Reduction")).doubleValue() : 0.0);

            result.setAquiferType((String) data.getOrDefault("aquiferType", "Alluvial"));
            result.setGroundwaterLevel(data.get("groundwaterLevel") != null ? ((Number) data.get("groundwaterLevel")).doubleValue() : 10.0);

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