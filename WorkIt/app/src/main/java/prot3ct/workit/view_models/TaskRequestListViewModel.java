package prot3ct.workit.view_models;

public class TaskRequestListViewModel {
    private int taskRequestId;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public int getTaskRequestId() {
        return taskRequestId;
    }

    public void setTaskRequestId(int taskRequestId) {
        this.taskRequestId = taskRequestId;
    }
}
