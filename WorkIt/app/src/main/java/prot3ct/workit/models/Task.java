package prot3ct.workit.models;

import java.io.Serializable;

import prot3ct.workit.models.base.TaskContract;

public class Task implements TaskContract, Serializable {
    private String title;
    private String startDate;
    private String endDate;
    private String description;
    private String country;
    private String city;
    private String address;
    private String reward;
    private String creatorEmail;
    private double minRaiting;
    private int minJobsCompleted;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMinJobsCompleted() {
        return minJobsCompleted;
    }

    public void setMinJobsCompleted(int minJobsCompleted) {
        this.minJobsCompleted = minJobsCompleted;
    }

    public double getMinRating() {
        return minRaiting;
    }

    public void setMinRating(double minRaiting) {
        this.minRaiting = minRaiting;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
