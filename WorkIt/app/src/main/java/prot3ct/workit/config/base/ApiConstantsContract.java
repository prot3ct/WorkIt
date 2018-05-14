package prot3ct.workit.config.base;

public interface ApiConstantsContract {
    String loginUrl();

    String registerUrl();

    String getAllJobsUrl();

    String getAllTasksUrl(int userId);

    String createTaskUrl();

    String getTaskRequestsForCurrentUserUrl(int userId);

    String createTaskRequestUrl();

    String getTaskRequestByIdUrl(int requestId);

    String createTaskRequestCommentUrl(int taskRequestId);

    String getTaskRequestComments(int taskRequestId);

    String getRequestsForTaskUrl(int taskId);

    String updateTaskRequestUrl();

    String getMyCompletedTasksUrl(int userId);

    String createRatingUrl(int userId);

    String getLocationLatLngUrl(String location);

    int responseSuccessCode();

    int responseErrorCode();

    int reponseServerErrorCode();
}