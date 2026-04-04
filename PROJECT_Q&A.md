# JalDhara - Project Q&A Reference
## Rainwater Harvesting Assessment Tool

---

## 1. Project Overview

**Q: What is this project?**
> JalDhara is a web-based tool that assesses the feasibility of rooftop rainwater harvesting (RWH) for buildings in India. It provides structure recommendations, cost estimation, and environmental impact analysis.

**Q: What is Rainwater Harvesting?**
> Rainwater harvesting is the process of collecting and storing rainwater for later use, rather than letting it runoff. This reduces dependency on groundwater and promotes water conservation.

**Q: What technologies are used?**
> - Backend: Spring Boot (Java 17)
> - Database: H2 (In-memory)
> - Frontend: HTML, CSS, JavaScript
> - PDF: OpenPDF library

---

## 2. Key Features

**Q: What features does the tool provide?**
> 1. Feasibility Check - Determines if RWH is viable
> 2. Aquifer Database - 30+ Indian districts with groundwater data
> 3. Structure Recommendations - Recharge pit, trench, shaft, or storage tank
> 4. Cost Estimation - Location-based pricing
> 5. Environmental Impact - CO₂ reduction calculation
> 6. PDF Report - Downloadable assessment report

**Q: How is feasibility determined?**
> Feasible if: Runoff ≥ 30% of Water Demand

---

## 3. Formulas Used

### Runoff Calculation
```
Runoff (KL/year) = Roof Area (m²) × Annual Rainfall (mm) × Runoff Coefficient / 1000
```

### Water Demand
```
Water Demand (KL/year) = Dwellers × 135 LPCD × 365 days / 1000
```

### Surplus Water
```
Surplus Water = Runoff - Water Demand
```

### CO₂ Reduction
```
CO₂ Reduction (kg/year) = Surplus Water × 0.4
```

### 20-Year Savings
```
20-Year Savings = (Surplus × ₹50 × 20) - (₹2,000 × 20) - Total Cost
```

### Runoff Coefficients by Roof Type
| Roof Type | Coefficient |
|----------|-------------|
| Metal Sheet | 0.90 |
| Concrete/RCC | 0.85 |
| Asbestos | 0.80 |
| Clay Tile | 0.75 |
| Thatched | 0.30 |

### Cost Multipliers by Location
| Area Type | Multiplier |
|-----------|------------|
| Metro | 1.35x |
| Tier-2 City | 1.15x |
| Town | 1.00x |
| Village | 0.85x |

---

## 4. Technical Questions

**Q: Why H2 database instead of MySQL?**
> H2 is an in-memory database that requires no setup. It's perfect for demos and student projects. Data resets on app restart.

**Q: How does the form wizard work?**
> JavaScript validates each step. If valid, it hides current section and shows next. Uses CSS classes (.form-step, .active) to control visibility.

**Q: How is PDF generated?**
> The OpenPDF library creates a PDF document on the server side. When user clicks "Download PDF", the file is generated and sent to the browser.

**Q: What is the database schema?**
> Two tables:
> - AQUIFER_DATA: stores district-wise groundwater data
> - ASSESSMENT_HISTORY: stores all user submissions

**Q: How to access the database?**
> - History Page: http://localhost:9090/history
> - API: http://localhost:9090/api/history
> - H2 Console: http://localhost:9090/h2-console (JDBC: jdbc:h2:mem:rtrwh_db)

---

## 5. Scientific Questions

**Q: What is 135 LPCD?**
> Liters Per Capita Per Day - The Indian standard for estimating water demand. 135 LPCD is the recommended daily water requirement per person.

**Q: Why CO₂ reduction = Runoff × 0.4?**
> Based on energy saved by not pumping groundwater. Approximately 0.35-0.4 kg of CO₂ emissions are avoided for every KL of rainwater harvested.

**Q: What is aquifer type?**
> The geological formation that stores groundwater. Types include:
> - Alluvial: Sandy soil, high water storage
> - Basalt: Hard rock, moderate storage
> - Granite: Low storage, fractured rock

**Q: What is runoff coefficient?**
> The percentage of rainfall that becomes runoff. Smooth surfaces (metal) have high coefficients (0.90), while absorbent surfaces (thatch) have low coefficients (0.30).

**Q: What is percolation?**
> The process of water filtering through soil into the ground aquifer. Recharge pits enhance percolation.

---

## 6. Data Source

**Q: Where does rainfall data come from?**
> From CGWB (Central Ground Water Board) reports - historical annual rainfall averages for Indian districts.

**Q: Is the data current?**
> The data is based on historical averages. For production use, it should be updated periodically.

**Q: How many districts are covered?**
> Currently 30+ districts across 18 Indian states.

---

## 7. Limitations & Improvements

**Q: What are the limitations?**
> - Static rainfall data (not live)
> - Simplified formulas
> - No water quality testing
> - H2 database resets on restart

**Q: How would you scale this?**
> - Add live weather API (OpenWeatherMap)
> - Use MySQL for persistent storage
> - Add user authentication
> - Include water quality parameters

**Q: Can this be a mobile app?**
> Yes! The REST API can be connected to React Native or Flutter for mobile access.

---

## 8. Troubleshooting

**Q: App won't start?**
> - Ensure Java 17+ is installed
> - Check port 9090 is available
> - Run: `.\mvnw.cmd spring-boot:run`

**Q: PDF not downloading?**
> - Check browser popups are allowed
> - Check console for errors
> - Try refreshing the page

**Q: Negative 20-year savings?**
> This happens when surplus water is too low to cover maintenance costs. The system may still be environmentally beneficial but not financially optimal.

---

## 9. For Presentation

**Q: How to run the project?**
> ```bash
> cd "mini project"
> .\mvnw.cmd spring-boot:run
> ```
> Then open http://localhost:9090

**Q: How to build JAR?**
> ```bash
> .\mvnw.cmd clean package -DskipTests
> java -jar target/rtrwh-assessment-1.0.0.jar
> ```

**Q: Key points to highlight?**
> - Scientifically accurate formulas
> - Real CGWB data
> - PDF report feature (stands out)
> - Location-based cost variation
> - Environmental impact calculation

---

## 10. Data Flow

```
User Input → Validate → Calculate Runoff → Check Feasibility 
    → Recommend Structure → Calculate Cost → Generate Result
    → Save to Database → Display on Screen → PDF Download
```

---

## Quick Reference

| Term | Meaning |
|------|---------|
| RWH | Rainwater Harvesting |
| LPCD | Liters Per Capita Per Day |
| CGWB | Central Ground Water Board |
| KL | Kiloliter (1000 liters) |
| Runoff | Water that runs off roof |
| Surplus | Excess water after demand met |

---

*Created for JalDhara - Mini Project Reference*
