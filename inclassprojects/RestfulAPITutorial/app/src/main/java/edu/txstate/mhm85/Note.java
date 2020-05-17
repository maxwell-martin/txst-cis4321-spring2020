package edu.txstate.mhm85;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {
    private int id;
    private String location;
    private String name;

    public Note(JSONObject object) {
        try {
            this.id = object.getInt("Id");
            this.location = object.getString("Location");
            this.name = object.getString("Name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return id + ", " + name;
    }
}
