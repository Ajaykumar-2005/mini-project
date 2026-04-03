package com.waterharvest.model;

public class UserInput {

    private String name;
    private String state;
    private String district;
    private String locationType;
    private Integer numberOfDwellers;
    private Double roofArea;
    private String roofType;
    private Double openSpace;
    private String soilType;
    private String language = "en";

    public UserInput() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}