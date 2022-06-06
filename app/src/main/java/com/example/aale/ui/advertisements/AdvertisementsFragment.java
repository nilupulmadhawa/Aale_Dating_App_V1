package com.example.aale.ui.advertisements;

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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aale.R;
import com.example.aale.databinding.FragmentAdvertisementsBinding;
import com.example.aale.databinding.FragmentEmailBinding;
import com.example.aale.model.Advertisement;
import com.example.aale.ui.e_mail.EmailViewModel;

public class AdvertisementsFragment extends Fragment {
    private FragmentAdvertisementsBinding binding;
    private AdvertisementsViewModel advertisementsViewModel;
    private TableLayout adTable;
    private Button addNewAdBtn;
    private  Fragment addNewAd;
    private NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AdvertisementsViewModel advertisementsViewModel = new ViewModelProvider(this).get( AdvertisementsViewModel.class);
        binding = FragmentAdvertisementsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adTable=binding.tablePushNotification;
        addNewAdBtn=binding.addNewAdBtn;


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        addNewAdBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               navController.navigate(R.id.action_nav_publish_advertisements_to_advertisementsNewAddFragment);
            }
        });
        advertisementsViewModel =new ViewModelProvider(this).get(AdvertisementsViewModel.class);
        advertisementsViewModel.getAds().observe(getViewLifecycleOwner(),advertisements -> {
            for (Advertisement advertisement:advertisements){
                Log.i("Advertise", "onViewCreated : "+advertisement.getAdID());
                //create table row
                TableRow row = new TableRow(getContext());

                //add values
                //add to table
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                row.setMinimumHeight((int)(1.5*34));

                //add values to row
                TextView advertisementID =new TextView(getContext());
                TextView adTitle =new TextView(getContext());
                TextView category =new TextView(getContext());
                TextView companyName =new TextView(getContext());

                //give text Size sp
                advertisementID.setTextSize(9);
                adTitle.setTextSize(9);
                category.setTextSize(9);
                companyName.setTextSize(9);

                //set alignment
                advertisementID.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                adTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                category.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                companyName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                //set values
                advertisementID.setText(advertisement.getAdID());
                adTitle.setText(advertisement.getAdTitle());
                category.setText(advertisement.getCategory());
                companyName.setText(advertisement.getCompanyName());

                //
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.CENTER);

                //initialize edit btn
                Button  editBtn= new Button(getContext());
                editBtn.setLayoutParams(new LinearLayout.LayoutParams(64,64));

                // editBtn
                editBtn.setId((Integer)90);
                editBtn.setMaxHeight(64);
                editBtn.setBackgroundResource(R.drawable.admin_edit);
                editBtn.setMaxWidth(64);

                editBtn.setEnabled(true);

                //initialize email btn
                Button  deleteBtn= new Button(getContext());
                deleteBtn.setLayoutParams(new LinearLayout.LayoutParams(64,64));

                //delete btn
                deleteBtn.setId((Integer)95);
                deleteBtn.setMaxHeight(64);
                deleteBtn.setMaxWidth(64);
                deleteBtn.setEnabled(true);
                deleteBtn.setBackgroundResource(R.drawable.admin_delete);

                linearLayout.addView(editBtn);
                linearLayout.addView(deleteBtn);

                row.addView(advertisementID);
                row.addView(adTitle);
                row.addView(category);
                row.addView(companyName);
             //   row.addView();
                row.addView(linearLayout);

                adTable.addView(row);

                //alert on delete
                //alert on delete
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(getContext());

                        // Set the message show for the Alert time
                        builder.setMessage("Do you want to delete this AD?");

                        // Set Alert Title
                        builder.setTitle("Alert !");

                        // Set Cancelable false
                        // for when the user clicks on the outside
                        // the Dialog Box then it will remain show
                        builder.setCancelable(false);

                        // Set the positive button with yes name
                        // OnClickListener method is use of
                        // DialogInterface interface.

                        builder
                                .setPositiveButton(
                                        "Yes",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which)
                                            {

                                                // When the user click yes button
                                                // then app user will be removed
                                                advertisementsViewModel.deleteAd(advertisement.getAdID()).observe(getViewLifecycleOwner(),integer -> {
                                                    if(integer.equals(1)){
                                                        adTable.removeView(row);
                                                        Toast.makeText(getContext(),"Advertisement deleted ",Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(getContext(),"Advertisement is not deleted ",Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }
                                        });

                        // Set the Negative button with No name
                        // OnClickListener method is use
                        // of DialogInterface interface.
                        builder
                                .setNegativeButton(
                                        "No",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which)
                                            {

                                                // If user click no
                                                // then dialog box is canceled.
                                                dialog.cancel();
                                            }
                                        });

                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();

                        // Show the Alert Dialog box
                        alertDialog.show();

                        //to remove user
                    }
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