package prot3ct.workit.data.remote.result_models;

public class TaskRequestListViewModel {
    private int taskRequestId;
    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
