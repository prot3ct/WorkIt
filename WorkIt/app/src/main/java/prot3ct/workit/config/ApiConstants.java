package prot3ct.workit.config;

import com.google.android.gms.common.api.Api;

import prot3ct.workit.config.base.ApiConstantsContract;

public class ApiConstants implements ApiConstantsContract {
    private static final String API_URL = "http://workit.azurewebsites.net/api";

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
    public String createTaskUrl() {
        return API_URL + "/tasks";
    }

    @Override
    public String updateTaskUrl(int taskId) {
        return API_URL + "/tasks/" + taskId +"/update";
    }

    @Override
    public String getTaskDetailsUrl(int taskId) {
        return API_URL  + "/tasks/" + taskId;
    }

    @Override
    public String deleteTaskUrl(int taskId) {
        return API_URL + "/tasks/" + taskId;
    }

    @Override
    public String getAvailableTasks(int userId) {
        return API_URL + "/users/" + userId + "/available-tasks";
    }

    @Override
    public String getAssignedTasksUrl(int userId) {
        return  API_URL + "/users/" + userId + "/assigned-tasks";
    }

    @Override
    public String getCompletedTasksUrl(int userId) {
        return API_URL + "/users/" + userId + "/completed-tasks";
    }

    @Override
    public String getMyTasks(int userId) {
        return API_URL + "/users/" + userId + "/my-tasks";
    }

    @Override
    public String createTaskRequestUrl() { return API_URL + "/requests"; }

    @Override
    public String updateTaskRequestUrl(int requestId) {
        return API_URL + "/requests/" + requestId + "/update";
    }

    @Override
    public String getRequestsForTaskUrl(int taskId) {
        return API_URL + "/tasks/" + taskId + "/requests";
    }

    @Override
    public String deleteTaskRequestUrl(int taskRequestId) {
        return API_URL + "/requests/" + taskRequestId + "/delete";
    }

    @Override
    public String createRaitingUrl() {
        return API_URL + "/raitings";
    }

    @Override
    public String updateProfile(int userId) {
        return API_URL + "/users/" + userId + "/update";
    }

    @Override
    public String getProfileDetailsUrl(int userId) {
        return API_URL + "/users/" + userId;
    }

    @Override
    public String getLocationLatLngUrl(String location) {
        return "https://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&key=AIzaSyA4t0Wp6n0os2wVPs3JRoSnDDJf49JVgFM";
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
