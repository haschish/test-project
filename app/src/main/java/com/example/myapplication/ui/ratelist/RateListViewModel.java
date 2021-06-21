package com.example.myapplication.ui.ratelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Record;
import com.example.myapplication.repository.RateRepository;

import java.util.ArrayList;
import java.util.List;

public class RateListViewModel extends ViewModel {
    private RateRepository rateRepository;

    public RateListViewModel() {
        rateRepository = new RateRepository();
        fetchRates();
    }

    private MutableLiveData<List<Record>> rateList = new MutableLiveData<>(new ArrayList<Record>());
    public LiveData<List<Record>> getRateList() {
        return rateList;
    }

    public void fetchRates() {
        rateRepository.getLastMonth(rateList);
    }
}
