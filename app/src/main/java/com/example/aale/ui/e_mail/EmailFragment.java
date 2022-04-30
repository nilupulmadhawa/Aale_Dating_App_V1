package com.example.aale.ui.e_mail;

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
import com.example.aale.databinding.FragmentEmailBinding;


public class EmailFragment extends Fragment {


    private FragmentEmailBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        EmailViewModel emailViewModel = new ViewModelProvider(this).get(EmailViewModel.class);
        binding = FragmentEmailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //just like find view by id
        final TextView textView = binding.textEmail;
        emailViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}

