package edu.cse470.restaurantrater;

public class Restaurant {
    private String restaurantName;
    private String restaurantAddress;
    private String city;
    private String state;
    private String zip;

    private static int restaurantID;

    public Restaurant() {
        restaurantID = -1;

    }

    public static int getRestaurantId() {
        return restaurantID;
    }

    public void setRestaurantId(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getrestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
