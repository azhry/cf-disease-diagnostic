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
        this.symptoms.add("Mual dan muntah");
        this.symptoms.add("Demam");
        this.symptoms.add("Nyeri dan kram pada bagian perut");
        this.symptoms.add("Feses lembek / cair");
        this.symptoms.add("Hilang nafsu makan");
        this.symptoms.add("Pendarahan saat BAB");
        this.symptoms.add("Tidak bisa buang gas");
        this.symptoms.add("Dehidrasi");
        this.symptoms.add("Mudah merasa lelah");
        this.symptoms.add("Rasa nyeri di bagian ulu hati hingga dada");
        this.symptoms.add("Pendarahan di sekitar anus");
        this.symptoms.add("Sulit dalam bernafas");
        this.symptoms.add("Merasa nyeri ketika buang air kecil");
        this.symptoms.add("Perut kembung");
        this.symptoms.add("Berat badan turun");
    }
    
    private void setCertaintyWeight() {
        this.certaintyWeight = new HashMap<>();
        this.certaintyWeight.put("Tidak Ada", 0.0);
        this.certaintyWeight.put("Ragu-ragu", 0.2);
        this.certaintyWeight.put("Mungkin", 0.4);
        this.certaintyWeight.put("Sangat mungkin", 0.6);
        this.certaintyWeight.put("Hampir pasti", 0.8);
        this.certaintyWeight.put("Pasti", 1.0);
    }
    
    public List<String> getSymptoms() {
        return this.symptoms;
    }
    
    public Map<String, Double> getCertaintyWeight() {
        return this.certaintyWeight;
    }
    
    public Map<String, Map<String, Double>> expertCertaintyFactors() {
        
        this.diseases = new HashMap<>();
        
        Map<String, Double> expertCertaintyFactor1 = new HashMap<>();
        expertCertaintyFactor1.put("Mual dan muntah", 0.7);
        expertCertaintyFactor1.put("Demam", 0.4);
        expertCertaintyFactor1.put("Nyeri dan kram pada bagian perut", 0.5);
        expertCertaintyFactor1.put("Feses lembek / cair", 0.85);
        expertCertaintyFactor1.put("Dehidrasi", 0.8);
        this.diseases.put("Diare", expertCertaintyFactor1);
        
        Map<String, Double> expertCertaintyFactor2 = new HashMap<>();
        expertCertaintyFactor2.put("Nyeri dan kram pada bagian perut", 0.85);
        expertCertaintyFactor2.put("Hilang nafsu makan", 0.5);
        expertCertaintyFactor2.put("Pendarahan saat BAB", 0.65);
        expertCertaintyFactor2.put("Mudah merasa lelah", 0.7);
        expertCertaintyFactor2.put("Berat badan turun", 0.75);
        this.diseases.put("Kolitis Ulseratif", expertCertaintyFactor2);
        
        Map<String, Double> expertCertaintyFactor3 = new HashMap<>();
        expertCertaintyFactor3.put("Hilang nafsu makan", 0.8);
        expertCertaintyFactor3.put("Tidak bisa buang gas", 0.9);
        expertCertaintyFactor3.put("Merasa nyeri ketika buang air kecil", 0.5);
        expertCertaintyFactor3.put("Perut kembung", 0.7);
        expertCertaintyFactor3.put("Berat badan turun", 1.0);
        this.diseases.put("Apendisitis", expertCertaintyFactor3);
        
        Map<String, Double> expertCertaintyFactor4 = new HashMap<>();
        expertCertaintyFactor4.put("Mual dan muntah", 0.7);
        expertCertaintyFactor4.put("Hilang nafsu makan", 0.6);
        expertCertaintyFactor4.put("Rasa nyeri di bagian ulu hati hingga dada", 0.9);
        expertCertaintyFactor4.put("Sulit dalam bernafas", 0.75);
        expertCertaintyFactor4.put("Perut kembung", 0.5);
        this.diseases.put("Ulkus Duodenum", expertCertaintyFactor4);
        
        Map<String, Double> expertCertaintyFactor5 = new HashMap<>();
        expertCertaintyFactor5.put("Mual dan muntah", 0.6);
        expertCertaintyFactor5.put("Nyeri dan kram pada bagian perut", 0.7);
        expertCertaintyFactor5.put("Feses lembek / cair", 0.9);
        expertCertaintyFactor5.put("Hilang nafsu makan", 0.5);
        expertCertaintyFactor5.put("Pendarahan di sekitar anus", 0.6);
        this.diseases.put("Disentri", expertCertaintyFactor5);
        
        return this.diseases;
    }
    
}
