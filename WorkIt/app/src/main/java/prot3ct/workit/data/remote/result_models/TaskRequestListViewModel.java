package prot3ct.workit.data.remote.result_models;

public class TaskRequestListViewModel {
    private int taskRequestId;
    private String taskTitle;
    private String status;

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaskRequestId() {
        return taskRequestId;
    }

    public void setTaskRequestId(int taskRequestId) {
        this.taskRequestId = taskRequestId;
    }
}
