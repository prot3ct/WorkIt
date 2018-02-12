package prot3ct.workit.config.base;

public interface ApiConstantsContract {
    String loginUrl();

    String registerUrl();

    String getAllJobsUrl();

    String createTaskUrl();

    String getTaskRequestsForCurrentUserUrl(int userId);

    String createTaskRequestUrl();

    String getTaskRequestByIdUrl(int requestId);

    int responseErrorCode();

    int reponseServerErrorCode();
}