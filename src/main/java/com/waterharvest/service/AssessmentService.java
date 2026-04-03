package com.waterharvest.service;

import com.waterharvest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    @Autowired
    private AquiferDataService aquiferDataService;

    public AssessmentResult assess(UserInput input) {
        System.out.println("Processing assessment for: " + input.getName() + ", State: " + input.getState());
        
        AssessmentResult result = new AssessmentResult();

        String state = input.getState() != null ? input.getState() : "Unknown";
        String district = input.getDistrict() != null ? input.getDistrict() : state;
        
        AquiferData aquiferData = aquiferDataService.getAquiferData(state, district);
        
        result.setAquiferType(aquiferData.getAquiferType());
        result.setGroundwaterLevel(aquiferData.getGroundwaterLevel());
        result.setAnnualRainfall(aquiferData.getAnnualRainfall());

        double roofArea = input.getRoofArea() != null ? input.getRoofArea() : 100.0;
        String roofType = input.getRoofType() != null ? input.getRoofType() : "concrete";
        
        double roofRunoffCoeff = getRoofRunoffCoefficient(roofType);
        double runoff = roofArea * aquiferData.getAnnualRainfall() * roofRunoffCoeff / 1000;
        result.setRunoffGeneration(Math.round(runoff * 100.0) / 100.0);

        int dwellers = input.getNumberOfDwellers() != null ? input.getNumberOfDwellers() : 4;
        double waterDemand = dwellers * 135 * 365 / 1000.0;
        result.setWaterDemand(Math.round(waterDemand * 100.0) / 100.0);

        double surplus = runoff - waterDemand;
        result.setSurplusWater(Math.round(surplus * 100.0) / 100.0);

        boolean feasible = runoff >= waterDemand * 0.3;
        result.setFeasible(feasible);

        if (feasible) {
            result.setFeasibilityMessage("Excellent! Your location is suitable for Rainwater Harvesting. " +
                "You can harvest " + Math.round(runoff) + " KL of rainwater annually.");
        } else {
            result.setFeasibilityMessage("Moderate potential. With proper storage and filtration, " +
                "you can still benefit from rainwater harvesting for non-potable uses.");
        }

        double openSpace = input.getOpenSpace() != null ? input.getOpenSpace() : 20.0;
        recommendStructure(result, openSpace, runoff, aquiferData);

        result.setEnvironmentalImpact(Math.round(runoff * 0.4 * 100.0) / 100.0);

        System.out.println("Assessment complete. Feasible: " + feasible + ", Runoff: " + runoff + " KL");
        
        return result;
    }

    private double getRoofRunoffCoefficient(String roofType) {
        if (roofType == null) return 0.75;
        switch (roofType.toLowerCase()) {
            case "concrete": return 0.85;
            case "tile": return 0.75;
            case "metal": return 0.90;
            case "asbestos": return 0.80;
            case "thatched": return 0.30;
            default: return 0.75;
        }
    }

    private void recommendStructure(AssessmentResult result, double openSpace, double runoff, AquiferData aquiferData) {
        StructureDimensions dimensions = new StructureDimensions();
        double gwl = aquiferData.getGroundwaterLevel();
        String soilType = aquiferData.getSoilType() != null ? aquiferData.getSoilType().toLowerCase() : "alluvial";

        if (openSpace >= 50 && gwl > 15) {
            result.setRecommendedStructure("Recharge Pit with Storage Tank");
            result.setStructureDescription("A recharge pit is ideal for your location as groundwater level is deep.");
            
            double area = Math.min(openSpace * 0.1, 10);
            dimensions.setStructureType("Rectangular Pit");
            dimensions.setLength(Math.round(Math.sqrt(area) * 100.0) / 100.0);
            dimensions.setWidth(dimensions.getLength());
            dimensions.setDepth(3.0);
            dimensions.setTankCapacity(Math.round(runoff * 0.5));
            
        } else if (openSpace >= 20) {
            result.setRecommendedStructure("Recharge Trench");
            result.setStructureDescription("A recharge trench is suitable for your available space.");
            
            double trenchLength = Math.min(openSpace * 0.5, 15);
            dimensions.setStructureType("Linear Trench");
            dimensions.setLength(trenchLength);
            dimensions.setWidth(1.5);
            dimensions.setDepth(2.5);
            dimensions.setTankCapacity(Math.round(runoff * 0.3));
            
        } else if (gwl < 10 && openSpace >= 10) {
            result.setRecommendedStructure("Recharge Shaft");
            result.setStructureDescription("A recharge shaft is best suited for areas with shallow groundwater.");
            
            dimensions.setStructureType("Cylindrical Shaft");
            dimensions.setDiameter(1.0);
            dimensions.setDepth(Math.max(gwl - 2, 1));
            dimensions.setTankCapacity(Math.round(runoff * 0.2));
            
        } else if (openSpace < 10) {
            result.setRecommendedStructure("Rooftop Rainwater Storage Tank");
            result.setStructureDescription("Since open space is limited, a storage tank system is recommended.");
            
            double tankVolume = Math.min(runoff * 0.6, 10);
            dimensions.setStructureType("Cylindrical Tank");
            dimensions.setDiameter(1.6);
            dimensions.setDepth(Math.round((tankVolume / (Math.PI * 0.8 * 0.8)) * 100.0) / 100.0);
            dimensions.setTankCapacity(Math.round(tankVolume));
            
        } else {
            result.setRecommendedStructure("Percolation Tank");
            result.setStructureDescription("A percolation tank is recommended to maximize groundwater recharge.");
            
            dimensions.setStructureType("Percolation Tank");
            dimensions.setLength(5.0);
            dimensions.setWidth(4.0);
            dimensions.setDepth(2.0);
            dimensions.setTankCapacity(20);
        }

        result.setDimensions(dimensions);
        result.setCostEstimation(calculateCost(result, runoff));
    }

    private CostEstimation calculateCost(AssessmentResult result, double runoff) {
        CostEstimation cost = new CostEstimation();
        double baseCost = 0;
        String structureType = result.getRecommendedStructure();

        if (structureType != null && structureType.contains("Recharge Pit")) {
            double volume = result.getDimensions().getLength() * result.getDimensions().getWidth() * result.getDimensions().getDepth();
            baseCost = volume * 3500;
            cost.setPlumbingCost(8000);
            cost.setFiltrationCost(12000);
        } else if (structureType != null && structureType.contains("Trench")) {
            double volume = result.getDimensions().getLength() * result.getDimensions().getWidth() * result.getDimensions().getDepth();
            baseCost = volume * 3000;
            cost.setPlumbingCost(6000);
            cost.setFiltrationCost(10000);
        } else if (structureType != null && structureType.contains("Shaft")) {
            double volume = Math.PI * Math.pow(result.getDimensions().getDiameter() / 2, 2) * result.getDimensions().getDepth();
            baseCost = volume * 4000;
            cost.setPlumbingCost(5000);
            cost.setFiltrationCost(8000);
        } else if (structureType != null && structureType.contains("Storage Tank")) {
            baseCost = result.getDimensions().getTankCapacity() * 2500;
            cost.setPlumbingCost(10000);
            cost.setFiltrationCost(15000);
        } else {
            baseCost = 25000;
            cost.setPlumbingCost(7000);
            cost.setFiltrationCost(10000);
        }

        cost.setStructureCost(Math.round(baseCost));
        cost.setTotalCost(Math.round(cost.getStructureCost() + cost.getPlumbingCost() + cost.getFiltrationCost()));
        cost.setAnnualMaintenance(Math.round(cost.getTotalCost() * 0.05));

        double waterSavingKL = runoff;
        double waterRate = 50;
        cost.setAnnualWaterSaving(waterSavingKL);
        cost.setWaterSavingValue(Math.round(waterSavingKL * waterRate));

        if (cost.getWaterSavingValue() > cost.getAnnualMaintenance()) {
            cost.setPaybackPeriod(Math.round(cost.getTotalCost() / (cost.getWaterSavingValue() - cost.getAnnualMaintenance()) * 10.0) / 10.0);
        } else {
            cost.setPaybackPeriod(20);
        }

        cost.setSavingsOver20Years(Math.round((cost.getWaterSavingValue() * 20) - (cost.getAnnualMaintenance() * 20) - cost.getTotalCost()));

        return cost;
    }
}