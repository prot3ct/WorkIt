package prot3ct.workit.models;

import java.util.Date;

import prot3ct.workit.models.base.TaskContract;

public class Task implements TaskContract
{
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;
    private String reward;
    private int minJobsCompleted;
    private double minRating;

    public Task(String title, Date startDate, Date endDate, String description, String reward, int minJobsCompleted, double minRating) {
        setTitle(title);
        setStartDate(startDate);
        setEndDate(endDate);
        setDescription(description);
        setReward(reward);
        setMinJobsCompleted(minJobsCompleted);
        setMinRating(minRating);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getMinJobsCompleted() {
        return minJobsCompleted;
    }

    public void setMinJobsCompleted(int minJobsCompleted) {
        this.minJobsCompleted = minJobsCompleted;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }
}
