package com.example.aale.ui.add_user;

import android.app.AlertDialog;
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
import com.example.aale.ui.edit_user.EditUserFragmentDirections;
import com.google.android.material.textfield.TextInputLayout;

public class AddUserFragment extends Fragment {

    private FragmentAddUserBinding binding;
    private Button addAdminBtn;
    private EditText password;
    private EditText confirm_password;
    private EditText userEmail;
    private  EditText userName;

    private TextInputLayout adminEmailLayout;
    private TextInputLayout adminUserNameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout passwordConfirmLayout;

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

        adminEmailLayout=binding.edtUserEmailLayout;
        adminUserNameLayout=binding.edtUserNameLayout;
        passwordLayout=binding.edtPasswordLayout;
        passwordConfirmLayout= binding.edtConfirmPasswordLayout;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Admin admin =(Admin) UserFactory.getAdmin();



            addAdminBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    admin.setEmail(userEmail.getText().toString().trim());
                    admin.setPassword(password.getText().toString().trim());
                    admin.setUserName(userName.getText().toString().trim());

                    if(isValidAdmin(admin)) {
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(getContext());

                        // Set the message show for the Alert time
                        builder.setMessage("Do you want to confirm this update?");

                        // Set Alert Title
                        builder.setTitle("Add Admin !");

                        // Set Cancelable false
                        // for when the user clicks on the outside
                        // the Dialog Box then it will remain show
                        builder.setCancelable(false);

                        // Set the positive button with yes name
                        // OnClickListener method is use of
                        // DialogInterface interface.



                        builder.setPositiveButton("Yes", (dialog, which) -> {
                            // When the user click yes button
                            // then admin  will be added
                            addUserViewModel.addAdmin(admin).observe(getViewLifecycleOwner(), integer -> {
                                if (integer == 0) {
                                    Toast.makeText(getContext(), "Admin Details storing failed failed", Toast.LENGTH_SHORT).show();
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

                        });

                        // Set the Negative button with No name
                        // OnClickListener method is use
                        // of DialogInterface interface.
                        builder.setNegativeButton("No", (dialog, which) -> {
                            // If user click no
                            // then dialog box is canceled.
                            dialog.cancel();
                        });
                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();

                        // Show the Alert Dialog box
                        alertDialog.show();

                    }else{
                        Log.i("User input",userEmail.getText().toString());
                        Log.i("User input",password.getText().toString());
                        Log.i("User input",userName.getText().toString());
                        Toast.makeText(getContext(), "Please enter valid inputs", Toast.LENGTH_SHORT).show();
                    }


                }
            });

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



    }
    //validate admin
    private boolean isValidAdmin(Admin admin){
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
//        String passwordVPattern = "^" +
//                "(?=.*[0-9])" +         //at least 1 digit
//                "(?=.*[a-z])" +         //at least 1 lower case letter
//                "(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{6,}" +               //at least 4 characters
//                "$";
        boolean flag=true;
        if(admin.getEmail().isEmpty()){
            adminEmailLayout.setError("Admin email cannot be empty");
            flag=false;
        }
        if(admin.getPassword().isEmpty()){
            passwordLayout.setError("Password cannot be empty");
            flag=false;
        }
        if(confirm_password.getText().toString().isEmpty()){
            passwordConfirmLayout.setError("Confirm password cannot be empty");
            flag=false;
        }

        if(!admin.getPassword().matches(confirm_password.getText().toString().trim())){
            passwordConfirmLayout.setError("Passwords doesn't match");
            flag=false;

        }
        if(admin.getUserName().isEmpty()){

            adminUserNameLayout.setError("Username cannot be empty");
            flag=false;
        }
        if(!admin.getEmail().matches(emailPattern)) {
            adminEmailLayout.setError("Invalid email");
            flag=false;
        }
        if(flag){
            //remove error
            adminEmailLayout.setError(null);
            adminUserNameLayout.setError(null);
            passwordConfirmLayout.setError(null);
            passwordLayout.setError(null);
            //disable  errors to remove displayed space
            passwordLayout.setErrorEnabled(false);
            passwordConfirmLayout.setErrorEnabled(false);
            adminUserNameLayout.setErrorEnabled(false);
            adminEmailLayout.setErrorEnabled(false);

        }
        return  flag;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}