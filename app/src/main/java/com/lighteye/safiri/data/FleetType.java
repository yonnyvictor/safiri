package com.lighteye.safiri.data;

/**
 * Created by yonny on 7/20/16.
 */
public class FleetType {
    private int id;
    private String nodeKey;
    private String name;
    private int capacity;

    public FleetType() {
    }

    public FleetType(int id, String nodeKey, String name, int capacity) {
        this.id = id;
        this.nodeKey = nodeKey;
        this.name = name;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
