package edu.cse470.restaurantrater;

public class Dish extends Restaurant {
    private String dishName;
    private int dishID;
    private String rating;
    private String type;

    private int restaurantID;

    public Dish() {
        dishID = -1;
        restaurantID = 1;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getRestaurantID() {return restaurantID;}
    public void setRestaurantID(int restaurantID) {this.restaurantID = restaurantID;}

}
