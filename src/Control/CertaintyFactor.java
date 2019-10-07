/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONObject;

/**
 *
 * @author Azhary Arliansyah
 */
public class CertaintyFactor {
    
    private Map<String, Map<String, Double>> diseases;
    private List<String> symptoms;
    private Map<String, Double> certaintyWeight;
    
    
    public CertaintyFactor() {
        this.symptoms = new ArrayList<>();
        this.diseases = new HashMap<>();
        this.certaintyWeight = new HashMap<>();
        
        this.setSymptoms();
        this.setCertaintyWeight();
        this.expertCertaintyFactors();
    }
    
    public Map<String, Double> calculateDiseaseCertaintyFactor(
            Map<String, Double> userCertaintyFactor) {
        
        Map<String, Double> diseaseCertaintyFactor = new HashMap<>();
        
        for (Map.Entry<String, Map<String, Double>> e : 
                this.diseases.entrySet()) {
            
            double cf = this.calculateCertaintyFactor(userCertaintyFactor, 
                    e.getValue());
            diseaseCertaintyFactor.put(e.getKey(), cf);
            
        }
        
        return diseaseCertaintyFactor;
    }
    
    public double calculateCertaintyFactor(
            Map<String, Double> userCertaintyFactor, 
            Map<String, Double> expertCertaintyFactor) {
        
        Map<String, Double> multipliedCertaintyFactor = 
                this.multiplyCertaintyFactor(userCertaintyFactor, 
                        expertCertaintyFactor);
        
        List<String> keys = 
                new ArrayList<>(expertCertaintyFactor.keySet());
        double certaintyFactor = multipliedCertaintyFactor.get(keys.get(0));
        for (int i = 1; i < keys.size(); i++) {
            certaintyFactor += multipliedCertaintyFactor.get(keys.get(i)) * 
                    (1 - certaintyFactor);
        }
        
        return certaintyFactor;
    }
    
    private Map<String, Double> multiplyCertaintyFactor(
            Map<String, Double> userCertaintyFactor, 
            Map<String, Double> expertCertaintyFactor) {
        
        Map<String, Double> multipliedCertaintyFactor = new HashMap<>();
        
        for (String key : expertCertaintyFactor.keySet()) {
               
            multipliedCertaintyFactor.put(key, expertCertaintyFactor.get(key) * 
                    userCertaintyFactor.getOrDefault(key, 0.0));
            
        }
        
        return multipliedCertaintyFactor;
        
    }
    
    private void setSymptoms() {
        this.symptoms = new ArrayList<>();
        List<Object> symptoms = JsonHandler.readJsonFile("config/symptoms.json");
        for (Object o : symptoms) {
            this.symptoms.add(o.toString());
        }
    }
    
    private void setCertaintyWeight() {
        this.certaintyWeight = new HashMap<>();
        JSONObject weights = JsonHandler
                .readJsonObjectFile("config/weights.json");
        Set<String> keys = weights.keySet();
        for (String key : keys) {
            this.certaintyWeight
                    .put(key, Double.parseDouble(weights.get(key).toString()));
        }
    }
    
    public List<String> getSymptoms() {
        return this.symptoms;
    }
    
    public Map<String, Double> getCertaintyWeight() {
        return this.certaintyWeight;
    }
    
    public Map<String, Map<String, Double>> getDiseases() {
        return this.diseases;
    }
    
    public Map<String, Map<String, Double>> expertCertaintyFactors() {
        
        this.diseases = new HashMap<>();
        List<Object> diseases = JsonHandler.readJsonFile("config/disease.json");
        for (Object o : diseases) {
            JSONObject disease = (JSONObject)o;
            String key = disease.get("disease").toString();
            
            JSONObject symptoms = (JSONObject)disease.get("symptoms");
            Set<String> keys = symptoms.keySet();
            
            Map<String, Double> expertCertaintyFactor = new HashMap<>();
            for (String k : keys) {
                expertCertaintyFactor.put(k, 
                        Double.parseDouble(symptoms.get(k).toString()));
            }
            
            this.diseases.put(key, expertCertaintyFactor);
        }
        
        
        return this.diseases;
    }
    
}
