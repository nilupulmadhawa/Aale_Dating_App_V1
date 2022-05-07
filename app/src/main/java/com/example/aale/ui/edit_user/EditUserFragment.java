package com.example.aale.ui.edit_user;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.aale.databinding.FragmentAdvertisementsBinding;
import com.example.aale.databinding.FragmentEditUserBinding;
import com.example.aale.model.Customer;
import com.example.aale.model.UserFactory;
import com.example.aale.ui.advertisements.AdvertisementsViewModel;
import com.example.aale.ui.send_email.SendEmailFragmentArgs;
import com.example.aale.ui.users.UsersFragmentDirections;
import com.google.android.material.textfield.TextInputLayout;

public class EditUserFragment extends Fragment {

    private FragmentEditUserBinding binding;
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userConfirmPassword;
    private Button editConfirm;
    private EditUserViewModel editUserViewModel;

    private TextInputLayout customerEmailLayout;
    private TextInputLayout customerUserNameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout passwordConfirmLayout;


    private Customer cus;
    private NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editUserViewModel = new ViewModelProvider(this).get( EditUserViewModel.class);
        binding = FragmentEditUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //bind text input field
        userName= binding.edtUn;
        userEmail= binding.edtUe;
        userPassword= binding.edtP;
        userConfirmPassword= binding.edtCP;
        editConfirm= binding.btnEditUser;

        //bind text input layout
        customerEmailLayout= binding.edtUeLayout;
        customerUserNameLayout= binding.edtUnLayout;
        passwordLayout= binding.edtPLayout;
        passwordConfirmLayout= binding.edtCPLayout;

        //initional customer
        cus= (Customer)UserFactory.getCustomer();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        //check if the navigation args are not received
         if(getArguments()!=null) {
            EditUserFragmentArgs args = EditUserFragmentArgs.fromBundle(getArguments());
           userName.setText(args.getUserName());
           userEmail.setText(args.getUserEmailId());
           userPassword.setText(args.getPassword());
           userConfirmPassword.setText(args.getPassword());


           editConfirm.setOnClickListener(v -> {
               String username= userName.getText().toString().trim();
               String email=userEmail.getText().toString().trim();
               String userPasswords=userPassword.getText().toString().trim();

               cus.setPassword(userPasswords);
               cus.setEmail(email);
               cus.setUserName(username);
               cus.setUserID(args.getUserId());
               cus.setGender(args.getGender());
               cus.setLooking_for(args.getLookingFor());
               cus.setPhone_number(args.getPhoneNumber());

               if(isValidCustomer(cus)){


               AlertDialog.Builder builder
                       = new AlertDialog
                       .Builder(getContext());

               // Set the message show for the Alert time
               builder.setMessage("Do you want to confirm this update?");

               // Set Alert Title
               builder.setTitle("Update Customer !");

               // Set Cancelable false
               // for when the user clicks on the outside
               // the Dialog Box then it will remain show
               builder.setCancelable(false);

               // Set the positive button with yes name
               // OnClickListener method is use of
               // DialogInterface interface.

               builder.setPositiveButton("Yes", (dialog, which) -> {
                   // When the user click yes button
                   // then app user will be updated
                   editUserViewModel.updateCustomer(cus).observe(getViewLifecycleOwner(),integer -> {
                       if(integer >0){
                           Toast.makeText(getContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();;

                         navController.navigate(EditUserFragmentDirections.actionNavEditUserToNavUsers());

                       }else{
                           Toast.makeText(getContext(),"Update unsuccessful",Toast.LENGTH_SHORT).show();;
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
                   Toast.makeText(getContext(),"Please check your inputs",Toast.LENGTH_SHORT).show();;
               }

           });

        }

    }

    private boolean isValidCustomer(Customer customer){
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean flag=true;
        if(customer.getEmail().isEmpty()){
            customerEmailLayout.setError("Admin email cannot be empty");
            flag=false;
        }
        if(customer.getPassword().isEmpty()){
            passwordLayout.setError("Password cannot be empty");
            flag=false;
        }
        if(!customer.getPassword().matches(userConfirmPassword.getText().toString().trim())){
            passwordConfirmLayout.setError("Passwords doesn't match");
            Log.i("Cus object ",customer.getPassword()+"*(9");
            Log.i("Cus object ",userConfirmPassword.getText().toString().trim()+"*(9");

            flag=false;

        }
        if(customer.getUserName().isEmpty()){

            customerUserNameLayout.setError("Username cannot be empty");
            flag=false;
        }
        if(!customer.getEmail().matches(emailPattern)) {
            customerEmailLayout.setError("Invalid email");
            flag=false;
        }
        if(flag){

            //remove error
            customerEmailLayout.setError(null);
            customerUserNameLayout.setError(null);
            passwordConfirmLayout.setError(null);
            passwordLayout.setError(null);

            //disable  errors to remove displayed space
            passwordLayout.setErrorEnabled(false);
            passwordConfirmLayout.setErrorEnabled(false);
            customerUserNameLayout.setErrorEnabled(false);
            customerEmailLayout.setErrorEnabled(false);

        }
        return  flag;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}