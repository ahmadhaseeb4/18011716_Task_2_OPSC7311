package com.task2.a18011716_task_2_opsc7311;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String currentHeight;
    private String currentWeight;
    private String weightGoal;
    private String calorieIntakeGoal;
    private String deltaWeight;
    private String system;

    public User() { }

    public User(String id, String username, String firstName, String lastName, String currentHeight, String currentWeight, String weightGoal, String calorieIntakeGoal, String deltaWeight, String system) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentHeight = currentHeight;
        this.currentWeight = currentWeight;
        this.weightGoal = weightGoal;
        this.calorieIntakeGoal = calorieIntakeGoal;
        this.deltaWeight = deltaWeight;
        this.system = system;
    }

    public String getID() { return id; }

    public void setID(String id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(String currentHeight) {
        this.currentHeight = currentHeight;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(String weightGoal) {
        this.weightGoal = weightGoal;
    }

    public String getCalorieIntakeGoal() {
        return calorieIntakeGoal;
    }

    public void setCalorieIntakeGoal(String calorieIntakeGoal) { calorieIntakeGoal = calorieIntakeGoal; }

    public String getUserId() {
        return id;
    }

    public void setUserId(String id) {
        this.id = id;
    }

    public String getDeltaWeight() {
        return deltaWeight;
    }

    public void setDeltaWeight(String deltaWeight) {
        this.deltaWeight = deltaWeight;
    }

    public String getSystem() { return system; }

    public void setSystem(String system) { this.system = system; }
}
