package prot3ct.workit.data.remote.result_models;

public class EditTaskViewModel {
    private int id;
    private String title;
    private String startDate;
    private int length;
    private String description;
    private String city;
    private String address;
    private double reward;
    private double minRaiting;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getMinRaiting() {
        return minRaiting;
    }

    public void setMinRaiting(double minRaiting) {
        this.minRaiting = minRaiting;
    }
}
