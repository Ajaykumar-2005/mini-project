package com.waterharvest.model;

public class StructureDimensions {

    private String structureType;
    private String type;
    private double length;
    private double width;
    private double depth;
    private double diameter;
    private double tankCapacity;
    private double capacity;
    private String unit;

    public StructureDimensions() {
        this.unit = "meters";
    }

    public String getStructureType() { return structureType; }
    public void setStructureType(String structureType) { this.structureType = structureType; }
    
    public String getType() { return type != null ? type : structureType; }
    public void setType(String type) { this.type = type; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getDepth() { return depth; }
    public void setDepth(double depth) { this.depth = depth; }

    public double getDiameter() { return diameter; }
    public void setDiameter(double diameter) { this.diameter = diameter; }

    public double getTankCapacity() { return tankCapacity; }
    public void setTankCapacity(double tankCapacity) { this.tankCapacity = tankCapacity; }
    
    public double getCapacity() { return capacity > 0 ? capacity : tankCapacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
