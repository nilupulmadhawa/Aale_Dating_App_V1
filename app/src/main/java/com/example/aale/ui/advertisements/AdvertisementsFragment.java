package com.example.aale.ui.advertisements;

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
import com.example.aale.databinding.FragmentAdvertisementsBinding;
import com.example.aale.databinding.FragmentEmailBinding;
import com.example.aale.ui.e_mail.EmailViewModel;

public class AdvertisementsFragment extends Fragment {

    private FragmentAdvertisementsBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AdvertisementsViewModel advertisementsViewModel = new ViewModelProvider(this).get( AdvertisementsViewModel.class);
        binding = FragmentAdvertisementsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textAdvertisements;
        advertisementsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}