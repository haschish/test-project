package com.example.myapplication.ui.ratelist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentRateListBinding;
import com.example.myapplication.model.Model;

public class RateListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRateListBinding binding = FragmentRateListBinding.inflate(getLayoutInflater());
        RateListViewModel viewModel = new ViewModelProvider(this).get(RateListViewModel.class);
        RateListAdapter adapter = new RateListAdapter(viewModel.getRateList());

        binding.rateRecyclerView.setAdapter(adapter);
        viewModel.getRateList().observe(getViewLifecycleOwner(), (newList) -> {
            adapter.notifyDataSetChanged();
        });

        return binding.getRoot();
    }
}