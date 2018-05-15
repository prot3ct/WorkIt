package prot3ct.workit.config.base;

public interface ApiConstantsContract {
    String loginUrl();

    String registerUrl();

    String getAllJobsUrl();

    String getAllTasksUrl(int userId);

    String createTaskUrl();

    String updateTaskUrl(int taskId);

    String getTaskByIdUrl(int taskId);

    String getTaskRequestsForCurrentUserUrl(int userId);

    String createTaskRequestUrl();

    String updateTaskRequestUrl(int requestId);

    String getTaskRequestByIdUrl(int requestId);

    String getRequestsForTaskUrl(int taskId);

    String getMyCompletedTasksUrl(int userId);

    String createRatingUrl(int userId);

    String getLocationLatLngUrl(String location);

    int responseSuccessCode();

    int responseErrorCode();

    int reponseServerErrorCode();
}