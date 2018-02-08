package prot3ct.workit.config;

import prot3ct.workit.config.base.ApiConstantsContract;

public class ApiConstants implements ApiConstantsContract {
    private static final String API_URL = "https://workit-server.azurewebsites.net/api";
    private static final String URL_SIGN_IN = API_URL + "/auth/login";
    private static final String URL_SIGN_UP = API_URL + "/auth/register";
    private static final String URL_GET_JOBS = API_URL + "/jobs";
    private static final String URL_CREATE_TASK = API_URL + "/jobs/create";

    private static final int RESPNCE_SUCCESS_CODE = 200;
    private static final int RESPONSE_ERROR_CODE = 404;

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
    public int responseErrorCode() {
        return RESPONSE_ERROR_CODE;
    }
}
