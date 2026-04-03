package com.waterharvest.model;

public class AssessmentResult {

    private boolean feasible;
    private double annualRainfall;
    private double waterDemand;
    private double runoffGeneration;
    private double surplusWater;
    private String aquiferType;
    private double groundwaterLevel;
    private String recommendedStructure;
    private String structureDescription;
    private StructureDimensions dimensions;
    private CostEstimation costEstimation;
    private String feasibilityMessage;
    private double environmentalImpact;

    public AssessmentResult() {}

    public boolean isFeasible() { return feasible; }
    public void setFeasible(boolean feasible) { this.feasible = feasible; }

    public double getAnnualRainfall() { return annualRainfall; }
    public void setAnnualRainfall(double annualRainfall) { this.annualRainfall = annualRainfall; }

    public double getWaterDemand() { return waterDemand; }
    public void setWaterDemand(double waterDemand) { this.waterDemand = waterDemand; }

    public double getRunoffGeneration() { return runoffGeneration; }
    public void setRunoffGeneration(double runoffGeneration) { this.runoffGeneration = runoffGeneration; }

    public double getSurplusWater() { return surplusWater; }
    public void setSurplusWater(double surplusWater) { this.surplusWater = surplusWater; }

    public String getAquiferType() { return aquiferType; }
    public void setAquiferType(String aquiferType) { this.aquiferType = aquiferType; }

    public double getGroundwaterLevel() { return groundwaterLevel; }
    public void setGroundwaterLevel(double groundwaterLevel) { this.groundwaterLevel = groundwaterLevel; }

    public String getRecommendedStructure() { return recommendedStructure; }
    public void setRecommendedStructure(String recommendedStructure) { this.recommendedStructure = recommendedStructure; }

    public String getStructureDescription() { return structureDescription; }
    public void setStructureDescription(String structureDescription) { this.structureDescription = structureDescription; }

    public StructureDimensions getDimensions() { return dimensions; }
    public void setDimensions(StructureDimensions dimensions) { this.dimensions = dimensions; }

    public CostEstimation getCostEstimation() { return costEstimation; }
    public void setCostEstimation(CostEstimation costEstimation) { this.costEstimation = costEstimation; }

    public String getFeasibilityMessage() { return feasibilityMessage; }
    public void setFeasibilityMessage(String feasibilityMessage) { this.feasibilityMessage = feasibilityMessage; }

    public double getEnvironmentalImpact() { return environmentalImpact; }
    public void setEnvironmentalImpact(double environmentalImpact) { this.environmentalImpact = environmentalImpact; }
}
