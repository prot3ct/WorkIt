package prot3ct.workit.data.remote;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.models.Location;
import prot3ct.workit.models.Task;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.models.base.LocationContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.OkHttpRequester;

public class LocationData {
    private final OkHttpRequester httpRequester;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;

    public LocationData() {
        this.jsonParser = new GsonParser();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
    }

    public Observable<LocationContract> getLatLng(final String location) {
        Log.d("RResult4", apiConstants.getLocationLatLngUrl(location));
        return httpRequester
                .get(apiConstants.getLocationLatLngUrl(location))
                .map(new Function<HttpResponseContract, LocationContract>() {
                    @Override
                    public LocationContract apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        String responseBody = iHttpResponse.getBody();

                        JSONObject jsonObj = new JSONObject(responseBody);
                        JSONObject locationJson = jsonObj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                        return jsonParser.fromJson(locationJson.toString(), Location.class);
                    }
                });
    }
}
