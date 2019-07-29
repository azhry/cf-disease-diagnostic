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
    
    public CertaintyFactor() {
        // example data
        this.diseases = new HashMap<>();
        Map<String, Double> expertCertaintyFactor = new HashMap<>();
        expertCertaintyFactor.put("Demam Tinggi", 0.2);
        expertCertaintyFactor.put("Badan Lemah", 0.4);
        expertCertaintyFactor.put("Turun Berat Badan", 0.6);
        expertCertaintyFactor.put("Mengalami Aborsi", 0.4);
        this.diseases.put("Brucellosis", expertCertaintyFactor);
        
        Map<String, Double> userCertaintyFactor = new HashMap<>();
        userCertaintyFactor.put("Demam Tinggi", 1.0);
        userCertaintyFactor.put("Badan Lemah", 0.8);
        userCertaintyFactor.put("Turun Berat Badan", 1.0);
        userCertaintyFactor.put("Mengalami Aborsi", 1.0);
        
        this.calculateCertaintyFactor(userCertaintyFactor, expertCertaintyFactor);
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
