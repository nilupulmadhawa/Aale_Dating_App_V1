package com.example.aale.ui.users;

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
import com.example.aale.databinding.FragmentSwipesBinding;
import com.example.aale.databinding.FragmentUsersBinding;
import com.example.aale.ui.swipes.SwipesViewModel;

public class UsersFragment extends Fragment {
    private FragmentUsersBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UsersViewModel usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        binding =  FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       // usersViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}