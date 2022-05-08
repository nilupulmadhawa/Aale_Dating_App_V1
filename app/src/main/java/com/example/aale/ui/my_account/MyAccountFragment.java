package com.example.aale.ui.my_account;

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
import com.example.aale.databinding.FragmentMyAccountBinding;
import com.example.aale.ui.advertisements.AdvertisementsViewModel;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyAccountViewModel myAccountViewModel = new ViewModelProvider(this).get( MyAccountViewModel.class);
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
      //  final TextView textView = binding.textMyAccount;
     //   myAccountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}