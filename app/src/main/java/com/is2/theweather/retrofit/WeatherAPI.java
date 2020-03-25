package com.is2.theweather.retrofit;

import com.is2.theweather.models.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {


    String BASE_URL = "http://api.openweathermap.org/";
    String API_KEY = "3ea610fb4c761b449fd66636cdf761a7";
    String UNITS_METRIC = "metric";

    @GET("data/2.5/weather")
    Call<Response> getWeather(@Query("q") String query,@Query("appid") String appId,@Query("units") String units);


    class Factory {
        private static WeatherAPI service;
        public static WeatherAPI getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(WeatherAPI.class);
                return service;
            } else {
                return service;
            }
        }
    }

}