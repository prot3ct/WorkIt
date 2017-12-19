package prot3ct.workit.config;

import prot3ct.workit.config.base.ApiConstantsContract;

public class ApiConstants implements ApiConstantsContract {
    private static final String API_URL = "https://infinote.herokuapp.com/api";
    private static final String URL_SIGN_IN = API_URL + "/auth/login";
    private static final String URL_SIGN_UP = API_URL + "/auth/register";

    private static final int RESPONSE_ERROR_CODE = 404;

    @Override
    public String signInUrl() {
        return URL_SIGN_IN;
    }

    @Override
    public String signUpUrl() {
        return URL_SIGN_UP;
    }
}
