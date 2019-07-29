/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Azhary Arliansyah
 */
public class MathFx {
    
    public static int sumMap(Map<String, Integer> map) {
        int total = 0;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            total += e.getValue();
        }
        return total;
    }
    
    public static double randUniform(int n) {
        return Math.random() * n;
    }
    
    public static int randInt(int range) {
        Random rand = new Random();
        return rand.nextInt(range + 1);
    }
    
    public static Double euclideanDistance(List<? extends Number> x1, 
            List<? extends Number> x2) {
        Double result = 0.00;
        if (x1.size() != x2.size()) {
            throw new Error("You should pass two lists of same size");
        }
        
        Iterator x1Iterator = x1.iterator();
        Iterator x2Iterator = x2.iterator();
        
        while (x1Iterator.hasNext() && x2Iterator.hasNext()) {
            result += Math.pow((double)x1Iterator.next() -
                    (double)x2Iterator.next(), 2);
        }

        return Math.sqrt(result);
    }
    
    public static List<Map.Entry<Integer, Double>> sortMap(
            Map<Integer, Double> map, final String order) {
        Set<Map.Entry<Integer, Double>> set = map.entrySet();
        List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<
                Integer, Double>>(set);
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>(){
            public int compare(Map.Entry<Integer, Double> o1, 
                    Map.Entry<Integer, Double> o2) {
                switch (order) {
                    case "DESC":
                        return (o2.getValue()).compareTo(o1.getValue());
                    
                    case "ASC":
                        return (o1.getValue()).compareTo(o2.getValue());
                }
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        return list;
    }
    
    public static List<Map.Entry<String, Double>> sortMapDouble(
            Map<String, Double> map, final String order) {
        Set<Map.Entry<String, Double>> set = map.entrySet();
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<
                String, Double>>(set);
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>(){
            public int compare(Map.Entry<String, Double> o1, 
                    Map.Entry<String, Double> o2) {
                switch (order) {
                    case "DESC":
                        return (o2.getValue()).compareTo(o1.getValue());
                    
                    case "ASC":
                        return (o1.getValue()).compareTo(o2.getValue());
                }
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        return list;
    }

    public static int min(List<Integer> list) {
        int lowest = Integer.MAX_VALUE;
        for (int i : list) {
            if (i < lowest) {
                lowest = i;
            }
        }
        return lowest;
    }
    
    public static int maxIndex(List<Double> list) {
        double highest = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > highest) {
                highest = list.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public static int maxIndex(double[] list) {
        double highest = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > highest) {
                highest = list[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public static int sum(List<Integer> list) {
        int total = 0;
        for (int x : list) {
            total += x;
        }
        return total;
    }
    
    public static double sum(ArrayList<Double> arr) {
        double total = 0;
        for (double x : arr) {
            total += x;
        }
        return total;
    }
    
    public static int countBooleanFrequency(List<Boolean> data, boolean e) {
        return Collections.frequency(data, e);
    }
}
