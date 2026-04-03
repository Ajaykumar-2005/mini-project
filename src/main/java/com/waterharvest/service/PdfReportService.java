package com.waterharvest.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.waterharvest.model.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfReportService {

    public byte[] generateReport(UserInput input, AssessmentResult result) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD, new java.awt.Color(0, 102, 153));
            Font headerFont = new Font(Font.HELVETICA, 14, Font.BOLD, new java.awt.Color(0, 102, 153));
            Font normalFont = new Font(Font.HELVETICA, 11, Font.NORMAL, java.awt.Color.BLACK);
            Font boldFont = new Font(Font.HELVETICA, 11, Font.BOLD, java.awt.Color.BLACK);

            Paragraph title = new Paragraph("JalDhara - Rainwater Harvesting Assessment Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            document.add(new Paragraph(" ", normalFont));

            document.add(new Paragraph("1. USER DETAILS", headerFont));
            document.add(new Paragraph("Name: " + (input.getName() != null ? input.getName() : "User"), normalFont));
            document.add(new Paragraph("Location: " + input.getDistrict() + ", " + input.getState(), normalFont));
            document.add(new Paragraph("Area Type: " + (input.getAreaType() != null ? input.getAreaType() : "Town"), normalFont));
            document.add(new Paragraph("Location Type: " + (input.getLocationType() != null ? input.getLocationType() : "Urban"), normalFont));

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph("2. PROPERTY DETAILS", headerFont));
            document.add(new Paragraph("Number of Dwellers: " + input.getNumberOfDwellers(), normalFont));
            document.add(new Paragraph("Roof Area: " + input.getRoofArea() + " sq. meters", normalFont));
            document.add(new Paragraph("Roof Type: " + input.getRoofType(), normalFont));
            document.add(new Paragraph("Open Space: " + input.getOpenSpace() + " sq. meters", normalFont));
            document.add(new Paragraph("Soil Type: " + input.getSoilType(), normalFont));

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph("3. ASSESSMENT RESULTS", headerFont));

            if (result.isFeasible()) {
                document.add(new Paragraph("Status: FEASIBLE", boldFont));
                document.add(new Paragraph("Recommended Structure: " + (result.getRecommendedStructure() != null ? result.getRecommendedStructure() : "N/A"), normalFont));
                document.add(new Paragraph("Annual Runoff Generated: " + String.format("%.2f", result.getRunoffGenerated()) + " KL/year", normalFont));
                document.add(new Paragraph("Water Demand: " + String.format("%.2f", result.getWaterDemand()) + " KL/year", normalFont));
                document.add(new Paragraph("Surplus Water: " + String.format("%.2f", result.getSurplusWater()) + " KL/year", normalFont));
                document.add(new Paragraph("Annual Rainfall: " + String.format("%.0f", result.getAnnualRainfall()) + " mm/year", normalFont));
            } else {
                document.add(new Paragraph("Status: NOT FEASIBLE", boldFont));
                document.add(new Paragraph("Reason: " + (result.getMessage() != null ? result.getMessage() : "Insufficient data for assessment"), normalFont));
            }

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph("4. STRUCTURE DIMENSIONS", headerFont));
            if (result.getDimensions() != null) {
                StructureDimensions dims = result.getDimensions();
                document.add(new Paragraph("Type: " + (dims.getType() != null ? dims.getType() : "Recharge Pit"), normalFont));
                document.add(new Paragraph("Length: " + String.format("%.2f", dims.getLength()) + " meters", normalFont));
                document.add(new Paragraph("Width: " + String.format("%.2f", dims.getWidth()) + " meters", normalFont));
                document.add(new Paragraph("Depth: " + String.format("%.2f", dims.getDepth()) + " meters", normalFont));
                document.add(new Paragraph("Capacity: " + String.format("%.2f", dims.getCapacity()) + " KL", normalFont));
            }

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph("5. COST ESTIMATION", headerFont));
            if (result.getCostEstimation() != null) {
                CostEstimation cost = result.getCostEstimation();
                document.add(new Paragraph("Structure Cost: Rs. " + String.format("%.0f", cost.getStructureCost()), normalFont));
                document.add(new Paragraph("Plumbing Cost: Rs. " + String.format("%.0f", cost.getPlumbingCost()), normalFont));
                document.add(new Paragraph("Filtration Cost: Rs. " + String.format("%.0f", cost.getFiltrationCost()), normalFont));
                document.add(new Paragraph("Total Cost: Rs. " + String.format("%.0f", cost.getTotalCost()), normalFont));
                document.add(new Paragraph("Payback Period: " + String.format("%.1f", cost.getPaybackPeriod()) + " years", normalFont));
            }

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph("6. ENVIRONMENTAL IMPACT", headerFont));
            double co2 = result.getEnvironmentalImpact();
            document.add(new Paragraph("CO2 Reduction: " + String.format("%.2f", co2) + " kg/year", normalFont));
            if (result.getCostEstimation() != null) {
                document.add(new Paragraph("20-Year Savings: Rs. " + String.format("%.0f", result.getCostEstimation().getSavingsOver20Years()), normalFont));
            }

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph("7. AQUIFER INFORMATION", headerFont));
            document.add(new Paragraph("Aquifer Type: " + (result.getAquiferType() != null ? result.getAquiferType() : "Unknown"), normalFont));
            document.add(new Paragraph("Groundwater Level: " + String.format("%.2f", result.getGroundwaterLevel()) + " meters below ground level", normalFont));

            document.add(new Paragraph(" ", normalFont));
            document.add(new Paragraph(" ", normalFont));

            Paragraph footer = new Paragraph("This report is generated by JalDhara - Rainwater Harvesting Assessment Tool", normalFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            Paragraph footer2 = new Paragraph("Based on CGWB Guidelines and Scientific Parameters", normalFont);
            footer2.setAlignment(Element.ALIGN_CENTER);
            document.add(footer2);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}
