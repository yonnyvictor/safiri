package com.lighteye.safiri.data;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatType {
    private int id;
    private String name;
    private String nodeKey;

    public SeatType() {
    }

    public SeatType(int id, String name, String nodeKey) {
        this.id = id;
        this.name = name;
        this.nodeKey = nodeKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }
}
