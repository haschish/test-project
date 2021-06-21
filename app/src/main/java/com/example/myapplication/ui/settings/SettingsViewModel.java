package com.example.myapplication.ui.settings;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {
    private static final String NOTIFICATION_KEY = "notification";
    private static final String VALUE_OF_MORE_THAN_KEY = "valueOfMoreThan";

    private MutableLiveData<Boolean> notification;
    private MutableLiveData<Double> valueOfMoreThan;

    private SavedStateHandle savedStateHandle;

    public SettingsViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;

        Log.d("SettingsViewModel notification", savedStateHandle.get(NOTIFICATION_KEY) + "");
        Log.d("SettingsViewModel value", savedStateHandle.get(NOTIFICATION_KEY) + "");
        Log.d("SettingsViewModel", savedStateHandle.keys() + "");
    }

    public LiveData<Boolean> getNotification() {
        return savedStateHandle.getLiveData(NOTIFICATION_KEY, false);
    }

    public void toggleNotification() {
//        Boolean newValue = !notification.getValue();
//        notification.setValue(newValue);
        Log.d("SettingsViewModel", savedStateHandle.get(NOTIFICATION_KEY) + "");
        Log.d("SettingsViewModel", savedStateHandle.get(VALUE_OF_MORE_THAN_KEY) + "");
        savedStateHandle.set(NOTIFICATION_KEY, !getNotification().getValue());
        savedStateHandle.set(VALUE_OF_MORE_THAN_KEY, getValueOfMoreThan().getValue());
        Log.d("SettingsViewModel", savedStateHandle.get(NOTIFICATION_KEY).toString());
        Log.d("SettingsViewModel", savedStateHandle.get(VALUE_OF_MORE_THAN_KEY).toString());
        Log.d("SettingsViewModel keys", savedStateHandle.keys() + "");
    }

    public LiveData<Double> getValueOfMoreThan() {
        return savedStateHandle.getLiveData(VALUE_OF_MORE_THAN_KEY, 75.0);
    }
}
