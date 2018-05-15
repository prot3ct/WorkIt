package prot3ct.workit.config.base;

public interface ApiConstantsContract {
    String loginUrl();

    String registerUrl();

    String createTaskUrl();

    String updateTaskUrl(int taskId);

    String getTaskDetailsUrl(int taskId);

    String deleteTaskUrl(int taskId);

    String getAvailableTasks(int userId);

    String getAssignedTasksUrl(int userId);

    String getCompletedTasksUrl(int userId);

    String getMyTasks(int userId);

    String createTaskRequestUrl();

    String updateTaskRequestUrl(int requestId);

    String getRequestsForTaskUrl(int taskId);

    String deleteTaskRequestUrl(int taskRequestId);

    String createRaitingUrl();

    String getLocationLatLngUrl(String location);

    int responseSuccessCode();

    int responseErrorCode();

    int reponseServerErrorCode();
}