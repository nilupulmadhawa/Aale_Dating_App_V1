package com.example.aale.ui.add_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.aale.databinding.FragmentAddUserBinding;

import com.example.aale.ui.advertisements.AdvertisementsViewModel;

public class AddUserFragment extends Fragment {

    private FragmentAddUserBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AdvertisementsViewModel advertisementsViewModel = new ViewModelProvider(this).get( AdvertisementsViewModel.class);
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       // final TextView textView = binding.textAdvertisements;
     //   advertisementsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}