package edu.txstate.mhm85;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Attraction {
    private int id;
    private String name;
    private String url;
    private int image;
    private double cost;

    public Attraction(JSONObject object) {
        try {
            this.id = object.getInt("Id");
            this.name = object.getString("Name");
            this.url = object.getString("Url");
            this.cost = object.getDouble("Cost");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Attraction() {
        // Default constructor
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toString() {
        return name;
    }
}
