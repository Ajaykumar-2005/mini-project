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
        aquiferDatabase.put("maharashtra_nashik", new AquiferData(
            "Maharashtra", "Nashik", "Basalt", 12.0, 650, "Black Cotton", 0.20, "Good"
        ));
        aquiferDatabase.put("maharashtra_thane", new AquiferData(
            "Maharashtra", "Thane", "Basalt", 9.0, 1800, "Laterite", 0.22, "Good"
        ));
        aquiferDatabase.put("maharashtra_navimumbai", new AquiferData(
            "Maharashtra", "Navi Mumbai", "Basalt", 8.0, 2100, "Laterite", 0.22, "Good"
        ));
        aquiferDatabase.put("maharashtra_kolhapur", new AquiferData(
            "Maharashtra", "Kolhapur", "Basalt", 11.0, 950, "Laterite", 0.25, "Good"
        ));
        aquiferDatabase.put("maharashtra_solapur", new AquiferData(
            "Maharashtra", "Solapur", "Basalt", 18.0, 550, "Black Cotton", 0.18, "Moderate"
        ));
        aquiferDatabase.put("maharashtra_aurangabad", new AquiferData(
            "Maharashtra", "Aurangabad", "Basalt", 16.0, 600, "Black Cotton", 0.18, "Moderate"
        ));
        aquiferDatabase.put("gujarat_vadodara", new AquiferData(
            "Gujarat", "Vadodara", "Alluvial", 14.0, 850, "Alluvial", 0.38, "Moderate"
        ));
        aquiferDatabase.put("gujarat_rajkot", new AquiferData(
            "Gujarat", "Rajkot", "Alluvial", 20.0, 450, "Sandy", 0.30, "Poor"
        ));
        aquiferDatabase.put("gujarat_gandhinagar", new AquiferData(
            "Gujarat", "Gandhinagar", "Alluvial", 18.0, 600, "Alluvial", 0.35, "Moderate"
        ));
        aquiferDatabase.put("karnataka_mangalore", new AquiferData(
            "Karnataka", "Mangalore", "Laterite", 6.0, 3200, "Laterite", 0.30, "Good"
        ));
        aquiferDatabase.put("karnataka_hubli", new AquiferData(
            "Karnataka", "Hubli", "Basalt", 14.0, 750, "Black Cotton", 0.25, "Good"
        ));
        aquiferDatabase.put("karnataka_belgaum", new AquiferData(
            "Karnataka", "Belgaum", "Basalt", 12.0, 900, "Laterite", 0.28, "Good"
        ));
        aquiferDatabase.put("tamil nadu_madurai", new AquiferData(
            "Tamil Nadu", "Madurai", "Hard Rock", 13.0, 850, "Red Sandy", 0.22, "Moderate"
        ));
        aquiferDatabase.put("tamil nadu_tiruchirappalli", new AquiferData(
            "Tamil Nadu", "Tiruchirappalli", "Hard Rock", 14.0, 750, "Red Sandy", 0.20, "Moderate"
        ));
        aquiferDatabase.put("tamil nadu_salem", new AquiferData(
            "Tamil Nadu", "Salem", "Hard Rock", 16.0, 700, "Red Sandy", 0.22, "Good"
        ));
        aquiferDatabase.put("tamil nadu_tirunelveli", new AquiferData(
            "Tamil Nadu", "Tirunelveli", "Hard Rock", 18.0, 650, "Red Sandy", 0.20, "Good"
        ));
        aquiferDatabase.put("uttar pradesh_kanpur", new AquiferData(
            "Uttar Pradesh", "Kanpur", "Alluvial", 12.0, 850, "Alluvial", 0.35, "Moderate"
        ));
        aquiferDatabase.put("uttar pradesh_agRA", new AquiferData(
            "Uttar Pradesh", "Agra", "Alluvial", 18.0, 700, "Alluvial", 0.32, "Moderate - declining"
        ));
        aquiferDatabase.put("uttar pradesh_allahabad", new AquiferData(
            "Uttar Pradesh", "Allahabad", "Alluvial", 9.0, 950, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("uttar pradesh_meerut", new AquiferData(
            "Uttar Pradesh", "Meerut", "Alluvial", 15.0, 750, "Alluvial", 0.35, "Moderate"
        ));
        aquiferDatabase.put("west bengal_howrah", new AquiferData(
            "West Bengal", "Howrah", "Alluvial", 7.0, 1550, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("west bengal_durgapur", new AquiferData(
            "West Bengal", "Durgapur", "Alluvial", 8.0, 1400, "Alluvial", 0.32, "Good"
        ));
        aquiferDatabase.put("west bengal_siliguri", new AquiferData(
            "West Bengal", "Siliguri", "Alluvial", 6.0, 1800, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("odisha_cuttack", new AquiferData(
            "Odisha", "Cuttack", "Alluvial", 8.0, 1300, "Alluvial", 0.32, "Good"
        ));
        aquiferDatabase.put("odisha_berhampur", new AquiferData(
            "Odisha", "Berhampur", "Alluvial", 7.0, 1250, "Alluvial", 0.30, "Good"
        ));
        aquiferDatabase.put("jharkhand_jamshedpur", new AquiferData(
            "Jharkhand", "Jamshedpur", "Granite", 9.0, 1200, "Red Sandy", 0.25, "Good"
        ));
        aquiferDatabase.put("bihar_gaya", new AquiferData(
            "Bihar", "Gaya", "Alluvial", 9.0, 1050, "Alluvial", 0.32, "Moderate"
        ));
        aquiferDatabase.put("madhya pradesh_indore", new AquiferData(
            "Madhya Pradesh", "Indore", "Sandstone", 14.0, 950, "Red Sandy", 0.28, "Good"
        ));
        aquiferDatabase.put("madhya pradesh_jabalpur", new AquiferData(
            "Madhya Pradesh", "Jabalpur", "Sandstone", 12.0, 1100, "Red Sandy", 0.28, "Good"
        ));
        aquiferDatabase.put("punjab_ludhiana", new AquiferData(
            "Punjab", "Ludhiana", "Alluvial", 18.0, 950, "Alluvial", 0.38, "Moderate"
        ));
        aquiferDatabase.put("punjab_amritsar", new AquiferData(
            "Punjab", "Amritsar", "Alluvial", 22.0, 850, "Alluvial", 0.35, "Moderate"
        ));
        aquiferDatabase.put("haryana_faridabad", new AquiferData(
            "Haryana", "Faridabad", "Alluvial", 20.0, 650, "Alluvial", 0.35, "Poor"
        ));
        aquiferDatabase.put("haryana_panipat", new AquiferData(
            "Haryana", "Panipat", "Alluvial", 24.0, 550, "Alluvial", 0.32, "Poor"
        ));
        aquiferDatabase.put("assam_dibrugarh", new AquiferData(
            "Assam", "Dibrugarh", "Alluvial", 4.0, 2100, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("goa_panji", new AquiferData(
            "Goa", "Panaji", "Laterite", 5.0, 2800, "Laterite", 0.30, "Good"
        ));
        aquiferDatabase.put("uttarakhand_dehradun", new AquiferData(
            "Uttarakhand", "Dehradun", "Alluvial", 10.0, 1550, "Alluvial", 0.35, "Good"
        ));
        aquiferDatabase.put("himachal pradesh_shimla", new AquiferData(
            "Himachal Pradesh", "Shimla", "Granite", 8.0, 1400, "Sandy", 0.25, "Good"
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
