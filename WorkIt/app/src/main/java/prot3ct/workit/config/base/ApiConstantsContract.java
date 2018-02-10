package prot3ct.workit.config.base;

public interface ApiConstantsContract {
    String loginUrl();

    String registerUrl();

    String getAllJobsUrl();

    String createTaskUrl();

    int responseErrorCode();

    int reponseServerErrorCode();

    String CreateTaskRequestUrl();
}