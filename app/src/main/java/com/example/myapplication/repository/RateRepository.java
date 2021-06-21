package com.example.myapplication.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.Record;
import com.example.myapplication.model.ValCurs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface CBR {
    public static final String BASE_URL = "https://cbr.ru";

    @GET("/scripts/XML_dynamic.asp?VAL_NM_RQ=R01235")
    Call<ValCurs> getLastMonth(@Query("date_req1") String dateFrom, @Query("date_req2") String dateTo);

//    @GET("/scripts/XML_daily.asp")
}

public class RateRepository {
    private final CBR api;

    public RateRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CBR.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        api = retrofit.create(CBR.class);
    }

    public void getLastMonth(MutableLiveData<List<Record>> list) {
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        Calendar calendar = Calendar.getInstance();
        Date stop = calendar.getTime();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        Date start = calendar.getTime();

        api.getLastMonth(df.format(start), df.format(stop)).enqueue(new Callback<ValCurs>() {
            @Override
            public void onResponse(Call<ValCurs> call, Response<ValCurs> response) {
                if (response.isSuccessful()) {
                    list.setValue(response.body().getRecordList());
                } else {
                    Log.d("RateRepository", "response is faild");
                }
            }

            @Override
            public void onFailure(Call<ValCurs> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
