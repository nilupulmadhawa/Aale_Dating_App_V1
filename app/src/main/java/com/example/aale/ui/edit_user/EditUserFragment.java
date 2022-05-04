package com.example.aale.ui.edit_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aale.databinding.FragmentAdvertisementsBinding;
import com.example.aale.ui.advertisements.AdvertisementsViewModel;

public class EditUserFragment extends Fragment {

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