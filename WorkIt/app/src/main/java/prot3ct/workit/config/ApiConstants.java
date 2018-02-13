package prot3ct.workit.config;

import prot3ct.workit.config.base.ApiConstantsContract;

public class ApiConstants implements ApiConstantsContract {
    private static final String API_URL = "https://workit-server.azurewebsites.net/api";
    private static final String URL_SIGN_IN = API_URL + "/auth/login";
    private static final String URL_SIGN_UP = API_URL + "/auth/register";
    private static final String URL_GET_JOBS = API_URL + "/tasks";
    private static final String URL_CREATE_TASK = API_URL + "/tasks";

    private static final int RESPNCE_SUCCESS_CODE = 200;
    private static final int RESPONSE_ERROR_CODE = 404;
    private static final int RESPONSE_SERVER_ERROR_CODE = 500;

    @Override
    public String loginUrl() {
        return URL_SIGN_IN;
    }

    @Override
    public String registerUrl() {
        return URL_SIGN_UP;
    }

    @Override
    public String getAllJobsUrl() { return URL_GET_JOBS; }

    @Override
    public String createTaskUrl() {
        return URL_CREATE_TASK;
    }

    @Override
    public String createTaskRequestUrl() { return API_URL + "/requests"; }

    @Override
    public String getTaskRequestsForCurrentUserUrl(int userId) { return API_URL + "/users/" + userId + "/requests"; }

    @Override
    public String getTaskRequestByIdUrl(int requestId) {
        return API_URL + "/requests/" + requestId;
    }

    @Override
    public String createTaskRequestCommentUrl(int taskRequestId) {
        return API_URL + "/requests/" + taskRequestId + "/comments";
    }

    @Override
    public String getTaskRequestComments(int taskRequestId) {
        return API_URL + "/requests/" + taskRequestId + "/comments";
    }

    @Override
    public int responseErrorCode() {
        return RESPONSE_ERROR_CODE;
    }

    @Override
    public int reponseServerErrorCode() { return RESPONSE_SERVER_ERROR_CODE; }
}
