package prot3ct.workit.config;

import prot3ct.workit.config.base.ApiConstantsContract;

public class ApiConstants implements ApiConstantsContract {
//    private static final String API_URL = "https://workit-server.azurewebsites.net/api";
    private static final String API_URL = "http://workit-server20180513121646.azurewebsites.net/api";

    private static final int RESPNCE_SUCCESS_CODE = 200;
    private static final int RESPONSE_ERROR_CODE = 404;
    private static final int RESPONSE_SERVER_ERROR_CODE = 500;

    @Override
    public String loginUrl() {
        return API_URL + "/auth/login";
    }

    @Override
    public String registerUrl() {
        return API_URL + "/auth/register";
    }

    @Override
    public String getAllJobsUrl() { return API_URL + "/tasks"; }

    @Override
    public String getAllTasksUrl(int userId) {
        return API_URL + "/users/" + userId + "/tasks";
    }

    @Override
    public String createTaskUrl() {
        return API_URL + "/tasks";
    }

    @Override
    public String updateTaskUrl(int taskId) {
        return API_URL + "/tasks/" + taskId +"/update";
    }

    @Override
    public String getTaskByIdUrl(int taskId) {
        return API_URL  + "/tasks/" + taskId;
    }

    @Override
    public String createTaskRequestUrl() { return API_URL + "/requests"; }

    @Override
    public String updateTaskRequestUrl(int requestId) {
        return API_URL + "/requests/" + requestId + "/update";
    }

    @Override
    public String getTaskRequestsForCurrentUserUrl(int userId) { return API_URL + "/users/" + userId + "/requests"; }

    @Override
    public String getTaskRequestByIdUrl(int requestId) {
        return API_URL + "/requests/" + requestId;
    }

    @Override
    public String getRequestsForTaskUrl(int taskId) {
        return API_URL + "/tasks/" + taskId + "/requests";
    }

    @Override
    public String getMyCompletedTasksUrl(int userId) {
        return API_URL + "/users/" + userId + "/completed-tasks";
    }

    @Override
    public String getLocationLatLngUrl(String location) {
        return "https://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&key=AIzaSyA4t0Wp6n0os2wVPs3JRoSnDDJf49JVgFM";
    }

    @Override
    public String createRatingUrl(int userId) {
        return API_URL + "/users/" + userId + "/raitings";
    }

    @Override
    public int responseSuccessCode() {
        return RESPNCE_SUCCESS_CODE;
    }

    @Override
    public int responseErrorCode() {
        return RESPONSE_ERROR_CODE;
    }

    @Override
    public int reponseServerErrorCode() { return RESPONSE_SERVER_ERROR_CODE; }
}
