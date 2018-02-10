package prot3ct.workit.models.base;

import java.io.Serializable;
import java.util.Date;

public interface TaskContract extends Serializable {
    int getId();

    void setId(int id);

    public String getTitle();

    public void setTitle(String title);

    public String getStartDate();

    public void setStartDate(String startDate);

    public String getEndDate();

    public void setEndDate(String endDate);

    public String getDescription();

    public void setDescription(String description);

    public String getReward();

    public void setReward(String reward);

    public int getMinJobsCompleted();

    public void setMinJobsCompleted(int minJobsCompleted);

    public double getMinRating();

    public void setMinRating(double minRating);

    public String getCountry();

    public void setCountry(String country);

    public String getCity();

    public void setCity(String city);

    public String getCreatorEmail();

    public void setCreatorEmail(String creatorEmail);

    public String getAddress();

    public void setAddress(String address);
}
