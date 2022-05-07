package com.example.aale.ui.add_user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.aale.R;
import com.example.aale.databinding.FragmentAddUserBinding;

import com.example.aale.model.Admin;
import com.example.aale.model.UserFactory;
import com.example.aale.ui.advertisements.AdvertisementsViewModel;

public class AddUserFragment extends Fragment {

    private FragmentAddUserBinding binding;
    private Button addAdminBtn;
    private EditText password;
    private EditText confirm_password;
    private EditText userEmail;
    private  EditText userName;
    private AddUserViewModel addUserViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addUserViewModel = new ViewModelProvider(this).get( AddUserViewModel.class);
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        addAdminBtn=binding.btnAddUser;
        password=(EditText) binding.edtPasswordLayout.findViewById(R.id.edt_password);
        userEmail =(EditText)binding.edtUserEmailLayout.findViewById(R.id.edt_user_email);
        userName =(EditText)binding.edtUserNameLayout.findViewById(R.id.edt_user_name);
        confirm_password=(EditText) binding.edtConfirmPasswordLayout.findViewById(R.id.edt_confirm_password);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Admin admin =(Admin) UserFactory.getAdmin();



            addAdminBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!userEmail.getText().toString().equals("")&&!password.getText().toString().equals("")&&!userName.getText().toString().equals("")) {
                        admin.setEmail(userEmail.getText().toString().trim());
                        admin.setPassword(password.getText().toString().trim());
                        admin.setUserName(userName.getText().toString().trim());


                        addUserViewModel.addAdmin(admin).observe(getViewLifecycleOwner(), integer -> {
                            if (integer == 0) {
                                Toast.makeText(getContext(), "ADmin Details storing failed failed", Toast.LENGTH_SHORT).show();
                            }
                            if (integer == 1) {
                                Toast.makeText(getContext(), "Admin Added successfully", Toast.LENGTH_SHORT).show();
                                clearFields();
                            }
                            if (integer == -1) {
                                Toast.makeText(getContext(), "Auth a failed", Toast.LENGTH_SHORT).show();
                            }
                            if(integer==-2){
                                Toast.makeText(getContext(), "null admin", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }else{
                        Log.i("User input",userEmail.getText().toString());
                        Log.i("User input",password.getText().toString());
                        Log.i("User input",userName.getText().toString());


                    }
                }
            });



//        addAdminBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"Empty",Toast.LENGTH_SHORT).show();
//            }
//        });


    }

   private  void clearFields(){
        userEmail.setText("");
        confirm_password.setText("");
       password.setText("");
       userEmail.setText("");
       userName.setText("");

   }
    @Override
    public void onResume() {
        super.onResume();

        Admin admin =(Admin) UserFactory.getAdmin();
        Log.i("User input",userEmail.getText().toString());
        Log.i("User input",password.getText().toString());
        Log.i("User input",userName.getText().toString());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}