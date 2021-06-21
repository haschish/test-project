package com.example.myapplication.ui.settings;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentSettingsBinding;
import com.example.myapplication.model.Model;
import com.example.myapplication.work.Notify;

import java.util.concurrent.TimeUnit;


public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        createChannel();

        FragmentSettingsBinding binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        SettingsViewModel viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        viewModel.getNotification().observe(getViewLifecycleOwner(), (value) -> {
            if (value) {
                startNotificationWorker();
            } else {
                clearNotificationWorker();
            }
        });
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private void startNotificationWorker() {
        clearNotificationWorker();

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(Notify.class, 15, TimeUnit.MINUTES)
                .addTag(Notify.TAG)
                .build();
        OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(Notify.class).addTag(Notify.TAG).build();
        WorkManager.getInstance(getContext()).enqueue(workRequest);
    }

    private void clearNotificationWorker() {
        WorkManager.getInstance(getContext()).cancelAllWorkByTag(Notify.TAG);
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Notify.CHANNEL_ID, Notify.CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("A channel which shows notifications about events of dollar rate");
            channel.setLightColor(Color.MAGENTA);

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}