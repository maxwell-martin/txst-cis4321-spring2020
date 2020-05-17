package edu.txstate.mhm85;

import org.json.JSONException;
import org.json.JSONObject;

// A class to represent Hotels.
public class Hotel {
    private int id;
    private String name;
    private String city;
    private String state;
    private double costPerDay;

    // Extra properties to programmatically add unique images and image descriptions to Hotel list.
    private int imageId;
    private String imageContentDescription;

    // Constructor for creating Hotel objects from JSON data retrieved from Firebase database.
    public Hotel(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.city = object.getString("city");
            this.state = object.getString("state");
            this.costPerDay = object.getDouble("costPerDay");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Default constructor
    public Hotel() { }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageContentDescription() {
        return imageContentDescription;
    }

    public void setImageContentDescription(String imageContentDescription) {
        this.imageContentDescription = imageContentDescription;
    }
}
