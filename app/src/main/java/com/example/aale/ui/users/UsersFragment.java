package com.example.aale.ui.users;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aale.R;
import com.example.aale.databinding.FragmentSwipesBinding;
import com.example.aale.databinding.FragmentUsersBinding;
import com.example.aale.model.Customer;
import com.example.aale.ui.swipes.SwipesViewModel;

import java.util.List;

public class UsersFragment extends Fragment {
    private FragmentUsersBinding binding;
    private  UsersViewModel usersViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        usersViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
              for(Customer customer : customers){

              }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}