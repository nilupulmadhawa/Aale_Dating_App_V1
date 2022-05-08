package com.example.aale.ui.users;



import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aale.R;
import com.example.aale.databinding.FragmentUsersBinding;
import com.example.aale.model.Customer;
import com.example.aale.model.User;
import com.google.firebase.auth.FirebaseAuth;



public class UsersFragment extends Fragment  {
    private FragmentUsersBinding binding;
    private  UsersViewModel usersViewModel;
    private TableLayout userTable;
    private Button adduserBtn;
    private  Fragment adduser;
    private NavController  navController;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        userTable=binding.tableUser;
        adduserBtn=binding.addUser;
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController =Navigation.findNavController(view);

        adduserBtn.setOnClickListener(v -> navController.navigate(R.id.action_nav_users_to_nav_add_new_user2));


        usersViewModel.getUsers().observe(getViewLifecycleOwner(), customers -> {
          for(Customer customer : customers){
              //create table row
              //getContext return the context that this fragment currently associated with
              TableRow row = new TableRow(getContext());

              //add values
              //add to able
              row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
              row.setMinimumHeight((int)(1.5*34));

              //add value to row
              TextView userName = new TextView(getContext());
              TextView mobileNo = new TextView(getContext());
              TextView email = new TextView(getContext());
              TextView gender= new TextView(getContext());


              //set alignment
              userName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              mobileNo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              email.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              gender.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


              //give text Size
              userName.setTextSize(9);
              mobileNo.setTextSize(9);
              email.setTextSize(9);
              gender.setTextSize(9);
              email.setId((int)System.currentTimeMillis()/100000);


              //set values
              userName.setText(customer.getUserName());

              mobileNo.setText(customer.getPhone_number().toString());
              email.setText(customer.getEmail());
              gender.setText(customer.getGender());

              //Do not give layout parameters for linear layout
              //for button in the table
              //must add a linear lay out
              LinearLayout linearLayout = new LinearLayout(getContext());
              linearLayout.setOrientation(LinearLayout.HORIZONTAL);
              linearLayout.setGravity(Gravity.CENTER);

              LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                      64,64);

              layoutParams.setMargins(0, 0, 30, 0);
              //initialize email btn
              ImageButton emailBtn= new ImageButton(getContext());
              emailBtn.setLayoutParams(new LinearLayout.LayoutParams(layoutParams));
              emailBtn.setBackgroundResource(R.drawable.admin_mail);
              emailBtn.setId(customer.getPhone_number());
              emailBtn.setMaxHeight(64);
              emailBtn.setMaxWidth(64);
              emailBtn.setEnabled(true);

              //initialize edit btn
              Button  editBtn= new Button(getContext());
              editBtn.setLayoutParams( layoutParams);

              //editBtn
              editBtn.setId(customer.getPhone_number()+1);
              editBtn.setMaxHeight(64);
              editBtn.setBackgroundResource(R.drawable.admin_edit);
              editBtn.setMaxWidth(64);
              editBtn.setEnabled(true);


              //initialize email btn
              Button  deleteBtn= new Button(getContext());
              deleteBtn.setLayoutParams(new LinearLayout.LayoutParams(64,64));
              //set button attributes
              deleteBtn.setId(customer.getPhone_number()+2);
              deleteBtn.setMaxHeight(64);
              deleteBtn.setMaxWidth(64);
              deleteBtn.setEnabled(true);
              deleteBtn.setBackgroundResource(R.drawable.admin_delete);
              //add button to linearlayout
              linearLayout.addView(emailBtn);
              linearLayout.addView(editBtn);
              linearLayout.addView(deleteBtn);
              //add views to row
              row.addView(userName);
              row.addView(mobileNo);
              row.addView(email);
              row.addView(gender);
              row.addView(linearLayout);

              userTable.addView(row);

              //alert on delete
              deleteBtn.setOnClickListener(v -> {

                 AlertDialog.Builder builder
                         = new AlertDialog
                         .Builder(getContext());

                 // Set the message show for the Alert time
                 builder.setMessage("Do you want to delete this user?");

                 // Set Alert Title
                 builder.setTitle("Alert !");

                 // Set Cancelable false
                 // for when the user clicks on the outside
                 // the Dialog Box then it will remain show
                 builder.setCancelable(false);

                 // Set the positive button with yes name
                 // OnClickListener method is use of
                 // DialogInterface interface.

                 builder.setPositiveButton("Yes", (dialog, which) -> {
                     // When the user click yes button
                     // then app user will be removed
                     //firet get the email
                       String deleted_usersID=customer.getUserID();
                        usersViewModel.deleteUser(deleted_usersID).observe(getViewLifecycleOwner(),integer -> {
                             if(integer.equals(1)){
                                 userTable.removeView(row);

                                 Toast.makeText(getContext(),"User deleted ",Toast.LENGTH_SHORT).show();
                             }else{
                                 Toast.makeText(getContext(),"User is not deleted ",Toast.LENGTH_SHORT).show();
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



                 //to remove user


             });
             //navigate to email interface
             emailBtn.setOnClickListener((v) -> {

                     String adminEmail =mAuth.getCurrentUser().getEmail();
                     UsersFragmentDirections.ActionNavUsersToSendEmailFragment action= UsersFragmentDirections.actionNavUsersToSendEmailFragment();

                     action.setEmailId(customer.getEmail());
                     action.setAdminEmailId(adminEmail);
                     navController.navigate(action);
             });

             editBtn.setOnClickListener((v) -> {
                 //once edit button i clicked add user profile values as action parameters
                 UsersFragmentDirections.ActionNavUsersToNavEditUser action = UsersFragmentDirections.actionNavUsersToNavEditUser();
                 action.setUserName(customer.getUserName());
                 action.setUserId(customer.getUserID());
                 action.setPassword(customer.getPassword());
                 action.setUserEmailId(customer.getEmail());
                 action.setGender(customer.getGender());
                 action.setLookingFor(customer.getLooking_for());
                 action.setPhoneNumber(customer.getPhone_number());
                 navController.navigate(action);

             });
          }

        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}