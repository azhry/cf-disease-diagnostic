/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

// using json_simple.jar
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Azhary Arliansyah
 */
public class JsonHandler {
    
    public static void updateJsonFile(String filename, List<Object> newValue) {
        try (FileWriter file = new FileWriter(filename)) {
            
            JSONArray json = new JSONArray();
            for (Object o : newValue) {
                json.add(o);
            }
            file.write(json.toString());
            
        } catch (IOException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateJsonObjectFile(String filename, JSONObject newValue) {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(newValue.toString());
        } catch (IOException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static JSONObject readJsonObjectFile(String filename) {
        JSONObject json = new JSONObject();
        try (Reader reader = new FileReader(filename)) {
            
            JSONParser parser = new JSONParser();
            json = (JSONObject)parser.parse(reader);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
    public static List<Object> readJsonFile(String filename) {
        List<Object> result = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(filename)) {
            
            JSONArray json = (JSONArray)parser.parse(reader);
            for (Object o : json) {
                result.add(o);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
