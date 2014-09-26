package com.taliflo.app.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Singleton class which holds a field for the currently logged in user
 *
 * Created by Ntokozo Radebe on 1/4/2014.
 */
public class UserStore {

    private User currentUser;
    private String uid, authToken;

    private static UserStore instance = null;

    private UserStore () {}

    public static UserStore getInstance() {
        if (instance == null) {
            synchronized (UserStore.class) {
                if (instance == null) {
                    instance = new UserStore();
                }
            }
        }
        return instance;
    }

    public void setLoggedInCredentials(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        authToken = jsonObject.optString("auth_token");
        uid = jsonObject.optString("uid");
    }

    public HashMap<String, String> getLoggedInCredentials() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("authToken", authToken);
        hashMap.put("uid", uid);
        return hashMap;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
