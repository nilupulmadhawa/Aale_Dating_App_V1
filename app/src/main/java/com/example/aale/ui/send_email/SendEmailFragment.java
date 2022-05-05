package com.example.aale.ui.send_email;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.aale.databinding.FragmentSendEmailBinding;



public class SendEmailFragment extends Fragment {

    private FragmentSendEmailBinding binding;
    private TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SendEmailViewModel sendEmailViewModel = new ViewModelProvider(this).get( SendEmailViewModel.class);
        binding = FragmentSendEmailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       textView = binding.textSendEmail;
       // sendEmailViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get the arguments
        if(getArguments()!=null){
            SendEmailFragmentArgs args =SendEmailFragmentArgs.fromBundle(getArguments());
            textView.setText(args.getEmailId()+"  "+args.getAdminEmailId());
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}