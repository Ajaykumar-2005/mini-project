package com.waterharvest.service;

import com.waterharvest.model.AquiferData;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AquiferDataService {

    private final Map<String, AquiferData> aquiferDatabase;

    public AquiferDataService() {
        aquiferDatabase = new HashMap<>();
        initializeDatabase();
    }

    private void initializeDatabase() {
        aquiferDatabase.put("delhi_new delhi", new AquiferData(
            "Delhi", "New Delhi", "Alluvial", 12.5, 650, "Alluvial", 0.35, "Moderate - High TDS"
        ));
        aquiferDatabase.put("delhi_south delhi", new AquiferData(
            "Delhi", "South Delhi", "Alluvial", 15.0, 650, "Alluvial", 0.35, "Moderate"
        ));
        aquiferDatabase.put("maharashtra_mumbai", new AquiferData(
            "Maharashtra", "Mumbai", "Basalt", 8.0, 2200, "Laterite", 0.20, "Good"
        ));
        aquiferDatabase.put("maharashtra_pune", new AquiferData(
            "Maharashtra", "Pune", "Basalt", 10.5, 720, "Black Cotton", 0.18, "Good"
        ));
        aquiferDatabase.put("maharashtra_nagpur", new AquiferData(
            "Maharashtra", "Nagpur", "Sandstone", 14.0, 1200, "Red Laterite", 0.30, "Moderate"
        ));
        aquiferDatabase.put("karnataka_bangalore", new AquiferData(
            "Karnataka", "Bangalore", "Peninsular Gneiss", 18.0, 880, "Red Sandy", 0.25, "Good"
        ));
        aquiferDatabase.put("karnataka_mysore", new AquiferData(
            "Karnataka", "Mysore", "Charnockite", 12.0, 780, "Red Sandy", 0.28, "Good"
        ));
        aquiferDatabase.put("tamil nadu_chennai", new AquiferData(
            "Tamil Nadu", "Chennai", "Hard Rock", 10.0, 1200, "Sandy", 0.22, "Moderate - Saline coastal"
        ));
        aquiferDatabase.put("tamil nadu_coimbatore", new AquiferData(
            "Tamil Nadu", "Coimbatore", "Hard Rock", 15.0, 600, "Red Sandy", 0.25, "Good"
        ));
        aquiferDatabase.put("gujarat_ahmedabad", new AquiferData(
            "Gujarat", "Ahmedabad", "Alluvial", 18.0, 750, "Alluvial", 0.40, "Moderate"
        ));
        aquiferDatabase.put("gujarat_surat", new AquiferData(
            "Gujarat", "Surat", "Alluvial", 12.0, 1100, "Alluvial", 0.38, "Good"
        ));
        aquiferDatabase.put("rajasthan_jaipur", new AquiferData(
            "Rajasthan", "Jaipur", "Aravalli", 25.0, 550, "Sandy", 0.20, "Poor - High Fluoride"
        ));
        aquiferDatabase.put("rajasthan_jodhpur", new AquiferData(
            "Rajasthan", "Jodhpur", "Aravalli", 30.0, 350, "Sandy Desert", 0.15, "Poor - Saline"
        ));
        aquiferDatabase.put("west bengal_kolkata", new AquiferData(
            "West Bengal", "Kolkata", "Alluvial", 6.0, 1580, "Alluvial", 0.35, "Good - Iron present"
        ));
        aquiferDatabase.put("kerala_thiruvananthapuram", new AquiferData(
            "Kerala", "Thiruvananthapuram", "Sedimentary", 5.0, 1800, "Laterite", 0.30, "Good"
        ));
        aquiferDatabase.put("kerala_kochi", new AquiferData(
            "Kerala", "Kochi", "Sedimentary", 4.5, 2800, "Laterite", 0.30, "Good"
        ));
        aquiferDatabase.put("uttar pradesh_lucknow", new AquiferData(
            "Uttar Pradesh", "Lucknow", "Alluvial", 10.0, 900, "Alluvial", 0.38, "Moderate"
        ));
        aquiferDatabase.put("uttar pradesh_varanasi", new AquiferData(
            "Uttar Pradesh", "Varanasi", "Alluvial", 8.0, 1050, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("madhya pradesh_bhopal", new AquiferData(
            "Madhya Pradesh", "Bhopal", "Sandstone", 16.0, 1150, "Red Sandy", 0.28, "Good"
        ));
        aquiferDatabase.put("andhra pradesh_hyderabad", new AquiferData(
            "Telangana", "Hyderabad", "Granite", 14.0, 800, "Red Sandy", 0.22, "Moderate"
        ));
        aquiferDatabase.put("punjab_chandigarh", new AquiferData(
            "Punjab", "Chandigarh", "Alluvial", 20.0, 1100, "Alluvial", 0.40, "Moderate - declining"
        ));
        aquiferDatabase.put("haryana_gurgaon", new AquiferData(
            "Haryana", "Gurgaon", "Alluvial", 22.0, 600, "Alluvial", 0.35, "Poor - Over exploited"
        ));
        aquiferDatabase.put("bihar_patna", new AquiferData(
            "Bihar", "Patna", "Alluvial", 7.0, 1180, "Alluvial", 0.35, "Moderate - Arsenic"
        ));
        aquiferDatabase.put("odisha_bhubaneswar", new AquiferData(
            "Odisha", "Bhubaneswar", "Alluvial", 6.5, 1400, "Alluvial", 0.32, "Good"
        ));
        aquiferDatabase.put("chhattisgarh_raipur", new AquiferData(
            "Chhattisgarh", "Raipur", "Sandstone", 12.0, 1300, "Red Sandy", 0.30, "Good"
        ));
        aquiferDatabase.put("jharkhand_ranchi", new AquiferData(
            "Jharkhand", "Ranchi", "Granite", 10.0, 1350, "Red Sandy", 0.25, "Good"
        ));
        aquiferDatabase.put("assam_guwahati", new AquiferData(
            "Assam", "Guwahati", "Alluvial", 5.0, 1750, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("telangana_hyderabad", new AquiferData(
            "Telangana", "Hyderabad", "Granite", 14.0, 800, "Red Sandy", 0.22, "Moderate"
        ));
    }

    public AquiferData getAquiferData(String state, String district) {
        String key = state.toLowerCase() + "_" + district.toLowerCase();
        AquiferData data = aquiferDatabase.get(key);
        
        if (data == null) {
            String stateKey = state.toLowerCase();
            for (Map.Entry<String, AquiferData> entry : aquiferDatabase.entrySet()) {
                if (entry.getKey().startsWith(stateKey + "_")) {
                    return entry.getValue();
                }
            }
            return new AquiferData(state, district, "Alluvial", 12.0, 800, "Alluvial", 0.30, "Moderate");
        }
        return data;
    }

    public List<String> getAvailableStates() {
        Set<String> states = new TreeSet<>();
        for (AquiferData data : aquiferDatabase.values()) {
            states.add(data.getState());
        }
        return new ArrayList<>(states);
    }

    public List<String> getDistrictsByState(String state) {
        List<String> districts = new ArrayList<>();
        String stateKey = state.toLowerCase();
        for (Map.Entry<String, AquiferData> entry : aquiferDatabase.entrySet()) {
            if (entry.getKey().startsWith(stateKey + "_")) {
                districts.add(entry.getValue().getDistrict());
            }
        }
        return districts;
    }
}
