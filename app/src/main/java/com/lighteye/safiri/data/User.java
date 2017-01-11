package com.lighteye.safiri.data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yonny on 7/15/16.
 */
public class User {
    private String email, name;
    private HashMap<String, Object> timestampJoined;

    public User() {
    }

    public User(String email, String name, HashMap<String, Object> timestampJoined) {
        this.email = email;
        this.name = name;
        this.timestampJoined = timestampJoined;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("name", name);
        result.put("timestampJoined", timestampJoined);

        return result;
    }
}
