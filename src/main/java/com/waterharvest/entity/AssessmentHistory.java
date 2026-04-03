package com.waterharvest.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_history")
public class AssessmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String district;

    @Column(name = "location_type")
    private String locationType;

    @Column(name = "number_of_dwellers")
    private Integer numberOfDwellers;

    @Column(name = "roof_area")
    private Double roofArea;

    @Column(name = "roof_type")
    private String roofType;

    @Column(name = "open_space")
    private Double openSpace;

    @Column(name = "soil_type")
    private String soilType;

    @Column(name = "annual_rainfall")
    private Double annualRainfall;

    @Column(name = "water_demand")
    private Double waterDemand;

    @Column(name = "runoff_generated")
    private Double runoffGenerated;

    @Column(name = "surplus_water")
    private Double surplusWater;

    @Column(name = "recommended_structure")
    private String recommendedStructure;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "payback_period")
    private Double paybackPeriod;

    @Column(name = "is_feasible")
    private Boolean isFeasible;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public AssessmentHistory() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getLocationType() { return locationType; }
    public void setLocationType(String locationType) { this.locationType = locationType; }

    public Integer getNumberOfDwellers() { return numberOfDwellers; }
    public void setNumberOfDwellers(Integer numberOfDwellers) { this.numberOfDwellers = numberOfDwellers; }

    public Double getRoofArea() { return roofArea; }
    public void setRoofArea(Double roofArea) { this.roofArea = roofArea; }

    public String getRoofType() { return roofType; }
    public void setRoofType(String roofType) { this.roofType = roofType; }

    public Double getOpenSpace() { return openSpace; }
    public void setOpenSpace(Double openSpace) { this.openSpace = openSpace; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public Double getAnnualRainfall() { return annualRainfall; }
    public void setAnnualRainfall(Double annualRainfall) { this.annualRainfall = annualRainfall; }

    public Double getWaterDemand() { return waterDemand; }
    public void setWaterDemand(Double waterDemand) { this.waterDemand = waterDemand; }

    public Double getRunoffGenerated() { return runoffGenerated; }
    public void setRunoffGenerated(Double runoffGenerated) { this.runoffGenerated = runoffGenerated; }

    public Double getSurplusWater() { return surplusWater; }
    public void setSurplusWater(Double surplusWater) { this.surplusWater = surplusWater; }

    public String getRecommendedStructure() { return recommendedStructure; }
    public void setRecommendedStructure(String recommendedStructure) { this.recommendedStructure = recommendedStructure; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

    public Double getPaybackPeriod() { return paybackPeriod; }
    public void setPaybackPeriod(Double paybackPeriod) { this.paybackPeriod = paybackPeriod; }

    public Boolean getIsFeasible() { return isFeasible; }
    public void setIsFeasible(Boolean isFeasible) { this.isFeasible = isFeasible; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
