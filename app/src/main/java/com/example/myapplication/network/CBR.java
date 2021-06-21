package com.example.myapplication.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

interface Api {
    @GET("/scripts/XML_dynamic.asp?date_req1=17/05/2021&date_req2=17/06/2021&VAL_NM_RQ=R01235")
    Call<List<Object>> getLastMonth();
}

public class CBR {
    private static final String BASE_URL = "https://cbr.ru";
    private static Retrofit retrofit;

    private static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Api getApi() {
        return getInstance().create(Api.class);
    }
}
