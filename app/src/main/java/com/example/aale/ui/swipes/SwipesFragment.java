package com.example.aale.ui.swipes;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.example.aale.databinding.FragmentReportsBinding;
import com.example.aale.databinding.FragmentSwipesBinding;
import com.example.aale.model.Customer;
import com.example.aale.model.Swipe;
import com.example.aale.repo.DBConnectionRepository;
import com.example.aale.ui.reports.ReportsViewModel;
import com.example.aale.ui.users.UsersFragmentDirections;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class SwipesFragment extends Fragment {

    private FragmentSwipesBinding binding;
    private TableLayout swipeTable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SwipesViewModel swipesViewModel = new ViewModelProvider(this).get(SwipesViewModel.class);
        binding =  FragmentSwipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        swipeTable=binding.tableSwipe;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseReference swipeDb = DBConnectionRepository.getSwipeReference();

        swipeDb.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    List<Swipe> customers = new ArrayList<>();
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        Swipe swipe = ds.getValue(Swipe.class);
                        Log.i("Swipes",swipe.toString());
                        //create table row
                        //getContext return the context that this fragment currently associated with
                        TableRow row = new TableRow(getContext());

                        //add values
                        //add to able
                        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        row.setMinimumHeight((int) (1.5 * 34));

                        //add value to row
                        TextView author = new TextView(getContext());
                        TextView type = new TextView(getContext());
                        TextView date = new TextView(getContext());
                        TextView profile_swiped = new TextView(getContext());


                        //set alignment
                        author.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        type.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        profile_swiped.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                        //give text Size
                        author.setTextSize(9);
                        type.setTextSize(9);
                        date.setTextSize(9);
                        profile_swiped.setTextSize(9);

                        //Do not give layout parameters for linear layout
                        //for button in the table
                        //must add a linear lay out
                        LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setGravity(Gravity.CENTER);

                        LinearLayout linearLayoutSwitch = new LinearLayout(getContext());
                        linearLayoutSwitch.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayoutSwitch.setGravity(Gravity.CENTER);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                64, 64);
                        LinearLayout.LayoutParams layoutParamsSwitch = new LinearLayout.LayoutParams(
                                120, 64);


                        layoutParams.setMargins(0, 0, 30, 0);

                        //set material spinner
                        SwitchMaterial switchMaterial = new SwitchMaterial(getContext());
                        //initialize switch
                        switchMaterial.setLayoutParams(layoutParamsSwitch);
                        switchMaterial.setTextOn("seen");
                        switchMaterial.setTextOff("Unseen");
                        switchMaterial.setMaxHeight(64);
                        switchMaterial.setMaxWidth(64);
                        //  switchMaterial.setUseMaterialThemeColors();
                        if (swipe.isSeen_status()) {
                            switchMaterial.setChecked(true);
                        }
                        //set text values
                        author.setText(swipe.getAuthor());
                        type.setText(swipe.getSwipe_type());
                        date.setText(swipe.getSwiped_date());
                        profile_swiped.setText(swipe.getSwiped_by());
                        //initialize edit btn
                        Button editBtn = new Button(getContext());
                        editBtn.setLayoutParams(layoutParams);
                        //editBtn

                        editBtn.setMaxHeight(64);
                        editBtn.setBackgroundResource(R.drawable.admin_edit);
                        editBtn.setMaxWidth(64);
                        editBtn.setEnabled(true);


                        //initialize delete btn
                        Button deleteBtn = new Button(getContext());
                        deleteBtn.setLayoutParams(new LinearLayout.LayoutParams(64, 64));
                        //set button attributes
                        deleteBtn.setMaxHeight(64);
                        deleteBtn.setMaxWidth(64);
                        deleteBtn.setEnabled(true);
                        deleteBtn.setBackgroundResource(R.drawable.baseline_cancel_24);

                        //add button to linearlayout
                        linearLayout.addView(editBtn);
                        linearLayout.addView(deleteBtn);
                        linearLayoutSwitch.addView(switchMaterial);
                        //add views to row
                        row.addView(author);
                        row.addView(linearLayoutSwitch);
                        row.addView(type);
                        row.addView(date);
                        row.addView(profile_swiped);
                        row.addView(linearLayout);

                        swipeTable.addView(row);

                        //alert on delete
                        deleteBtn.setOnClickListener(v -> {

                            AlertDialog.Builder builder
                                    = new AlertDialog
                                    .Builder(getContext());

                            // Set the message show for the Alert time
                            builder.setMessage("Do you want to remove this swipe?");

                            // Set Alert Title
                            builder.setTitle("Delete Swipe !");

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
                                //first get the email
                                //String deleted_usersID=customer.getUserID();
//                                  usersViewModel.deleteUser(deleted_usersID).observe(getViewLifecycleOwner(),integer -> {
//                                      if(integer.equals(1)){
//                                          userTable.removeView(row);
//
//                                          Toast.makeText(getContext(),"User deleted ",Toast.LENGTH_SHORT).show();
//                                      }else{
//                                          Toast.makeText(getContext(),"User is not deleted ",Toast.LENGTH_SHORT).show();
//                                      }
//                                  });

                                swipeDb.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if(task.isSuccessful()){
                                            if(task.getResult().hasChild(swipe.getSwipe_id())){
                                                swipeDb.child(swipe.getSwipe_id()).removeValue();
                                                Toast.makeText(getContext(),"Swipe is deleted",Toast.LENGTH_SHORT).show();

                                            }else{
                                                Toast.makeText(getContext(),"Swipe is not deleted",Toast.LENGTH_SHORT).show();
                                            }

                                        }else{
                                          Toast.makeText(getContext(),"Swipe is not retrieved",Toast.LENGTH_SHORT).show();
                                        }
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

                        editBtn.setOnClickListener((v) -> {

                            AlertDialog.Builder builder
                                    = new AlertDialog
                                    .Builder(getContext());

                            // Set the message show for the Alert time
                            builder.setMessage("Do you want to update this swipe?");

                            // Set Alert Title
                            builder.setTitle("Delete Swipe !");

                            // Set Cancelable false
                            // for when the user clicks on the outside
                            // the Dialog Box then it will remain show
                            builder.setCancelable(false);



                            builder.setPositiveButton("Yes", (dialog, which) -> {

                                swipeDb.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if(task.isSuccessful()){
                                            if(task.getResult().hasChild(swipe.getSwipe_id())){
                                                swipeDb.child(swipe.getSwipe_id()).setValue(swipe);
                                                Toast.makeText(getContext(),"Swipe is updated",Toast.LENGTH_SHORT).show();

                                            }else{
                                                Toast.makeText(getContext(),"Swipe is not deleted",Toast.LENGTH_SHORT).show();
                                            }

                                        }else{
                                            Toast.makeText(getContext(),"Swipe is not retrieved",Toast.LENGTH_SHORT).show();
                                        }
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
                    }

                }

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}