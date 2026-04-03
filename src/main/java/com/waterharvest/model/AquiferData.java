package com.waterharvest.model;

public class AquiferData {

    private String state;
    private String district;
    private String aquiferType;
    private double groundwaterLevel;
    private double annualRainfall;
    private String soilType;
    private double runoffCoefficient;
    private String waterQuality;

    public AquiferData() {}

    public AquiferData(String state, String district, String aquiferType, 
                       double groundwaterLevel, double annualRainfall, 
                       String soilType, double runoffCoefficient, String waterQuality) {
        this.state = state;
        this.district = district;
        this.aquiferType = aquiferType;
        this.groundwaterLevel = groundwaterLevel;
        this.annualRainfall = annualRainfall;
        this.soilType = soilType;
        this.runoffCoefficient = runoffCoefficient;
        this.waterQuality = waterQuality;
    }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getAquiferType() { return aquiferType; }
    public void setAquiferType(String aquiferType) { this.aquiferType = aquiferType; }

    public double getGroundwaterLevel() { return groundwaterLevel; }
    public void setGroundwaterLevel(double groundwaterLevel) { this.groundwaterLevel = groundwaterLevel; }

    public double getAnnualRainfall() { return annualRainfall; }
    public void setAnnualRainfall(double annualRainfall) { this.annualRainfall = annualRainfall; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public double getRunoffCoefficient() { return runoffCoefficient; }
    public void setRunoffCoefficient(double runoffCoefficient) { this.runoffCoefficient = runoffCoefficient; }

    public String getWaterQuality() { return waterQuality; }
    public void setWaterQuality(String waterQuality) { this.waterQuality = waterQuality; }
}
