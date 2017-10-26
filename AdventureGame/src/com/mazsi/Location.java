package com.mazsi;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private final int locationId;
    private final String descreption;
    private final Map<String, Integer> exits;

    public Location(int locationId, String descreption) {
        this.locationId = locationId;
        this.descreption = descreption;
        this.exits = new HashMap<>();
        this.exits.put("Q", 0);
    }

    public void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescreption() {
        return descreption;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }
}
