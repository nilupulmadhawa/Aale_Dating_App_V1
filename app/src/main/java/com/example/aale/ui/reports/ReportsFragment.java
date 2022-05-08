package com.example.aale.ui.reports;

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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aale.R;
import com.example.aale.databinding.FragmentPushNotificationsBinding;
import com.example.aale.databinding.FragmentReportsBinding;
import com.example.aale.model.Report;
import com.example.aale.model.Swipe;
import com.example.aale.repo.DBConnectionRepository;
import com.example.aale.ui.push_notifications.PushNotificationsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends Fragment {

    private FragmentReportsBinding binding;
    private TableLayout reportTable;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ReportsViewModel reportsViewModel = new ViewModelProvider(this).get(ReportsViewModel.class);
        binding =  FragmentReportsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        reportTable=binding.tableReports;

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference reportDb = DBConnectionRepository.getReportReference();

        reportDb.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    List<Report> reports = new ArrayList<>();
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        Report report = ds.getValue(Report.class);
                        Log.i("Swipes",report.toString());
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
                        TextView receiver = new TextView(getContext());


                        //set alignment
                        author.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        type.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        receiver.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                        //give text Size
                        author.setTextSize(9);
                        type.setTextSize(9);
                        date.setTextSize(9);
                        receiver.setTextSize(9);

                        //Do not give layout parameters for linear layout
                        //for button in the table
                        //must add a linear lay out
                        LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setGravity(Gravity.CENTER);



                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                64, 64);



                        layoutParams.setMargins(0, 0, 30, 0);

                        //set material spinner
                        SwitchMaterial switchMaterial = new SwitchMaterial(getContext());


                        //set text values
                        author.setText(report.getAuthor());
                        type.setText(report.getReport_type());
                        date.setText(report.getReported_date());
                        receiver.setText(report.getReceiver());
                        //initialize edit btn
                        Button emailBtn = new Button(getContext());
                        emailBtn.setLayoutParams(layoutParams);
                        //editBtn

                        emailBtn.setMaxHeight(64);
                        emailBtn.setBackgroundResource(R.drawable.admin_mail);
                        emailBtn.setMaxWidth(64);
                        emailBtn.setEnabled(true);


                        //initialize delete btn
                        Button deleteBtn = new Button(getContext());
                        deleteBtn.setLayoutParams(new LinearLayout.LayoutParams(64, 64));
                        //set button attributes
                        deleteBtn.setMaxHeight(64);
                        deleteBtn.setMaxWidth(64);
                        deleteBtn.setEnabled(true);
                        deleteBtn.setBackgroundResource(R.drawable.baseline_cancel_24);

                        //add button to linearlayout
                        linearLayout.addView(emailBtn);
                        linearLayout.addView(deleteBtn);

                        //add views to row
                        row.addView(author);
                        row.addView(type);
                        row.addView(date);
                        row.addView(receiver);

                        row.addView(linearLayout);

                        reportTable.addView(row);

                        //alert on delete
                        deleteBtn.setOnClickListener(v -> {

                            AlertDialog.Builder builder
                                    = new AlertDialog
                                    .Builder(getContext());

                            // Set the message show for the Alert time
                            builder.setMessage("Do you want to remove this report?");

                            // Set Alert Title
                            builder.setTitle("Report Report !");

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

                                reportDb.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if(task.isSuccessful()){
                                            if(task.getResult().hasChild(report.getReport_id())){
                                                reportDb.child(report.getReport_id()).removeValue();
                                                Toast.makeText(getContext(),"Report is removed",Toast.LENGTH_SHORT).show();
                                                reportTable.removeView(row);
                                            }else{
                                                Toast.makeText(getContext(),"Report is not removed",Toast.LENGTH_SHORT).show();
                                            }

                                        }else{
                                            Toast.makeText(getContext(),"Report does not exists",Toast.LENGTH_SHORT).show();
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

                        emailBtn.setOnClickListener((v) -> {


                        });

                    }

                }

            }
        });


    }
}