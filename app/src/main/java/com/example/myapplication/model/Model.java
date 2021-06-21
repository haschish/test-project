package com.example.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.repository.RateRepository;

import java.util.List;

public class Model extends ViewModel {

    private RateRepository rateRepository;

    public Model() {
        rateRepository = new RateRepository();
    }

    private MutableLiveData<Boolean> notification = new MutableLiveData<>(false);
    public LiveData<Boolean> getNotification() {
        return notification;
    }

    private MutableLiveData<List<Record>> rateList = new MutableLiveData<>();
    public LiveData<List<Record>> getRateList() {
        return rateList;
    }

    public void toggleNotification() {
        notification.setValue(!notification.getValue());
    }

    public void fetchRates() {
        rateRepository.getLastMonth(rateList);
    }
}
