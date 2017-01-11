package com.lighteye.safiri.data;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yonny on 7/14/16.
 */
public class Town {

    @SerializedName("name")
    private String name;
    private int id;
    private String nodeKey;


    public Town() {
    }

    public Town(int id, String nodeKey, String name) {
        this.id = id;
        this.nodeKey = nodeKey;
        this.name = name;
    }
    @Exclude
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    @Exclude
    public String getNodeKey() {
        return nodeKey;
    }
    @Exclude
    public void setId(int id) {
        this.id = id;
    }
    @Exclude
    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);

        return result;
    }

    @Override
    public String toString() {
        return "Town{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", nodeKey='" + nodeKey + '\'' +
                '}';
    }
}
