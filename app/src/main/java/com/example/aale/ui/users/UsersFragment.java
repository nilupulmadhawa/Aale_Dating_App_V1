package com.example.aale.ui.users;


import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.aale.databinding.FragmentUsersBinding;
import com.example.aale.model.Customer;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersFragment extends Fragment {
    private FragmentUsersBinding binding;
    private  UsersViewModel usersViewModel;
    private TableLayout userTable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        userTable=binding.tableUser;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        usersViewModel.getUsers().observe(getViewLifecycleOwner(), customers -> {
          for(Customer customer : customers){
              //create table row
              //getContext return the context that this fragment currently associated with
              TableRow row = new TableRow(getContext());
              //add values
              //add to atble
              row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
              row.setMinimumHeight((int)(1.5*34));
              //add valuew to row
              TextView firstName = new TextView(getContext());
              TextView lastName = new TextView(getContext());
              TextView email = new TextView(getContext());
              TextView userType = new TextView(getContext());

              TextView password = new TextView(getContext());
              //set aligment
              firstName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              lastName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              email.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              userType.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

              password.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

              //give text Size
              //from sp
              firstName.setTextSize(9);
              lastName.setTextSize(9);
              email.setTextSize(5);
              userType.setTextSize(9);

              password.setTextSize(9);
             //set values
              firstName.setText(customer.getUserName());
              lastName.setText(customer.getPhone_number().toString());
              email.setText(customer.getEmail());
              userType.setText(customer.getUserType());
              //must add a linear lay out
              LinearLayout linearLayout = new LinearLayout(getContext());
              linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
              linearLayout.setOrientation(LinearLayout.HORIZONTAL);
              //initialize email btn
              Button  emailBtn= new Button(getContext());
            //  emailBtn.setBackground(getContext().getDrawable(R.drawable.admin_mail));
              emailBtn.setId(customer.getPhone_number());
              emailBtn.setMinimumHeight(24);
              emailBtn.setMinimumWidth(24);
              //add email btn to linera lay out
            //  linearLayout.addView(emailBtn);

              //initialize edit btn
              Button  editBtn= new Button(getContext());
              //emailBtn.setBackground(getContext().getDrawable(R.drawable.admin_edit));
              editBtn.setId(customer.getPhone_number()+1);
              editBtn.setMinimumHeight(24);
              editBtn.setMinimumWidth(24);
              //add email btn to linera lay out
             // linearLayout.addView(editBtn);


              //initialize email btn
              Button  deleteBtn= new Button(getContext());
          //    emailBtn.setBackground(getContext().getDrawable(R.drawable.admin_delete));
              deleteBtn.setId(customer.getPhone_number()+2);
              deleteBtn.setMinimumHeight(24);
              deleteBtn.setMinimumWidth(24);
              //add email btn to linera lay out
             // linearLayout.addView(deleteBtn);
              linearLayout.addView(password);

              //add view
              row.addView(firstName);
              row.addView(lastName);
              row.addView(email);
              row.addView(userType);
             // row.addView(linearLayout);

              userTable.addView(row);

          }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}