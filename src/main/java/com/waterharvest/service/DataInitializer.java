package com.waterharvest.service;

import com.waterharvest.entity.AquiferEntity;
import com.waterharvest.repository.AquiferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AquiferRepository aquiferRepository;

    @Override
    public void run(String... args) {
        if (aquiferRepository.count() == 0) {
            initializeDatabase();
            System.out.println("Aquifer database initialized with sample data.");
        } else {
            System.out.println("Aquifer database already contains " + aquiferRepository.count() + " records.");
        }
    }

    private void initializeDatabase() {
        // Delhi
        saveData("Delhi", "New Delhi", "Alluvial", 12.5, 650, "Alluvial", 0.35, "Moderate - High TDS");
        saveData("Delhi", "South Delhi", "Alluvial", 15.0, 650, "Alluvial", 0.35, "Moderate");
        saveData("Delhi", "Central Delhi", "Alluvial", 13.0, 650, "Alluvial", 0.35, "Moderate");
        saveData("Delhi", "East Delhi", "Alluvial", 11.5, 650, "Alluvial", 0.35, "Moderate");
        saveData("Delhi", "West Delhi", "Alluvial", 14.0, 650, "Alluvial", 0.35, "Moderate");
        saveData("Delhi", "North Delhi", "Alluvial", 13.5, 650, "Alluvial", 0.35, "Moderate");

        // Maharashtra
        saveData("Maharashtra", "Mumbai", "Basalt", 8.0, 2200, "Laterite", 0.20, "Good");
        saveData("Maharashtra", "Pune", "Basalt", 10.5, 720, "Black Cotton", 0.18, "Good");
        saveData("Maharashtra", "Nagpur", "Sandstone", 14.0, 1200, "Red Laterite", 0.30, "Moderate");
        saveData("Maharashtra", "Thane", "Basalt", 9.0, 2000, "Laterite", 0.22, "Good");
        saveData("Maharashtra", "Nashik", "Basalt", 12.0, 800, "Black Cotton", 0.20, "Good");
        saveData("Maharashtra", "Aurangabad", "Basalt", 15.0, 600, "Sandy", 0.18, "Moderate");

        // Karnataka
        saveData("Karnataka", "Bangalore", "Peninsular Gneiss", 18.0, 880, "Red Sandy", 0.25, "Good");
        saveData("Karnataka", "Mysore", "Charnockite", 12.0, 780, "Red Sandy", 0.28, "Good");
        saveData("Karnataka", "Mangalore", "Laterite", 8.0, 3200, "Laterite", 0.30, "Good");
        saveData("Karnataka", "Hubli", "Basalt", 14.0, 750, "Black Cotton", 0.22, "Moderate");
        saveData("Karnataka", "Belgaum", "Basalt", 13.0, 900, "Black Cotton", 0.25, "Good");
        saveData("Karnataka", "Gulbarga", "Granite", 16.0, 850, "Red Sandy", 0.20, "Moderate");

        // Tamil Nadu
        saveData("Tamil Nadu", "Chennai", "Hard Rock", 10.0, 1200, "Sandy", 0.22, "Moderate - Saline coastal");
        saveData("Tamil Nadu", "Coimbatore", "Hard Rock", 15.0, 600, "Red Sandy", 0.25, "Good");
        saveData("Tamil Nadu", "Madurai", "Hard Rock", 12.0, 850, "Red Sandy", 0.24, "Good");
        saveData("Tamil Nadu", "Trichy", "Hard Rock", 14.0, 900, "Alluvial", 0.26, "Moderate");
        saveData("Tamil Nadu", "Salem", "Granite", 16.0, 950, "Red Sandy", 0.22, "Good");
        saveData("Tamil Nadu", "Tirunelveli", "Hard Rock", 8.0, 750, "Sandy", 0.20, "Good");

        // Gujarat
        saveData("Gujarat", "Ahmedabad", "Alluvial", 18.0, 750, "Alluvial", 0.40, "Moderate");
        saveData("Gujarat", "Surat", "Alluvial", 12.0, 1100, "Alluvial", 0.38, "Good");
        saveData("Gujarat", "Vadodara", "Alluvial", 14.0, 900, "Alluvial", 0.35, "Moderate");
        saveData("Gujarat", "Rajkot", "Alluvial", 16.0, 550, "Sandy", 0.32, "Moderate");
        saveData("Gujarat", "Bhavnagar", "Alluvial", 10.0, 600, "Sandy", 0.30, "Moderate");
        saveData("Gujarat", "Jamnagar", "Alluvial", 8.0, 450, "Sandy", 0.28, "Moderate");

        // Rajasthan
        saveData("Rajasthan", "Jaipur", "Aravalli", 25.0, 550, "Sandy", 0.20, "Poor - High Fluoride");
        saveData("Rajasthan", "Jodhpur", "Aravalli", 30.0, 350, "Sandy Desert", 0.15, "Poor - Saline");
        saveData("Rajasthan", "Udaipur", "Aravalli", 20.0, 600, "Sandy", 0.18, "Moderate");
        saveData("Rajasthan", "Kota", "Vindhyan", 22.0, 700, "Black Cotton", 0.20, "Moderate");
        saveData("Rajasthan", "Ajmer", "Aravalli", 24.0, 500, "Sandy", 0.18, "Poor");
        saveData("Rajasthan", "Bikaner", "Desert", 35.0, 300, "Sandy Desert", 0.10, "Poor - Saline");

        // West Bengal
        saveData("West Bengal", "Kolkata", "Alluvial", 6.0, 1580, "Alluvial", 0.35, "Good - Iron present");
        saveData("West Bengal", "Howrah", "Alluvial", 7.0, 1500, "Alluvial", 0.34, "Good");
        saveData("West Bengal", "Asansol", "Alluvial", 8.0, 1200, "Alluvial", 0.32, "Moderate");
        saveData("West Bengal", "Siliguri", "Alluvial", 5.0, 3000, "Alluvial", 0.38, "Good");
        saveData("West Bengal", "Durgapur", "Alluvial", 9.0, 1400, "Alluvial", 0.33, "Good");
        saveData("West Bengal", "Berhampore", "Alluvial", 7.5, 1300, "Alluvial", 0.35, "Good");

        // Kerala
        saveData("Kerala", "Thiruvananthapuram", "Sedimentary", 5.0, 1800, "Laterite", 0.30, "Good");
        saveData("Kerala", "Kochi", "Sedimentary", 4.5, 2800, "Laterite", 0.30, "Good");
        saveData("Kerala", "Kozhikode", "Sedimentary", 5.5, 3000, "Laterite", 0.32, "Good");
        saveData("Kerala", "Thrissur", "Sedimentary", 6.0, 2500, "Laterite", 0.28, "Good");
        saveData("Kerala", "Kollam", "Sedimentary", 5.0, 2200, "Laterite", 0.30, "Good");
        saveData("Kerala", "Palakkad", "Sedimentary", 12.0, 2000, "Black Cotton", 0.25, "Good");

        // Uttar Pradesh
        saveData("Uttar Pradesh", "Lucknow", "Alluvial", 10.0, 900, "Alluvial", 0.38, "Moderate");
        saveData("Uttar Pradesh", "Varanasi", "Alluvial", 8.0, 1050, "Alluvial", 0.35, "Good");
        saveData("Uttar Pradesh", "Kanpur", "Alluvial", 12.0, 800, "Alluvial", 0.36, "Moderate");
        saveData("Uttar Pradesh", "Agra", "Alluvial", 15.0, 650, "Alluvial", 0.32, "Moderate");
        saveData("Uttar Pradesh", "Allahabad", "Alluvial", 9.0, 1000, "Alluvial", 0.38, "Good");
        saveData("Uttar Pradesh", "Meerut", "Alluvial", 14.0, 850, "Alluvial", 0.34, "Moderate");

        // Madhya Pradesh
        saveData("Madhya Pradesh", "Bhopal", "Sandstone", 16.0, 1150, "Red Sandy", 0.28, "Good");
        saveData("Madhya Pradesh", "Indore", "Sandstone", 14.0, 950, "Black Cotton", 0.25, "Good");
        saveData("Madhya Pradesh", "Gwalior", "Sandstone", 18.0, 850, "Black Cotton", 0.22, "Moderate");
        saveData("Madhya Pradesh", "Jabalpur", "Sandstone", 12.0, 1200, "Red Sandy", 0.26, "Good");
        saveData("Madhya Pradesh", "Ujjain", "Sandstone", 15.0, 900, "Black Cotton", 0.24, "Moderate");
        saveData("Madhya Pradesh", "Sagar", "Sandstone", 14.0, 1100, "Red Sandy", 0.27, "Good");

        // Telangana
        saveData("Telangana", "Hyderabad", "Granite", 14.0, 800, "Red Sandy", 0.22, "Moderate");
        saveData("Telangana", "Warangal", "Granite", 16.0, 950, "Red Sandy", 0.24, "Good");
        saveData("Telangana", "Nizamabad", "Granite", 12.0, 850, "Black Cotton", 0.20, "Moderate");
        saveData("Telangana", "Karimnagar", "Granite", 15.0, 900, "Red Sandy", 0.22, "Good");
        saveData("Telangana", "Ramagundam", "Granite", 13.0, 1000, "Black Cotton", 0.25, "Moderate");
        saveData("Telangana", "Secunderabad", "Granite", 14.0, 800, "Red Sandy", 0.22, "Moderate");

        // Punjab
        saveData("Punjab", "Chandigarh", "Alluvial", 20.0, 1100, "Alluvial", 0.40, "Moderate - declining");
        saveData("Punjab", "Ludhiana", "Alluvial", 18.0, 750, "Alluvial", 0.38, "Moderate");
        saveData("Punjab", "Amritsar", "Alluvial", 22.0, 650, "Alluvial", 0.35, "Moderate");
        saveData("Punjab", "Jalandhar", "Alluvial", 19.0, 850, "Alluvial", 0.36, "Moderate");
        saveData("Punjab", "Patiala", "Alluvial", 17.0, 900, "Alluvial", 0.37, "Moderate");
        saveData("Punjab", "Bathinda", "Alluvial", 25.0, 500, "Sandy", 0.30, "Moderate");

        // Haryana
        saveData("Haryana", "Gurgaon", "Alluvial", 22.0, 600, "Alluvial", 0.35, "Poor - Over exploited");
        saveData("Haryana", "Faridabad", "Alluvial", 20.0, 650, "Alluvial", 0.34, "Moderate");
        saveData("Haryana", "Panipat", "Alluvial", 18.0, 550, "Alluvial", 0.32, "Moderate");
        saveData("Haryana", "Ambala", "Alluvial", 16.0, 1100, "Alluvial", 0.38, "Good");
        saveData("Haryana", "Karnal", "Alluvial", 17.0, 700, "Alluvial", 0.35, "Moderate");
        saveData("Haryana", "Rohtak", "Alluvial", 24.0, 550, "Alluvial", 0.30, "Poor");

        // Bihar
        saveData("Bihar", "Patna", "Alluvial", 7.0, 1180, "Alluvial", 0.35, "Moderate - Arsenic");
        saveData("Bihar", "Gaya", "Alluvial", 9.0, 1050, "Alluvial", 0.33, "Moderate");
        saveData("Bihar", "Muzaffarpur", "Alluvial", 6.0, 1300, "Alluvial", 0.36, "Good");
        saveData("Bihar", "Bhagalpur", "Alluvial", 8.0, 1200, "Alluvial", 0.34, "Good");
        saveData("Bihar", "Darbhanga", "Alluvial", 5.0, 1400, "Alluvial", 0.38, "Good");
        saveData("Bihar", "Purnia", "Alluvial", 7.0, 1500, "Alluvial", 0.35, "Good");

        // Odisha
        saveData("Odisha", "Bhubaneswar", "Alluvial", 6.5, 1400, "Alluvial", 0.32, "Good");
        saveData("Odisha", "Cuttack", "Alluvial", 7.0, 1500, "Alluvial", 0.33, "Good");
        saveData("Odisha", "Rourkela", "Granite", 8.0, 1600, "Red Sandy", 0.28, "Good");
        saveData("Odisha", "Berhampur", "Alluvial", 6.0, 1250, "Alluvial", 0.30, "Good");
        saveData("Odisha", "Sambalpur", "Alluvial", 7.5, 1400, "Alluvial", 0.32, "Good");
        saveData("Odisha", "Balasore", "Alluvial", 5.5, 1600, "Alluvial", 0.34, "Good");

        // Chhattisgarh
        saveData("Chhattisgarh", "Raipur", "Sandstone", 12.0, 1300, "Red Sandy", 0.30, "Good");
        saveData("Chhattisgarh", "Bhilai", "Sandstone", 11.0, 1200, "Red Sandy", 0.28, "Good");
        saveData("Chhattisgarh", "Durg", "Sandstone", 10.0, 1250, "Red Sandy", 0.29, "Good");
        saveData("Chhattisgarh", "Bilaspur", "Sandstone", 13.0, 1400, "Black Cotton", 0.27, "Good");
        saveData("Chhattisgarh", "Korba", "Sandstone", 14.0, 1200, "Red Sandy", 0.28, "Good");
        saveData("Chhattisgarh", "Rajnandgaon", "Sandstone", 11.0, 1350, "Red Sandy", 0.30, "Good");

        // Jharkhand
        saveData("Jharkhand", "Ranchi", "Granite", 10.0, 1350, "Red Sandy", 0.25, "Good");
        saveData("Jharkhand", "Jamshedpur", "Granite", 8.0, 1400, "Red Sandy", 0.28, "Good");
        saveData("Jharkhand", "Dhanbad", "Granite", 12.0, 1200, "Red Sandy", 0.26, "Good");
        saveData("Jharkhand", "Bokaro", "Granite", 11.0, 1300, "Red Sandy", 0.27, "Good");
        saveData("Jharkhand", "Hazaribagh", "Granite", 9.0, 1250, "Red Sandy", 0.25, "Good");
        saveData("Jharkhand", "Deoghar", "Granite", 8.0, 1400, "Red Sandy", 0.28, "Good");

        // Assam
        saveData("Assam", "Guwahati", "Alluvial", 5.0, 1750, "Alluvial", 0.35, "Good");
        saveData("Assam", "Silchar", "Alluvial", 4.0, 2500, "Alluvial", 0.38, "Good");
        saveData("Assam", "Dibrugarh", "Alluvial", 4.5, 2200, "Alluvial", 0.36, "Good");
        saveData("Assam", "Jorhat", "Alluvial", 5.0, 1900, "Alluvial", 0.34, "Good");
        saveData("Assam", "Tezpur", "Alluvial", 5.5, 1600, "Alluvial", 0.33, "Good");
        saveData("Assam", "Bongaigaon", "Alluvial", 6.0, 2000, "Alluvial", 0.35, "Good");

        // Andhra Pradesh
        saveData("Andhra Pradesh", "Visakhapatnam", "Granite", 7.0, 1100, "Red Sandy", 0.25, "Good");
        saveData("Andhra Pradesh", "Vijayawada", "Alluvial", 10.0, 1050, "Alluvial", 0.30, "Good");
        saveData("Andhra Pradesh", "Guntur", "Alluvial", 12.0, 950, "Black Cotton", 0.28, "Moderate");
        saveData("Andhra Pradesh", "Tirupati", "Granite", 14.0, 1100, "Red Sandy", 0.24, "Good");
        saveData("Andhra Pradesh", "Nellore", "Granite", 11.0, 1200, "Sandy", 0.26, "Good");
        saveData("Andhra Pradesh", "Kurnool", "Granite", 15.0, 850, "Red Sandy", 0.22, "Moderate");
    }

    private void saveData(String state, String district, String aquiferType,
                          double groundwaterLevel, double annualRainfall,
                          String soilType, double runoffCoefficient, String waterQuality) {
        if (!aquiferRepository.existsByStateAndDistrict(state, district)) {
            AquiferEntity entity = new AquiferEntity(state, district, aquiferType,
                    groundwaterLevel, annualRainfall, soilType, runoffCoefficient, waterQuality);
            aquiferRepository.save(entity);
        }
    }
}
