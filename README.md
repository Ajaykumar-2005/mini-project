# JalDhara - Rooftop Rainwater Harvesting Assessment Tool

A Spring Boot web application for assessing rooftop rainwater harvesting and artificial recharge potential.

## Features
- Feasibility check for rainwater harvesting
- Aquifer and groundwater level data for 19 Indian states
- Recommended structure design (Recharge Pit, Trench, Shaft, Storage Tank)
- Cost estimation with payback period
- Environmental impact calculation

## Technology Stack
- **Backend**: Java 17, Spring Boot 3.2
- **Database**: H2 (in-memory) / MySQL (optional)
- **Frontend**: HTML, CSS, JavaScript
- **Build Tool**: Maven

## How to Run

### Prerequisites
- Java 17 or higher
- Maven (or use included Maven wrapper)

### Run Locally
```bash
# Using Maven Wrapper (Windows)
.\mvnw.cmd spring-boot:run

# Using Maven (if installed)
mvn spring-boot:run
```

### Access Application
Open browser: http://localhost:9090

## Project Structure
```
mini project/
├── pom.xml
├── mvnw.cmd
├── .gitignore
└── src/main/
    ├── java/com/waterharvest/
    │   ├── RainwaterHarvestingApplication.java
    │   ├── controller/
    │   ├── service/
    │   ├── model/
    │   ├── entity/
    │   └── repository/
    └── resources/
        ├── application.properties
        ├── static/
        │   ├── css/style.css
        │   └── js/app.js
        └── templates/index.html
```

## Configuration
Edit `src/main/resources/application.properties` to change:
- Server port (default: 9090)
- Database settings

## License
MIT