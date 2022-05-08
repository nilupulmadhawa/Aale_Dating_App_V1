package com.example.aale.ui.add_new_ad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.aale.R;
import com.example.aale.databinding.FragmentAddNewAdBinding;
import com.example.aale.model.Advertisement;
import com.example.aale.ui.advertisements.AdvertisementsViewModel;

public class AdvertisementsNewAddFragment extends Fragment {
    private FragmentAddNewAdBinding binding;
    private AdvertisementsNewAddViewModel advertisementsAddViewModel;

    private Button addNewAdBtn;
    private TextView adID;
    private TextView company_name;
    private TextView ad_title;
    private TextView  description;


    private  Fragment addNewAd;
    private NavController navController;

    private AutoCompleteTextView autoCompleteTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        advertisementsAddViewModel= new ViewModelProvider(this).get( AdvertisementsNewAddViewModel .class);
        binding =  FragmentAddNewAdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        company_name=binding.insertCompany1;
        ad_title=binding.insertAdTitle;
        description=binding.insertDes1;
        adID=binding.insertAdId1;
        autoCompleteTextView = binding.autoCompleteText1;

        addNewAdBtn=binding.createAdBtn1;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] option ={"Foods","Beauty Products","Places","Educations","other"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.option_menu,option);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);

        addNewAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Advertisement add = new Advertisement();
                add.setAdTitle(ad_title.getText().toString());
                add.setDescription(description.getText().toString());
                add.setCategory(autoCompleteTextView.getText().toString());
                add.setCompanyName(company_name.getText().toString());
                add.setAdID(adID.getText().toString());
                advertisementsAddViewModel.addAdvertisement(add).observe(getViewLifecycleOwner(),integer -> {
                    if(integer < 0){
                        Toast.makeText(getContext(),"Advertiement is not sent ",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"Advertiement is sent please check database ",Toast.LENGTH_SHORT).show();
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