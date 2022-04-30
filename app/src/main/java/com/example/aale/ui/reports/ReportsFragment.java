package com.example.aale.ui.reports;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aale.R;
import com.example.aale.databinding.FragmentPushNotificationsBinding;
import com.example.aale.databinding.FragmentReportsBinding;
import com.example.aale.ui.push_notifications.PushNotificationsViewModel;

public class ReportsFragment extends Fragment {

    private FragmentReportsBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ReportsViewModel reportsViewModel = new ViewModelProvider(this).get(ReportsViewModel.class);
        binding =  FragmentReportsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textReports;
        reportsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}