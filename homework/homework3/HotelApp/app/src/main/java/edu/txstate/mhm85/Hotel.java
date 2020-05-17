package edu.txstate.mhm85;

// A class to represent Hotels.
public class Hotel {
    private int id;
    private String name;
    private String city;
    private String state;
    private double costPerDay;
    private String url;
    private int imageId;
    private String imageContentDescription;

    public Hotel() {
        // Default constructor
    }

    public Hotel(int id, String name, String city, String state, double costPerDay, String url, int imageId, String imageContentDescription) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.costPerDay = costPerDay;
        this.url = url;
        this.imageId = imageId;
        this.imageContentDescription = imageContentDescription;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
