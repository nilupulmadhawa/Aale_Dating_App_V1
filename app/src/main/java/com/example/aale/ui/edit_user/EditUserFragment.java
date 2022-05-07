package com.example.aale.ui.edit_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aale.databinding.FragmentAdvertisementsBinding;
import com.example.aale.databinding.FragmentEditUserBinding;
import com.example.aale.ui.advertisements.AdvertisementsViewModel;
import com.example.aale.ui.send_email.SendEmailFragmentArgs;

public class EditUserFragment extends Fragment {

    private FragmentEditUserBinding binding;
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userConfirmPassword;
    private Button editConfirm;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EditUserViewModel editUseriewModel = new ViewModelProvider(this).get( EditUserViewModel.class);
        binding = FragmentEditUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userName= binding.edtUn;
        userEmail= binding.edtUe;
        userPassword= binding.edtP;
        userConfirmPassword= binding.edtCP;
        editConfirm= binding.btnEditUser;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get the arguments
        if(getArguments()!=null) {
            EditUserFragmentArgs args = EditUserFragmentArgs.fromBundle(getArguments());
           userName.setText(args.getUserName());
           userEmail.setText(args.getUserEmailId());
           userPassword.setText(args.getPassword());


           editConfirm.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String username= userName.getText().toString();
                   String email=userEmail.getText().toString();
                   String userPasswords=userPassword.getText().toString();
                   String userPasswordConfirm=userConfirmPassword.getText().toString();
                   //if(!userPasswords.equals("")&&)

               }
           });

        }

    }

    private boolean isValid(){
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}