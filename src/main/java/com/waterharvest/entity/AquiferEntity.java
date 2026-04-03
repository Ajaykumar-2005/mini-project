package com.waterharvest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aquifer_data")
public class AquiferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String district;

    @Column(name = "aquifer_type")
    private String aquiferType;

    @Column(name = "groundwater_level")
    private double groundwaterLevel;

    @Column(name = "annual_rainfall")
    private double annualRainfall;

    @Column(name = "soil_type")
    private String soilType;

    @Column(name = "runoff_coefficient")
    private double runoffCoefficient;

    @Column(name = "water_quality")
    private String waterQuality;

    public AquiferEntity() {}

    public AquiferEntity(String state, String district, String aquiferType, 
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
