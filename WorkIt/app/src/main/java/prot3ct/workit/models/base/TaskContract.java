package prot3ct.workit.models.base;

import java.util.Date;

public interface TaskContract {
    public String getTitle();

    public void setTitle(String title);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public Date getEndDate();

    public void setEndDate(Date endDate);

    public String getDescription();

    public void setDescription(String description);

    public String getReward();

    public void setReward(String reward);

    public int getMinJobsCompleted();

    public void setMinJobsCompleted(int minJobsCompleted);

    public double getMinRating();

    public void setMinRating(double minRating);
}
