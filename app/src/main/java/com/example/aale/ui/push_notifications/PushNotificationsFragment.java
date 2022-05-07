package com.example.aale.ui.push_notifications;

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
import com.example.aale.databinding.FragmentMyAccountBinding;
import com.example.aale.databinding.FragmentPushNotificationsBinding;
import com.example.aale.ui.my_account.MyAccountViewModel;

public class PushNotificationsFragment extends Fragment {

    private FragmentPushNotificationsBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PushNotificationsViewModel pushNotificationsViewModel = new ViewModelProvider(this).get(PushNotificationsViewModel.class);
        binding = FragmentPushNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       // final TextView textView = binding.textPushNotifications;
      //  pushNotificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}