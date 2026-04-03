package com.waterharvest.model;

public class CostEstimation {

    private double structureCost;
    private double plumbingCost;
    private double filtrationCost;
    private double totalCost;
    private double annualMaintenance;
    private double annualWaterSaving;
    private double waterSavingValue;
    private double paybackPeriod;
    private double savingsOver20Years;

    public CostEstimation() {}

    public double getStructureCost() { return structureCost; }
    public void setStructureCost(double structureCost) { this.structureCost = structureCost; }

    public double getPlumbingCost() { return plumbingCost; }
    public void setPlumbingCost(double plumbingCost) { this.plumbingCost = plumbingCost; }

    public double getFiltrationCost() { return filtrationCost; }
    public void setFiltrationCost(double filtrationCost) { this.filtrationCost = filtrationCost; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public double getAnnualMaintenance() { return annualMaintenance; }
    public void setAnnualMaintenance(double annualMaintenance) { this.annualMaintenance = annualMaintenance; }

    public double getAnnualWaterSaving() { return annualWaterSaving; }
    public void setAnnualWaterSaving(double annualWaterSaving) { this.annualWaterSaving = annualWaterSaving; }

    public double getWaterSavingValue() { return waterSavingValue; }
    public void setWaterSavingValue(double waterSavingValue) { this.waterSavingValue = waterSavingValue; }

    public double getPaybackPeriod() { return paybackPeriod; }
    public void setPaybackPeriod(double paybackPeriod) { this.paybackPeriod = paybackPeriod; }

    public double getSavingsOver20Years() { return savingsOver20Years; }
    public void setSavingsOver20Years(double savingsOver20Years) { this.savingsOver20Years = savingsOver20Years; }
}
