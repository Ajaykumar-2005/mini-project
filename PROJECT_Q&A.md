# JalDhara - Project Q&A Reference
## Rainwater Harvesting Assessment Tool

---

## 1. Project Overview

**Q: What is this project?**
> JalDhara is a comprehensive web-based tool that assesses the feasibility of rooftop rainwater harvesting (RWH) for buildings in India. It provides structure recommendations, cost estimation, and environmental impact analysis with multi-factor adjustments.

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
> 2. Aquifer Database - 70+ Indian districts with groundwater data
> 3. Structure Recommendations - Recharge pit, trench, shaft, or storage tank
> 4. Cost Estimation - Location + Year + Quality based pricing
> 5. Environmental Impact - CO₂ reduction calculation
> 6. PDF Report - Downloadable assessment report
> 7. Custom Cost Entry - User can enter their own quotes
> 8. Detailed Cost Breakdown - Excavation, PCC, Stone/Gravel, Brickwork, Labour

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

### Year-Based Cost Adjustment
```
Cost for Year = Base Cost × (0.93)^(2026 - Year)
- 2024: 0.86x (14% less)
- 2025: 0.93x (7% less)
- 2026: 1.00x (current)
```

### Runoff Coefficients by Roof Type
| Roof Type | Coefficient | Source |
|----------|-------------|--------|
| Metal Sheet | 0.90 | Scientific literature |
| Concrete/RCC | 0.85 | Scientific literature |
| Asbestos | 0.80 | Scientific literature |
| Clay Tile | 0.75 | Scientific literature |
| Thatched | 0.30 | Scientific literature |

---

## 4. Cost Multipliers (Data Sources)

### Location-Based Cost Multipliers
| Area Type | Multiplier | Source |
|-----------|------------|--------|
| Mumbai | 1.50x | Construction Estimator India 2026 |
| Delhi/NCR | 1.40x | Construction Estimator India 2026 |
| Bangalore | 1.35x | Construction Estimator India 2026 |
| Other Metro | 1.30x | Estimated |
| Tier-2 City | 1.15x | JK Cement 2026 |
| Town | 1.00x | Baseline |
| Village | 0.85x | Estimated |

**Source:** 
- https://constructionestimatorindia.com/construction-cost-per-sq-ft-in-india/
- https://www.jkcement.com/blog/construction-budgeting/construction-cost-per-sq-ft-india-2026/

### Quality-Based Cost Multipliers
| Quality | Multiplier | Description |
|---------|------------|-------------|
| Basic | 0.8x | Local materials |
| Standard | 1.0x | Quality materials |
| Premium | 1.3x | Premium brands |

**Source:** JK Cement Construction Guide 2026

### Cost Breakdown (per 5000L Storage Tank)
| Brand | Price Range (₹) | Source |
|-------|----------------|--------|
| Sintex | ₹8,000 - ₹12,000 | NoBroker 2025 |
| Supreme | ₹7,500 - ₹11,500 | NoBroker 2025 |
| Plasto | ₹7,000 - ₹10,500 | NoBroker 2025 |
| Aqua | ₹8,500 - ₹13,000 | NoBroker 2025 |

**Source:** https://www.nobroker.in/blog/5000-litre-water-tank-price-in-india/

### Construction Cost Variation
- Contractor quotes vary by: **±10-20%**
- Annual inflation: **5-8%** (JLL 2026)

**Source:** https://www.nbmcw.com/news/construction-costs-across-all-asset-classes-in-india-projected-to-increase-3-5-in-2026-jll.html

---

## 5. Aquifer Database

**Q: How many districts are covered?**
> 70+ districts across 25+ Indian states

**Q: What data is stored per district?**
> - State, District
> - Aquifer Type (Alluvial, Basalt, Granite, etc.)
> - Groundwater Level (meters)
> - Annual Rainfall (mm)
> - Soil Type
> - Runoff Coefficient
> - Water Quality Status

**Q: What is the data source?**
> CGWB (Central Ground Water Board) reports and IMD (India Meteorological Department)

---

## 6. Technical Questions

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

## 7. Scientific Questions

**Q: What is 135 LPCD?**
> Liters Per Capita Per Day - The Indian standard for estimating water demand. 135 LPCD is the recommended daily water requirement per person.

**Q: Why CO₂ reduction = Runoff × 0.4?**
> Based on energy saved by not pumping groundwater. Approximately 0.35-0.4 kg of CO₂ emissions are avoided for every KL of rainwater harvested.
> 
> Calculation: Pumping groundwater uses ~0.5 kWh per 1000 liters. At ~0.7 kg CO₂ per kWh (grid average) = ~0.35 kg CO₂ per KL. Rounded to 0.4 for conservative estimate.

**Q: What is aquifer type?**
> The geological formation that stores groundwater. Types include:
> - Alluvial: Sandy soil, high water storage
> - Basalt: Hard rock, moderate storage
> - Granite: Low storage, fractured rock

**Q: What is runoff coefficient?**
> The percentage of rainfall that becomes runoff. Smooth surfaces (metal) have high coefficients (0.90), while absorbent surfaces (thatch) have low coefficients (0.30).

---

## 8. Data Sources (Complete List)

| Data Type | Source | Year |
|-----------|--------|------|
| Rainfall/Aquifer Data | CGWB (Central Ground Water Board) | Various |
| Construction Costs | JK Cement | 2026 |
| Construction Costs | Construction Estimator India | 2026 |
| Tank Prices | NoBroker | 2025 |
| Cost Variation | JLL India | 2026 |
| RWH Costs | HouseYog | 2025 |

---

## 9. Features Explained

### Custom Cost Entry
Users can enter their own quotes from local contractors:
- Excavation cost
- PCC cost
- Stone/Gravel cost
- Brickwork cost
- Labour cost
- Plumbing cost
- Filtration cost

### Year-Based Cost Adjustment
Select estimation year: 2024, 2025, or 2026
Accounts for annual inflation (~7%)

### Quality Options
- Basic: 0.8x (local materials)
- Standard: 1.0x (quality materials)
- Premium: 1.3x (premium brands)

### Cost Range Display
- Minimum: 80% of calculated cost
- Maximum: 120% of calculated cost
- Accounts for contractor quote variations

---

## 10. Limitations & Improvements

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

## 11. For Presentation

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
> - Scientifically accurate formulas with proper references
> - Real CGWB data (70+ districts)
> - PDF report feature
> - Multi-factor cost estimation (location + year + quality)
> - Custom cost entry option
> - Data sources from established Indian sources (JK Cement, NoBroker, CGWB)

---

## 12. Data Flow

```
User Input → Validate → Calculate Runoff → Check Feasibility 
    → Recommend Structure → Calculate Cost (with multipliers)
    → Generate Result → Save to Database → Display on Screen 
    → PDF Download / Custom Cost Entry
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

*Last Updated: April 2026*
*Created for JalDhara - Mini Project Reference*
*Data Sources: JK Cement, NoBroker, CGWB, Construction Estimator India, JLL*
