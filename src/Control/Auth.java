/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus
 */
public class Auth {
    
    public static boolean isAuthenticated() {
        JSONObject session = Auth.getSession();
        boolean isLoggedIn = (boolean)session.get("logged_in");
        return isLoggedIn;
    }
    
    public static void login(String username, String password) {
        
        Auth.clearSession();
        List<Object> users = JsonHandler.readJsonFile("data/user-data.json");
        for (Object user : users) {
            JSONObject userJson = (JSONObject)user;
            if (username.equals(userJson.get("username")) && 
                    password.equals(userJson.get("password"))) {
                Auth.addSession(userJson);
                break;
            }
        }
        
    }
    
    public static void clearSession() {
        JSONObject session = new JSONObject();
        session.put("logged_in", false);
        session.put("current_user", null);
        JsonHandler.updateJsonObjectFile("data/session.json", session);
    }
    
    public static void addSession(JSONObject currentUser) {
        JSONObject session = new JSONObject();
        session.put("logged_in", true);
        session.put("current_user", currentUser);
        JsonHandler.updateJsonObjectFile("data/session.json", session);
    }
    
    public static JSONObject getSession() {
        return JsonHandler.readJsonObjectFile("data/session.json");
    }
}
