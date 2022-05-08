package com.example.aale.ui.swipes;

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
import com.example.aale.databinding.FragmentReportsBinding;
import com.example.aale.databinding.FragmentSwipesBinding;
import com.example.aale.ui.reports.ReportsViewModel;

public class SwipesFragment extends Fragment {

    private FragmentSwipesBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SwipesViewModel swipesViewModel = new ViewModelProvider(this).get(SwipesViewModel.class);
        binding =  FragmentSwipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textSwipes;
        swipesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}